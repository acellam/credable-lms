package com.acellam.lms.cbs;

import java.util.List;

import com.acellam.lms.cbs.dtos.FetchTransactionDto;
import com.acellam.lms.cbs.dtos.TransactionResponseDto;

public interface TransactionService {
    List<TransactionResponseDto> fetchTransactions(FetchTransactionDto FetchTransactionDto);
}
