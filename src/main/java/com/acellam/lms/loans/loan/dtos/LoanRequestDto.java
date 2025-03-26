package com.acellam.lms.loans.loan.dtos;

import lombok.Builder;

@Builder
public record LoanRequestDto(
        Long customerId,
        int amount) {
}
