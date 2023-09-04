package com.talentwunder.financetracker.service.impl;

import com.talentwunder.financetracker.dto.SummaryDto;
import com.talentwunder.financetracker.enumeration.TransactionType;
import com.talentwunder.financetracker.model.Transaction;
import com.talentwunder.financetracker.model.User;
import com.talentwunder.financetracker.repository.TransactionRepository;
import com.talentwunder.financetracker.service.SummaryService;
import com.talentwunder.financetracker.service.UserService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SummaryServiceImpl implements SummaryService {
    /**
     * The repository used to retrieve transaction data.
     */
    private final TransactionRepository transactionRepository;

    /**
     * The repository used to retrieve user data.
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
     * Retrieves a financial summary for the currently authenticated user. The summary includes the total income,
     * total expenses, and the resulting balance.
     * <p>
     * The method fetches income and expense transactions associated with the authenticated user, calculates the
     * respective totals, and calculates the balance as the difference between total income and total expenses.
     */
    @Override
    public SummaryDto getSummary() {
        User user = userService.getUserFromAuthentication();
        SummaryDto summaryDto = new SummaryDto();
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter(TRANSACTION_FILTER);
        filter.setParameter("isDeleted", false);
        List<Transaction> incomes = transactionRepository.
                findByTransactionTypeAndUserId(TransactionType.INCOME, user.getId());
        List<Transaction> expenses = transactionRepository.
                findByTransactionTypeAndUserId(TransactionType.EXPENSE, user.getId());
        session.disableFilter(TRANSACTION_FILTER);

        double incomeTotal = 0;

        double expenseTotal = 0;

        for (Transaction transaction : incomes) {
            incomeTotal = incomeTotal + transaction.getAmount();
        }

        for (Transaction transaction : expenses) {
            expenseTotal = expenseTotal + transaction.getAmount();
        }

        summaryDto.setTotalIncome(incomeTotal);
        summaryDto.setTotalExpense(expenseTotal);
        summaryDto.setBalance(incomeTotal - expenseTotal);

        return summaryDto;
    }
}
