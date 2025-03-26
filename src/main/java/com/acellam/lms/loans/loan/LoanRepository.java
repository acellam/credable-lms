package com.acellam.lms.loans.loan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<LoanModel, Long> {
    boolean existsByCustomerIdAndStatus(Long customerId, LoanStatus status);
}
