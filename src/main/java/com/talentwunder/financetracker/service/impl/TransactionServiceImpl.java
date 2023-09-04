package com.talentwunder.financetracker.service.impl;

import com.talentwunder.financetracker.dto.TransactionCreateDto;
import com.talentwunder.financetracker.dto.TransactionDto;
import com.talentwunder.financetracker.dto.UpdateTransactionDto;
import com.talentwunder.financetracker.enumeration.TransactionType;
import com.talentwunder.financetracker.mapper.TransactionMapper;
import com.talentwunder.financetracker.model.Transaction;
import com.talentwunder.financetracker.model.User;
import com.talentwunder.financetracker.repository.TransactionRepository;
import com.talentwunder.financetracker.service.TransactionService;
import com.talentwunder.financetracker.service.UserService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;

/**
 * Implementation of the Transaction interface.
 * <p>
 * Provides methods to manage transaction-related operations.
 *
 * @author Dragan Jovanovic
 * @version 1.0
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    /**
     * The repository used to retrieve transaction data.
     */
    private final TransactionRepository transactionRepository;

    /**
     * The mapper used to convert transaction data between TransactionDto and Transaction entities.
     */
    private final TransactionMapper transactionMapper;

    /**
     * The service used to retrieve user data.
     */
    private final UserService userService;

    /**
     * An EntityManager instance is associated with a persistence context.
     * A persistence context is a set of entity instances in which for any
     * persistent entity identity there is a unique entity instance.
     */
    private final EntityManager entityManager;


    /**
     * Created TRANSACTION_FILTER attribute, so we can change Filter easily if needed.
     */
    private static final String TRANSACTION_FILTER = "deletedTransactionFilter";

    /**
     * Retrieves a list of all transactions sorted by creation date in ascending order.
     *
     * @return A list of TransactionDto objects representing the transactions.
     */
    @Override
    public List<TransactionDto> getAllTransactions() {
        return transactionMapper.transactionsToTransactionDtos
                (transactionRepository.findAll(Sort.by(Sort.Direction.ASC, "createdAt")));
    }

    /**
     * Retrieves a list of transactions for a user, optionally filtered by transaction type.
     *
     * @param type The type of transactions to filter (e.g., "EXPENSE" or "INCOME").
     *             If null or empty, all transactions for the user are retrieved.
     * @return A list of TransactionDto objects representing the user's transactions.
     */
    @Override
    public List<TransactionDto> getAllTransactionsOfUser(String type) {
        User user = userService.getUserFromAuthentication();
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter(TRANSACTION_FILTER);
        filter.setParameter("isDeleted", false);
        if (type.equals("EXPENSE") || type.equals("INCOME")) {
            TransactionType transactionType = TransactionType.valueOf(type);
            List<Transaction> transactions = transactionRepository.findByTransactionTypeAndUserId
                    (Sort.by(Sort.Direction.ASC, "createdAt"), transactionType, user.getId());

            session.disableFilter(TRANSACTION_FILTER);
            return transactionMapper.transactionsToTransactionDtos(transactions);
        }
        List<Transaction> transactions = transactionRepository.findByUserId(user.getId());

        session.disableFilter(TRANSACTION_FILTER);
        return transactionMapper.transactionsToTransactionDtos(transactions);
    }

    /**
     * A method for deleting transactions. It is implemented in TransactionServiceImpl class.
     *
     * @param transactionId parameter that is unique to entity
     */
    @Override
    public void deleteTransaction(Long transactionId) {
        User user = userService.getUserFromAuthentication();

        transactionRepository.findById(transactionId)
                .map(transaction -> {
                    if (Boolean.TRUE.equals(transaction.getDeleted())) {
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction is already deleted.");
                    }

                    if (transaction.getUser() != user) {
                        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Transaction doesn't belong to the user");
                    }

                    return transaction;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction is not found."));

        transactionRepository.deleteById(transactionId);
    }

    /**
     * Creates a new transaction using the information provided in the {@code TransactionCreateDto}
     * and returns a ResponseEntity object with status code 201 (Created) and the saved TransactionDto
     * object in the response body.
     *
     * @param transactionCreateDto the DTO containing the information for the new transaction to be created
     * @return a ResponseEntity object with status code 201 (Created) and the saved TransactionDto object in the response body
     */
    @Override
    public TransactionDto createTransaction(TransactionCreateDto transactionCreateDto) {
        User user = userService.getUserFromAuthentication();
        Transaction transaction = transactionMapper.transactionCreateDtoToTransaction(transactionCreateDto);
        transaction.setUser(user);
        transactionRepository.save(transaction);

        return transactionMapper.transactionToTransactionDto(transaction);
    }

    /**
     * Updates an existing transaction with the information provided in the UpdateTransactionDto.
     *
     * @param updateTransactionDto The DTO containing the updated information for the transaction.
     * @return A TransactionDto object representing the updated transaction.
     * @throws ResponseStatusException if the transaction with the specified ID is not found,
     *                                 or if the amount in the updateTransactionDto is not a positive number.
     */
    @Override
    public TransactionDto updateTransaction(UpdateTransactionDto updateTransactionDto) {
        Transaction transaction = transactionRepository.findById(updateTransactionDto.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction is not found."));

        transaction.setTransactionType(updateTransactionDto.getTransactionType());
        transaction.setUpdatedAt(Instant.now());
        if (updateTransactionDto.getAmount() <= 0) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Amount must be positive number");
        }
        transaction.setAmount(updateTransactionDto.getAmount());
        transaction.setDescription(updateTransactionDto.getDescription());
        transaction.setDeleted(updateTransactionDto.getDeleted());

        transactionRepository.save(transaction);

        return transactionMapper.transactionToTransactionDto(transaction);
    }
}
