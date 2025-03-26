package com.acellam.lms.loans.customer.service;

import org.springframework.stereotype.Component;

import com.acellam.lms.loans.customer.dtos.CustomerResponseDto;
import com.acellam.lms.loans.customer.dtos.CustomerSubscriptionDto;

@Component
public interface CustomerService {
    CustomerResponseDto getCustomer(CustomerSubscriptionDto customerSubscriptionDto);
}
