package com.acellam.lms.loans.customer.service;

import com.acellam.lms.loans.customer.dtos.CustomerResponseDto;
import com.acellam.lms.loans.customer.dtos.CustomerSubscriptionDto;

public interface CustomerService {
    CustomerResponseDto getCustomer(CustomerSubscriptionDto customerSubscriptionDto);

    CustomerResponseDto createCustomer(CustomerSubscriptionDto customerSubscriptionDto);
}
