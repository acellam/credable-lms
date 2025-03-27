package com.acellam.lms.loans.customer;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acellam.lms.loans.customer.dtos.CustomerResponseDto;
import com.acellam.lms.loans.customer.dtos.CustomerSubscriptionDto;
import com.acellam.lms.loans.customer.service.CustomerService;

import jakarta.validation.Valid;

@RequestMapping("api/v1/customers")
@RestController
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public CustomerResponseDto createCustomer(@Valid @RequestBody CustomerSubscriptionDto customerSubscriptionDto) {
        return this.customerService.createCustomer(customerSubscriptionDto);
    }
}
