package com.talentwunder.financetracker.dto;

import lombok.Data;

/**
 * A Data Transfer Object (DTO) for transferring user data between layers of the application.
 *
 * @author Dragan Jovanovic
 * @version 1.0
 * @since 1.0
 */
@Data
public class SummaryDto {
    /**
     * The total income represented as a double.
     */
    private double totalIncome;

    /**
     * The total expenses represented as a double.
     */
    private double totalExpense;

    /**
     * The balance calculated as the difference between total income and total expenses.
     */
    private double balance;
}
