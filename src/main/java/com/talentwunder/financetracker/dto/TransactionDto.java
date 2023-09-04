package com.talentwunder.financetracker.dto;

import com.talentwunder.financetracker.config.ValidTransactionType;
import com.talentwunder.financetracker.enumeration.TransactionType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

/**
 * A Data Transfer Object (DTO) for transferring user data between layers of the application.
 * It extends the {@link BaseEntityDto} class.
 *
 * @author Dragan Jovanovic
 * @version 1.0
 * @since 1.0
 */
@Data
public class TransactionDto extends BaseEntityDto {
    /**
     * The type of the transaction.
     */
    @ValidTransactionType(message = "Transaction type must be either INCOME or EXPENSE")
    private TransactionType transactionType;

    /**
     * The amount` of the transaction.
     */
    @Positive(message = "Amount must be greater than 0")
    private double amount;

    /**
     * The description of the transaction.
     */
    @NotEmpty(message = "Description must not be empty")
    private String description;

    /**
     * The user of the transaction.
     */
    @NotNull(message = "User must not be null")
    private UserDto userDto;
}
