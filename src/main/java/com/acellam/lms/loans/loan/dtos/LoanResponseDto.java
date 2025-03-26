package com.acellam.lms.loans.loan.dtos;

import com.acellam.lms.loans.loan.LoanStatus;

import lombok.Builder;

@Builder
public record LoanResponseDto(
                String customerNumber,
                Long id,
                int amount,
                LoanStatus status) {

}
