package com.acellam.lms.loans.customer.service;

import org.springframework.stereotype.Service;

import com.acellam.lms.cbs.KycService;
import com.acellam.lms.cbs.dtos.CheckKycDto;
import com.acellam.lms.cbs.dtos.KycResponseDto;
import com.acellam.lms.loans.customer.CustomerMapper;
import com.acellam.lms.loans.customer.Customer;
import com.acellam.lms.loans.customer.CustomerRepository;
import com.acellam.lms.loans.customer.dtos.CustomerResponseDto;
import com.acellam.lms.loans.customer.dtos.CustomerSubscriptionDto;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final KycService kycService;

    public CustomerServiceImpl(
            CustomerRepository customerRepository,
            CustomerMapper customerMapper, KycService kycService) {

        this.kycService = kycService;
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public CustomerResponseDto getCustomer(CustomerSubscriptionDto customerSubscriptionDto) {
        Customer customerModel = customerRepository
                .findByCustomerNumber(customerSubscriptionDto.customerNumber());

        if (customerModel == null) {
            throw new RuntimeException("Failed to retrieve customer");
        }

        return this.customerMapper.toCustomerResponseDto(customerModel);
    }

    public CustomerResponseDto createCustomer(CustomerSubscriptionDto customerSubscriptionDto) {
        Customer customer = this.customerMapper.toCustomer(customerSubscriptionDto);

        KycResponseDto kycResponseDto = kycService
                .fetchCustomerKyc(new CheckKycDto(customer.getCustomerNumber()));

        // check customer kyc
        if (kycResponseDto == null) {
            throw new RuntimeException("Customer KYC is not valid");
        }

        Customer savedCustomer = customerRepository.save(customer);

        return this.customerMapper.toCustomerResponseDto(savedCustomer);
    }

}
