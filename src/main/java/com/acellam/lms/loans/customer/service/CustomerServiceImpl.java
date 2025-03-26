package com.acellam.lms.loans.customer.service;

import org.springframework.stereotype.Component;

import com.acellam.lms.loans.customer.CustomerMapper;
import com.acellam.lms.loans.customer.CustomerModel;
import com.acellam.lms.loans.customer.CustomerRepository;
import com.acellam.lms.loans.customer.dtos.CustomerResponseDto;
import com.acellam.lms.loans.customer.dtos.CustomerSubscriptionDto;

@Component
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public CustomerResponseDto getCustomer(CustomerSubscriptionDto customerSubscriptionDto) {
        CustomerModel customerModel = customerRepository
                .findByCustomerNumber(customerSubscriptionDto.customerNumber());

        return this.customerMapper.toCustomerResponseDto(customerModel);
    }
}
