package com.acellam.lms.loans.customer.service;

import org.springframework.stereotype.Service;

import com.acellam.lms.loans.customer.dtos.CustomerResponseDto;
import com.acellam.lms.loans.customer.dtos.CustomerSubscriptionDto;

@Service
public interface CustomerService {
    CustomerResponseDto getCustomer(CustomerSubscriptionDto customerSubscriptionDto);
}
