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

        Customer customer = this.customerMapper.toCustomer(customerSubscriptionDto);

        assertEquals(customer.getCustomerNumber(), customer.getCustomerNumber());
    }

    @Test
    void testToCustomerResponseDto() {
        Customer customer = new Customer();
        customer.setCustomerNumber("1234");
        customer.setId(1L);

        CustomerResponseDto customerResponseDto = this.customerMapper.toCustomerResponseDto(customer);

        assertEquals(customerResponseDto.customerNumber(), customer.getCustomerNumber());
    }
}
