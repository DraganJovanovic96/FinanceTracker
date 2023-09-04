package com.talentwunder.financetracker.repository;

import com.talentwunder.financetracker.enumeration.TransactionType;
import com.talentwunder.financetracker.model.Transaction;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * A method for finding all transactions.
     *
     * @return List of transactions that are fetched
     */
    List<Transaction> findAll();

    /**
     * A method for finding all transactions by type and sorted.
     *
     * @return List of transactions that are fetched
     */
    List<Transaction> findByTransactionTypeAndUserId(Sort sort, TransactionType transactionType, Long userId);

    /**
     * A method for finding all transactions by type.
     *
     * @return List of transactions that are fetched
     */
    List<Transaction> findByTransactionTypeAndUserId(TransactionType transactionType, Long userId);

    @Query("SELECT t FROM Transaction t WHERE t.user.id = :userId")
    List<Transaction> findByUserId(Long userId);
}
