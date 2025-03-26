package com.acellam.lms.loan.mappers;

import org.springframework.stereotype.Component;

import com.acellam.lms.loan.dtos.Loans.LoanRequestDto;
import com.acellam.lms.loan.dtos.Loans.LoanResponseDto;
import com.acellam.lms.loan.models.Customer;
import com.acellam.lms.loan.models.Loan;

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
