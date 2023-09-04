package com.talentwunder.financetracker.mapper;

import com.talentwunder.financetracker.dto.TransactionCreateDto;
import com.talentwunder.financetracker.dto.TransactionDto;
import com.talentwunder.financetracker.dto.UserDto;
import com.talentwunder.financetracker.model.Transaction;
import com.talentwunder.financetracker.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * TransactionMapper is a mapper interface that defines mapping methods between {@link Transaction} and{@link TransactionDto}
 * classes using MapStruct library. It also enables list to list mapping.
 *
 * @author Dragan Jovanovic
 * @version 1.0
 * @since 1.0
 */
@Mapper
public interface TransactionMapper {

    /**
     * Maps a Transaction object to a TransactionDto object.
     *
     * @param transaction the Transaction object to be mapped to a TransactionDto object
     * @return a TransactionDto object containing the transaction's information
     */
    @Mapping(target = "userDto", source = "user")
    TransactionDto transactionToTransactionDto(Transaction transaction);

    /**
     * Maps a TransactionDto object to a Transaction object.
     *
     * @param transactionCreateDto the TransactionCreateDto object to be mapped to a Transaction object
     * @return a Transaction object containing the transactionDto's information
     */
    Transaction transactionCreateDtoToTransaction(TransactionCreateDto transactionCreateDto);

    /**
     * Maps a list of Transactions objects to a list of TransactionDto objects.
     *
     * @param transactions the List<Transactions> to be mapped to a List<TransactionDto>
     * @return a List<TransactionDto> containing the TransactionDto information
     */
    List<TransactionDto> transactionsToTransactionDtos(List<Transaction> transactions);
}
