package com.acellam.lms.cbs.dtos;

import jakarta.validation.constraints.NotEmpty;

public record FetchTransactionDto(
        @NotEmpty(message = "Customer number is required") String customerNumber) {

}
