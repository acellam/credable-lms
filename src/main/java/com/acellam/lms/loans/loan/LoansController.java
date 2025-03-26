package com.acellam.lms.loans.loan;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acellam.lms.loans.loan.dtos.LoanCheckStatusDto;
import com.acellam.lms.loans.loan.dtos.LoanResponseDto;
import com.acellam.lms.loans.loan.service.LoanServiceImpl;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequestMapping("api/v1/loans")
@RestController
public class LoansController {
    private final LoanServiceImpl loanServiceImpl;

    public LoansController(LoanServiceImpl loanServiceImpl) {
        this.loanServiceImpl = loanServiceImpl;
    }

    @GetMapping("status/{customerNumber}")
    public LoanResponseDto checkLoanStatus(
            @Valid @PathVariable("customerNumber") LoanCheckStatusDto loanCheckStatusDto) {

        return this.loanServiceImpl.checkLoanStatus(loanCheckStatusDto);
    }
}
