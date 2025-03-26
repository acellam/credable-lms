package com.acellam.lms.loans.customer.dtos;

import jakarta.validation.constraints.NotEmpty;

public record CustomerSubscriptionDto(
                @NotEmpty(message = "Customer number is required") String customerNumber) {
}
