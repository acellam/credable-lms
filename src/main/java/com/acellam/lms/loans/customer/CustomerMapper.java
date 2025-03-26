package com.acellam.lms.loans.customer;

import org.springframework.stereotype.Component;

import com.acellam.lms.loans.customer.dtos.CustomerResponseDto;
import com.acellam.lms.loans.customer.dtos.CustomerSubscriptionDto;

@Component
public class CustomerMapper {
    public CustomerModel toCustomer(CustomerSubscriptionDto customerSubscriptionDto) {
        CustomerModel customerModel = new CustomerModel();

        customerModel.setCustomerNumber(customerSubscriptionDto.customerNumber());

        return customerModel;
    }

    public CustomerResponseDto toCustomerResponseDto(CustomerModel customerModel) {
        return CustomerResponseDto
                .builder()
                .firstName(customerModel.getFristName())
                .lastName(customerModel.getLastName())
                .customerNumber(customerModel.getCustomerNumber())
                .build();
    }
}
