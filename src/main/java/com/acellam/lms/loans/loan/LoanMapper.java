package com.acellam.lms.loans.loan;

import org.springframework.stereotype.Component;

import com.acellam.lms.loans.customer.CustomerModel;
import com.acellam.lms.loans.loan.dtos.LoanRequestDto;
import com.acellam.lms.loans.loan.dtos.LoanResponseDto;

@Component
public class LoanMapper {
    public LoanModel toLoan(LoanRequestDto requestLoanDto) {
        LoanModel loan = new LoanModel();

        loan.setAmount(requestLoanDto.amount());

        CustomerModel customer = new CustomerModel();
        customer.setId(requestLoanDto.customerId());
        loan.setCustomer(customer);

        return loan;
    }

    public LoanResponseDto toLoanResponseDto(LoanModel loan) {
        return LoanResponseDto
                .builder()
                .id(loan.getId())
                .amount(loan.getAmount())
                .customerNumber(loan.getCustomer().getCustomerNumber())
                .status(loan.getStatus())
                .build();
    }
}
