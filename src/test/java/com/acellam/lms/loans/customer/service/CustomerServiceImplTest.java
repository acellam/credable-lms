package com.acellam.lms.loans.customer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.acellam.lms.cbs.KycServiceImpl;
import com.acellam.lms.cbs.dtos.CheckKycDto;
import com.acellam.lms.cbs.dtos.KycResponseDto;
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

    @Mock
    private KycServiceImpl kycServiceImpl;

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

    @Test
    void testCreateCustomer() {
        var customerNumber = "1234";
        var customerId = 1L;
        var firstName = "John";
        var lastName = "Doe";

        CustomerSubscriptionDto customerSubscriptionDto = new CustomerSubscriptionDto(customerNumber);
        CheckKycDto checkKycDto = new CheckKycDto(customerNumber);

        CustomerModel customer = new CustomerModel();
        customer.setCustomerNumber(customerNumber);
        customer.setId(customerId);
        customer.setFristName(firstName);
        customer.setLastName(lastName);

        KycResponseDto kycResponseDto = new KycResponseDto(customerId, customerNumber, firstName, lastName);

        // Mocks
        when(customerMapper.toCustomer(customerSubscriptionDto)).thenReturn(customer);

        when(customerRepository.save(customer)).thenReturn(customer);

        when(customerMapper.toCustomerResponseDto(customer))
                .thenReturn(
                        new CustomerResponseDto(
                                customerId,
                                customerNumber,
                                firstName,
                                lastName));

        when(kycServiceImpl.fetchCustomerKyc(checkKycDto)).thenReturn(kycResponseDto);

        // Then
        CustomerResponseDto customerResponseDto = this.customerServiceImpl.createCustomer(customerSubscriptionDto);

        // Assertions
        assertEquals(customerResponseDto.customerId(), customerId);
        assertEquals(customerResponseDto.customerNumber(), customerNumber);
        assertEquals(customerResponseDto.firstName(), firstName);
        assertEquals(customerResponseDto.lastName(), lastName);
    }

    @Test
    void testFailCreateCustomer() {
        var customerNumber = "1234";
        var customerId = 1L;
        var firstName = "John";
        var lastName = "Doe";

        CustomerModel customer = new CustomerModel();
        customer.setCustomerNumber(customerNumber);
        customer.setId(customerId);
        customer.setFristName(firstName);
        customer.setLastName(lastName);

        CustomerSubscriptionDto customerSubscriptionDto = new CustomerSubscriptionDto(customerNumber);
        CheckKycDto checkKycDto = new CheckKycDto(customerNumber);

        // Mock
        when(customerMapper.toCustomer(customerSubscriptionDto)).thenReturn(customer);

        when(kycServiceImpl.fetchCustomerKyc(checkKycDto)).thenReturn(null);

        // Assertions
        var exception = assertThrows(RuntimeException.class,
                () -> this.customerServiceImpl.createCustomer(customerSubscriptionDto));

        assertEquals("Customer KYC is not valid", exception.getMessage());

    }

    @Test
    void testFailedToGetCustomer() {
        var customerNumber = "1234";

        CustomerSubscriptionDto customerSubscriptionDto = new CustomerSubscriptionDto(customerNumber);

        var exception = assertThrows(RuntimeException.class,
                () -> this.customerServiceImpl.getCustomer(customerSubscriptionDto));

        assertEquals("Failed to retrieve customer", exception.getMessage());
    }
}
