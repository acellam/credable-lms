package com.acellam.lms.cbs;

import org.springframework.stereotype.Service;

import io.credable.kycapitest.wsdl.Customer;
import io.credable.kycapitest.wsdl.CustomerResponse;

import com.acellam.lms.cbs.dtos.CheckKycDto;
import com.acellam.lms.cbs.dtos.KycResponseDto;

@Service
public class KycServiceImpl implements KycService {

    private final KycClient kycClient;

    public KycServiceImpl(KycClient kycClient) {
        this.kycClient = kycClient;
    }

    @Override
    public KycResponseDto fetchCustomerKyc(CheckKycDto checkKycDto) {
        CustomerResponse customerResponse = this.kycClient.getCustomerKyc(checkKycDto.customerNumber());

        if (customerResponse == null) {
            return null;
        }

        Customer customer = customerResponse.getCustomer();

        KycResponseDto kycResponseDto = new KycResponseDto(
                customer.getId(),
                customer.getCustomerNumber(),
                customer.getFirstName(),
                customer.getLastName());

        return kycResponseDto;
    }

}
