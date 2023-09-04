package com.talentwunder.financetracker.controller;

import com.talentwunder.financetracker.dto.TransactionCreateDto;
import com.talentwunder.financetracker.dto.TransactionDto;
import com.talentwunder.financetracker.dto.UpdateTransactionDto;
import com.talentwunder.financetracker.service.TransactionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The TransactionController class is a REST controller which is responsible for handling HTTP requests related to Transaction management.
 * It communicates with the transaction service to perform CRUD operations on transaction resources.
 * The RequiredArgsConstructor is used for fetching transactionService from IoC container.
 *
 * @author Dragan Jovanovic
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("api/v1/transactions")
@RequiredArgsConstructor
@CrossOrigin
public class TransactionController {
    /**
     * The service used to for transaction.
     */
    private final TransactionService transactionService;

    /**
     * This endpoint retrieves all transactions data in ascending order.
     *
     * @return ResponseEntity<List> {@link TransactionDto} - The HTTP response containing the
     * list of TransactionDto objects as the response body
     */
    @GetMapping(value = "/all-transactions", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('admin:read')")
    @ApiOperation(value = "Get transactions' data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Transactions' data successfully fetched.", response = TransactionDto.class)
    })
    public ResponseEntity<List<TransactionDto>> getAllTransactions() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(transactionService.getAllTransactions());
    }


    /**
     * The endpoint accepts a GET request.
     * Retrieves the transactions data for a given user id that is received through path variable.
     *
     * @return ResponseEntity {@link TransactionDto}  containing the transactions' data for the specified user id
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get transactions data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Transactions data successfully fetched.", response = TransactionDto.class),
            @ApiResponse(code = 404, message = "User doesn't exist.")
    })
    public ResponseEntity<List<TransactionDto>> getUserTransactions(
            @RequestParam(value = "type", defaultValue = "", required = false) String type) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(transactionService.getAllTransactionsOfUser(type));
    }

    /**
     * The endpoint accepts a DELETE request.
     *
     * @param transactionId the id of the Transaction to delete
     * @return HTTP status
     */
    @DeleteMapping(value = "/{transactionId}")
    @ApiOperation(value = "Delete transaction")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Transaction successfully deleted."),
            @ApiResponse(code = 404, message = "Transaction is not found."),
            @ApiResponse(code = 403, message = "Transaction does not belong to the user."),
            @ApiResponse(code = 404, message = "Transaction is already deleted.")
    })
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long transactionId) {
        transactionService.deleteTransaction(transactionId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }

    /**
     * Creates a new transaction using the information provided in the {@code transactionCreateDto}
     * and returns a ResponseEntity object with status code 201 (Created) and the saved TransactionDto
     * object in the response body.
     *
     * @param transactionCreateDto the DTO containing the information for the new transaction to be created
     * @return a ResponseEntity object with status code 201 (Created) and the saved TransactionDto
     * object in the response body
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('user:create')")
    @ApiOperation(value = "Save transaction through TransactionCreateDto")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully transaction.", response = TransactionDto.class),
            @ApiResponse(code = 404, message = "User with this id doesn't exists.")
    })
    public ResponseEntity<TransactionDto> createTransaction(@Valid @RequestBody TransactionCreateDto transactionCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(transactionService.createTransaction(transactionCreateDto));
    }

    /**
     * A method for updating transaction data in database.
     *
     * @param updateTransactionDto contains transaction data to be saved in database
     * @return ResponseEntity object which contains TransactionDto object that contains saved data
     */
    @PutMapping
    @ApiOperation(value = "Saving transaction data.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully saved transaction data.", response = TransactionDto.class),
            @ApiResponse(code = 403, message = "User doesn't own transaction.", response = TransactionDto.class),
    })
    public ResponseEntity<TransactionDto> updateTransaction(@RequestBody UpdateTransactionDto updateTransactionDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(transactionService.updateTransaction(updateTransactionDto));
    }
}
