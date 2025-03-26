package com.acellam.lms.loans.customer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.acellam.lms.loans.customer.CustomerMapper;
import com.acellam.lms.loans.customer.CustomerModel;
import com.acellam.lms.loans.customer.CustomerRepository;
import com.acellam.lms.loans.customer.dtos.CustomerResponseDto;
import com.acellam.lms.loans.customer.dtos.CustomerSubscriptionDto;

public class CustomerServiceImplTest {
    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerMapper customerMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCustomer() {
        var customerNumber = "1234";
        var customerId = 1L;
        var firstName = "John";
        var lastName = "Doe";

        CustomerSubscriptionDto customerSubscriptionDto = new CustomerSubscriptionDto(customerNumber);

        CustomerModel customer = new CustomerModel();
        customer.setCustomerNumber(customerNumber);
        customer.setId(customerId);
        customer.setFristName(firstName);
        customer.setLastName(lastName);

        // Mocks
        when(customerRepository.findByCustomerNumber(customerNumber)).thenReturn(customer);

        when(customerMapper.toCustomerResponseDto(customer))
                .thenReturn(
                        new CustomerResponseDto(
                                customerId,
                                customerNumber,
                                firstName,
                                lastName));

        CustomerResponseDto customerResponseDto = this.customerServiceImpl.getCustomer(customerSubscriptionDto);

        assertEquals(customerResponseDto.customerNumber(), customerSubscriptionDto.customerNumber());

        assertEquals(customerResponseDto.customerNumber(), customer.getCustomerNumber());
        assertEquals(customerResponseDto.firstName(), customer.getFristName());
        assertEquals(customerResponseDto.lastName(), customer.getLastName());

    }
}
