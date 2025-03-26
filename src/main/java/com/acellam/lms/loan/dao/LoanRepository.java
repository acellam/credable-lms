package com.acellam.lms.loan.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acellam.lms.loan.models.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {

}
