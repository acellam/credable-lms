package com.acellam.lms.cbs.soap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import io.credable.trxapitest.wsdl.TransactionsRequest;
import io.credable.trxapitest.wsdl.TransactionsResponse;

@Component
public class TransactionClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(TransactionClient.class);

    public TransactionsResponse fetchTransactions(String customerNumber) {

        TransactionsRequest request = new TransactionsRequest();
        request.setCustomerNumber(customerNumber);

        log.info("Requesting customers transactions ");

        TransactionsResponse response = (TransactionsResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws", request,
                        new SoapActionCallback("http://localhost:8080/ws"));

        return response;
    }

}
