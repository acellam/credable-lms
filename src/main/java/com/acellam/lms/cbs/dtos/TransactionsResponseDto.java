package com.acellam.lms.cbs.dtos;

import java.util.List;

public record TransactionsResponseDto(
        List<TransactionResponseDto> transactions) {

}
