package com.talentwunder.financetracker.service;

import com.talentwunder.financetracker.dto.TransactionCreateDto;
import com.talentwunder.financetracker.dto.TransactionDto;
import com.talentwunder.financetracker.dto.UpdateTransactionDto;

import java.util.List;

/**
 * The TransactionService interface contains methods that will be implemented is TransactionServiceImpl and
 * methods correlate to Transaction entity.
 *
 * @author Dragan Jovanovic
 * @version 1.0
 * @since 1.0
 */
public interface TransactionService {
    /**
     * A method for retrieving all transactions implemented in TransactionServiceImpl class.
     *
     * @return Transaction data through TransactionDto
     */
    List<TransactionDto> getAllTransactions();

    /**
     * A method for retrieving all transactions of the user implemented in TransactionServiceImpl class
     * by the type of transaction.
     *
     * @return Transaction data through TransactionDto
     */
    List<TransactionDto> getAllTransactionsOfUser(String type);

    /**
     * A method for deleting transactions. It is implemented in TransactionServiceImpl class.
     *
     * @param transactionId parameter that is unique to entity
     */
    void deleteTransaction(Long transactionId);

    /**
     * A method for saving {@link com.talentwunder.financetracker.model.Transaction} object in database.
     *
     * @param transactionCreateDto contains transaction data to be saved
     * @return {@link TransactionDto} object that contains saved data
     */
    TransactionDto createTransaction(TransactionCreateDto transactionCreateDto);

    /**
     * A method for updating {@link com.talentwunder.financetracker.model.Transaction} object in database.
     *
     * @param updateTransactionDto contains transaction data to be updated
     * @return {@link TransactionDto} object that contains saved data
     */
    TransactionDto updateTransaction(UpdateTransactionDto updateTransactionDto);
}
