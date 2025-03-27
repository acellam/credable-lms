package com.acellam.lms.loans.transaction;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.acellam.lms.cbs.TransactionService;
import com.acellam.lms.cbs.dtos.FetchTransactionDto;
import com.acellam.lms.cbs.dtos.TransactionResponseDto;

import jakarta.validation.Valid;

@RequestMapping("api/v1/transactions")
@RestController
public class TransactionsController {
    private final TransactionService transactionService;

    public TransactionsController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<TransactionResponseDto> fetchTransactions(
            @Valid @RequestBody FetchTransactionDto fetchTransactionDto) {
        return this.transactionService.fetchTransactions(fetchTransactionDto);
    }
}
