package com.acellam.lms.loan.dtos.Loans;

import lombok.Builder;

@Builder
public record LoanResponseDto(
                String customerNumber,
                Long id,
                int amount,
                String status,
                String reason,
                String createdDate,
                String updatedDate) {

}
