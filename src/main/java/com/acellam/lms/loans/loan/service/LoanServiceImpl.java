package com.acellam.lms.loans.loan.service;

import org.springframework.stereotype.Service;

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

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final CustomerService customerService;
    private final LoanMapper loanMapper;

    public LoanServiceImpl(
            LoanRepository loanRepository, CustomerService customerService, LoanMapper loanMapper) {

        this.loanMapper = loanMapper;
        this.customerService = customerService;
        this.loanRepository = loanRepository;
    }

    public LoanResponseDto createLoan(LoanClientRequestDto loanClientRequest) {
        // check if customer exists
        CustomerSubscriptionDto customerSubscriptionDto = new CustomerSubscriptionDto(
                loanClientRequest.customerNumber());

        CustomerResponseDto customerResponseDto = customerService.getCustomer(customerSubscriptionDto);

        if (customerResponseDto == null) {
            throw new RuntimeException("Customer not found");
        }

        // check if there is an on going loan
        checkOngoingLoan(customerResponseDto);

        LoanRequestDto loanRequestDto = LoanRequestDto
                .builder()
                .customerId(customerResponseDto.customerId())
                .amount(loanClientRequest.amount())
                .build();

        LoanModel loan = this.loanMapper.toLoan(loanRequestDto);
        loan.setStatus(LoanStatus.REQUESTED);

        LoanModel savedLoan = this.loanRepository.save(loan);

        LoanResponseDto loanResponseDto = this.loanMapper.toLoanResponseDto(savedLoan);

        return loanResponseDto;
    }

    // Private methods

    private void checkOngoingLoan(CustomerResponseDto customerResponseDto) {
        // check if customer has a pending loan request
        boolean hasPendingLoanRequest = loanRepository.existsByCustomerIdAndStatus(
                customerResponseDto.customerId(), LoanStatus.REQUESTED);

        if (hasPendingLoanRequest) {
            throw new RuntimeException("Customer has a pending loan request");
        }

        // check if customer has an approved loan
        boolean hasApprovedLoan = loanRepository.existsByCustomerIdAndStatus(
                customerResponseDto.customerId(), LoanStatus.APPROVED);
        if (hasApprovedLoan) {
            throw new RuntimeException("Customer has an approved loan pending to be disbursed");
        }

        // check if customer has an active loan
        boolean hasActiveLoan = loanRepository.existsByCustomerIdAndStatus(
                customerResponseDto.customerId(), LoanStatus.ACTIVE);
        if (hasActiveLoan) {
            throw new RuntimeException("Customer has an active loan");
        }
    }

}
