package com.acellam.lms.cbs.soap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import io.credable.kycapitest.wsdl.CustomerRequest;
import io.credable.kycapitest.wsdl.CustomerResponse;

@Component
public class KycClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(KycClient.class);

    public CustomerResponse getCustomerKyc(String customerNumber) {

        CustomerRequest request = new CustomerRequest();
        request.setCustomerNumber(customerNumber);

        log.info("Requesting customer kyc for " + customerNumber);

        CustomerResponse response = (CustomerResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws", request,
                        new SoapActionCallback("http://localhost:8080/ws"));

        return response;
    }

}
