package com.acellam.lms.loans.loan.dtos;

public record LoanRequestDto(
        Long customerId,
        int amount) {
}
