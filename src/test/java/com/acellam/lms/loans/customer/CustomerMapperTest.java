package com.acellam.lms.loans.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.acellam.lms.loans.customer.dtos.CustomerResponseDto;
import com.acellam.lms.loans.customer.dtos.CustomerSubscriptionDto;

public class CustomerMapperTest {
    private CustomerMapper customerMapper;

    @BeforeEach
    void setUp() {
        this.customerMapper = new CustomerMapper();
    }

    @Test
    void testToCustomer() {
        CustomerSubscriptionDto customerSubscriptionDto = new CustomerSubscriptionDto("1234");

        CustomerModel customerModel = this.customerMapper.toCustomer(customerSubscriptionDto);

        assertEquals(customerModel.getCustomerNumber(), customerModel.getCustomerNumber());
    }

    @Test
    void testToCustomerResponseDto() {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setCustomerNumber("1234");
        customerModel.setId(1L);

        CustomerResponseDto customerResponseDto = this.customerMapper.toCustomerResponseDto(customerModel);

        assertEquals(customerResponseDto.customerNumber(), customerModel.getCustomerNumber());
    }
}
