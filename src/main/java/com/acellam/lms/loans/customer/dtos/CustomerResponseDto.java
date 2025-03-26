package com.acellam.lms.loans.customer.dtos;

import lombok.Builder;

@Builder
public record CustomerResponseDto(
        Long customerId,
        String customerNumber,
        String firstName,
        String lastName) {

}
