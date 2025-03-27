package com.acellam.lms.loans.customer;

import org.springframework.stereotype.Component;

import com.acellam.lms.loans.customer.dtos.CustomerResponseDto;
import com.acellam.lms.loans.customer.dtos.CustomerSubscriptionDto;

@Component
public class CustomerMapper {
    public Customer toCustomer(CustomerSubscriptionDto customerSubscriptionDto) {
        Customer customer = new Customer();

        customer.setCustomerNumber(customerSubscriptionDto.customerNumber());

        return customer;
    }

    public CustomerResponseDto toCustomerResponseDto(Customer customer) {
        return CustomerResponseDto
                .builder()
                .firstName(customer.getFristName())
                .lastName(customer.getLastName())
                .customerNumber(customer.getCustomerNumber())
                .build();
    }
}
