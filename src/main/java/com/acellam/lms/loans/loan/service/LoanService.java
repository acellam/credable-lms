package com.acellam.lms.loans.loan.service;

import com.acellam.lms.loans.loan.dtos.LoanClientRequestDto;
import com.acellam.lms.loans.loan.dtos.LoanResponseDto;

public interface LoanService {
    LoanResponseDto createLoan(LoanClientRequestDto loanClientRequest);
}
