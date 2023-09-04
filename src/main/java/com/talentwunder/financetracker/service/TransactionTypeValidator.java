package com.talentwunder.financetracker.service;

import com.talentwunder.financetracker.config.ValidTransactionType;
import com.talentwunder.financetracker.enumeration.TransactionType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * A custom constraint validator for validating the validity of TransactionType enumeration values.
 * Ensures that the provided TransactionType is either INCOME or EXPENSE.
 * <p>
 * This validator is used in conjunction with the @ValidTransactionType annotation.
 */
public class TransactionTypeValidator implements ConstraintValidator<ValidTransactionType, TransactionType> {
    /**
     * Initializes the TransactionTypeValidator.
     *
     * @param constraintAnnotation The annotation instance being validated.
     */
    @Override
    public void initialize(ValidTransactionType constraintAnnotation) {
    }

    /**
     * Validates if the provided TransactionType value is valid.
     *
     * @param value   The TransactionType value to be validated.
     * @param context The validation context.
     * @return true if the value is either INCOME or EXPENSE, false otherwise.
     */
    @Override
    public boolean isValid(TransactionType value, ConstraintValidatorContext context) {
        return value != null && (value == TransactionType.INCOME || value == TransactionType.EXPENSE);
    }
}