package com.acellam.lms.loans.loan.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.acellam.lms.loans.customer.CustomerModel;
import com.acellam.lms.loans.customer.dtos.CustomerResponseDto;
import com.acellam.lms.loans.customer.dtos.CustomerSubscriptionDto;
import com.acellam.lms.loans.customer.service.CustomerService;
import com.acellam.lms.loans.loan.LoanMapper;
import com.acellam.lms.loans.loan.LoanModel;
import com.acellam.lms.loans.loan.LoanRepository;
import com.acellam.lms.loans.loan.LoanStatus;
import com.acellam.lms.loans.loan.dtos.LoanClientRequestDto;
import com.acellam.lms.loans.loan.dtos.LoanRequestDto;
import com.acellam.lms.loans.loan.dtos.LoanResponseDto;

public class LoanServiceImplTest {
    @InjectMocks
    private LoanServiceImpl loanServiceImpl;

    @Mock
    private LoanRepository loanRepository;
    @Mock
    private CustomerService customerService;
    @Mock
    private LoanMapper loanMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateLoan() {
        String customerNumber = "1234";
        Long customerId = 1L;
        Long loanId = 1L;
        String firstName = "John";
        String lastName = "Doe";
        int amount = 1000;
        LoanStatus loanStatus = LoanStatus.REQUESTED;

        // Models
        CustomerModel customer = new CustomerModel();
        customer.setCustomerNumber(customerNumber);
        customer.setId(customerId);
        customer.setFristName(firstName);
        customer.setLastName(lastName);

        LoanModel loan = new LoanModel(loanId, amount, loanStatus, customer);

        // DTOs
        CustomerSubscriptionDto customerSubscriptionDto = new CustomerSubscriptionDto(customerNumber);

        CustomerResponseDto customerResponseDto = new CustomerResponseDto(
                customerId, customerNumber, firstName, lastName);

        LoanRequestDto loanRequestDto = new LoanRequestDto(customerId, amount);

        LoanClientRequestDto loanClientRequestDto = new LoanClientRequestDto(customerNumber, amount);

        // Mocks
        when(customerService.getCustomer(customerSubscriptionDto)).thenReturn(customerResponseDto);

        when(loanMapper.toLoan(loanRequestDto)).thenReturn(loan);

        when(loanRepository.save(loan)).thenReturn(loan);

        when(loanMapper.toLoanResponseDto(loan))
                .thenReturn(new LoanResponseDto(
                        customerNumber,
                        loanId,
                        amount,
                        loanStatus));

        // Then
        LoanResponseDto loanResponseDto = loanServiceImpl.createLoan(loanClientRequestDto);

        // Assertions

        assertNotNull(loan.getCustomer());
        assertEquals(loan.getCustomer(), customer);

        assertEquals(loanResponseDto.status(), loanStatus);
        assertEquals(loanResponseDto.amount(), amount);
        assertEquals(loanResponseDto.status(), loan.getStatus());
        assertEquals(loanResponseDto.amount(), loan.getAmount());

    }
}
