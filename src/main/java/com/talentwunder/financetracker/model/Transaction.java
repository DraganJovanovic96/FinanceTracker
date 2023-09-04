package com.talentwunder.financetracker.model;

import com.talentwunder.financetracker.enumeration.TransactionType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

/**
 * This class represents the Transaction entity.
 * It extends the {@link BaseEntity} class, which contains fields for creation
 * and update timestamps as well as a boolean flag for deletion status.
 *
 * @author Dragan Jovanovic
 * @version 1.0
 * @since 1.0
 */
@Entity
@Data
@Table(name = "transactions")
@SQLDelete(sql = "UPDATE transactions SET deleted = true WHERE id=?")
@FilterDef(name = "deletedTransactionFilter", parameters = @ParamDef(name = "isDeleted", type = Boolean.class))
@Filter(name = "deletedTransactionFilter", condition = "deleted = :isDeleted")
public class Transaction extends BaseEntity<Long> {
    /**
     * The amount` of the transaction.
     */
    private double amount;

    /**
     * The description of the transaction.
     */
    private String description;


    /**
     * The type of the transaction.
     */
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    /**
     * The user associated with the transaction.
     */
    @ManyToOne
    private User user;
}
