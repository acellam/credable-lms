package com.acellam.lms.crb;

import org.springframework.stereotype.Service;

@Service
public interface ScoringService {
    boolean isEligibleForLoan(String customerNumber);
}
