package com.acellam.lms.loans.loan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.acellam.lms.loans.loan.dtos.LoanRequestDto;
import com.acellam.lms.loans.loan.dtos.LoanResponseDto;
import com.acellam.lms.loans.customer.Customer;

public class LoanMapperTest {
    private LoanMapper loanMapper;

    @BeforeEach
    void setUp() {
        this.loanMapper = new LoanMapper();
    }

    @Test
    void testToLoan() {
        LoanRequestDto loanRequestDto = new LoanRequestDto(1L, 1000);

        LoanModel loan = this.loanMapper.toLoan(loanRequestDto);

        assertEquals(loan.getAmount(), loanRequestDto.amount());
        assertNotNull(loan.getCustomer());
        assertEquals(loan.getCustomer().getId(), loanRequestDto.customerId());

    }

    @Test
    void testToLoanResponseDto() {
        Customer customer = new Customer();
        customer.setCustomerNumber("1234");
        customer.setId(1L);

        LoanModel loan = new LoanModel();
        loan.setId(1L);
        loan.setAmount(1000);
        loan.setCustomer(customer);

        LoanResponseDto loanResponseDto = this.loanMapper.toLoanResponseDto(loan);

        assertEquals(loanResponseDto.amount(), loan.getAmount());
        assertEquals(loanResponseDto.id(), loan.getId());
        assertEquals(loan.getCustomer().getCustomerNumber(), loanResponseDto.customerNumber());
        assertEquals(loan.getStatus(), loanResponseDto.status());

    }
}
