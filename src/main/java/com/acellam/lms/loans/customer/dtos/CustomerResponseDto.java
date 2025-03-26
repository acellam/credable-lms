package com.acellam.lms.loans.customer.dtos;

import lombok.Builder;

@Builder
public record CustomerResponseDto(
        String customerNumber,
        String firstName,
        String lastName) {

}
