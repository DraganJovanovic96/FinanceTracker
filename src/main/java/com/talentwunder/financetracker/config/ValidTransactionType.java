package com.talentwunder.financetracker.config;

import com.talentwunder.financetracker.service.TransactionTypeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * The {@code ValidTransactionType} annotation is used to mark a field as requiring validation
 * for a valid transaction type. It associates the field with a custom validator, {@link TransactionTypeValidator},
 * to ensure that the value of the annotated field conforms to a valid transaction type.
 * <p>
 * A valid transaction type represent different types of financial transactions
 * within the application.
 *
 * @author Dragan Jovanovic
 * @version 1.0
 * @see TransactionTypeValidator
 * @see com.talentwunder.financetracker.service.TransactionTypeValidator
 * @see jakarta.validation.Constraint
 * @see jakarta.validation.Payload
 * @since 1.0
 */
@Documented
@Constraint(validatedBy = TransactionTypeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTransactionType {
    /**
     * Defines the message to be used for validation error messages when the annotated field
     * does not conform to a valid transaction type.
     *
     * @return The validation error message.
     */
    String message() default "Invalid transaction type";

    /**
     * Defines the groups that the annotated field belongs to for validation purposes.
     *
     * @return An array of group classes.
     */
    Class<?>[] groups() default {};

    /**
     * Defines any additional payloads that should be attached to the validation constraint.
     *
     * @return An array of payload classes.
     */
    Class<? extends Payload>[] payload() default {};
}