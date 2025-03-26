package com.acellam.lms.cbs;

import org.springframework.stereotype.Service;

import com.acellam.lms.cbs.dtos.CheckKycDto;
import com.acellam.lms.cbs.dtos.KycResponseDto;

@Service
public interface KycService {
    KycResponseDto fetchCustomerKyc(CheckKycDto checkKycDto);
}
