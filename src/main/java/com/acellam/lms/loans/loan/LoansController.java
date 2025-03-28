package com.acellam.lms.loans.loan;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acellam.lms.loans.loan.dtos.LoanCheckStatusDto;
import com.acellam.lms.loans.loan.dtos.LoanClientRequestDto;
import com.acellam.lms.loans.loan.dtos.LoanResponseDto;
import com.acellam.lms.loans.loan.service.LoanService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("api/v1/loans")
@RestController
public class LoansController {
    private final LoanService loanService;

    public LoansController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("status/{customerNumber}")
    public LoanResponseDto checkLoanStatus(
            @Valid @PathVariable("customerNumber") LoanCheckStatusDto loanCheckStatusDto) {

        return this.loanService.checkLoanStatus(loanCheckStatusDto);
    }

    @PostMapping
    public LoanResponseDto createLoan(@Valid @RequestBody LoanClientRequestDto loanClientRequestDto) {
        return this.loanService.createLoan(loanClientRequestDto);
    }
}
