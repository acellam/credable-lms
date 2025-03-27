package com.acellam.lms.loans.loan;

import org.springframework.stereotype.Component;

import com.acellam.lms.loans.customer.Customer;
import com.acellam.lms.loans.loan.dtos.LoanRequestDto;
import com.acellam.lms.loans.loan.dtos.LoanResponseDto;

@Component
public class LoanMapper {
    public Loan toLoan(LoanRequestDto requestLoanDto) {
        Loan loan = new Loan();

        loan.setAmount(requestLoanDto.amount());

        Customer customer = new Customer();
        customer.setId(requestLoanDto.customerId());
        loan.setCustomer(customer);

        return loan;
    }

    public LoanResponseDto toLoanResponseDto(Loan loan) {
        return LoanResponseDto
                .builder()
                .id(loan.getId())
                .amount(loan.getAmount())
                .customerNumber(loan.getCustomer().getCustomerNumber())
                .status(loan.getStatus())
                .build();
    }
}
