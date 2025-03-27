package com.acellam.lms.cbs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.acellam.lms.cbs.dtos.FetchTransactionDto;
import com.acellam.lms.cbs.dtos.TransactionResponseDto;
import com.acellam.lms.cbs.soap.TransactionClient;

import io.credable.trxapitest.wsdl.TransactionData;
import io.credable.trxapitest.wsdl.TransactionsResponse;

public class TransactionServiceImplTest {
    @InjectMocks
    private TransactionServiceImpl transactionServiceImpl;

    @Mock
    private TransactionClient transactionClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFetchTransactions() {
        String customerNumber = "1234";
        String accountNumber = "881771";
        double alternativechanneltrnscrAmount = 10000;
        int alternativechanneltrnscrNumber = 28382636;
        double alternativechanneltrnsdebitAmount = 1500.50;
        String createdAt = "2021-09-01T12:00:00";

        javax.xml.datatype.XMLGregorianCalendar xmlCreatedAt = null;
        try {
            javax.xml.datatype.DatatypeFactory datatypeFactory = javax.xml.datatype.DatatypeFactory.newInstance();
            xmlCreatedAt = datatypeFactory.newXMLGregorianCalendar(createdAt);
        } catch (javax.xml.datatype.DatatypeConfigurationException e) {
            e.printStackTrace();
        }

        FetchTransactionDto fetchTransactionDto = new FetchTransactionDto(customerNumber);
        TransactionsResponse transactionsResponse = new TransactionsResponse();
        TransactionData transactionData = new TransactionData();
        transactionData.setAccountNumber(accountNumber);
        transactionData.setAlternativechanneltrnscrAmount(alternativechanneltrnscrAmount);
        transactionData.setAlternativechanneltrnscrNumber(alternativechanneltrnscrNumber);
        transactionData.setAlternativechanneltrnsdebitAmount(alternativechanneltrnsdebitAmount);
        transactionData.setCreatedAt(xmlCreatedAt);
        transactionData.setCreatedDate(xmlCreatedAt);
        transactionData.setLastTransactionDate(xmlCreatedAt);
        transactionData.setUpdatedAt(xmlCreatedAt);
        transactionsResponse.getTransactions().add(transactionData);

        when(transactionClient
                .fetchTransactions(fetchTransactionDto.customerNumber()))
                .thenReturn(transactionsResponse);

        List<TransactionResponseDto> transactions = transactionServiceImpl
                .fetchTransactions(fetchTransactionDto);

        TransactionResponseDto transaction = transactions.get(0);

        assertEquals(transactions.size(), transactionsResponse.getTransactions().size());
        assertEquals(transaction.accountNumber(), accountNumber);
        assertEquals(transaction.alternativechanneltrnscrAmount(), alternativechanneltrnscrAmount);
        assertEquals(transaction.alternativechanneltrnscrNumber(), alternativechanneltrnscrNumber);
        assertEquals(transaction.alternativechanneltrnsdebitAmount(), alternativechanneltrnsdebitAmount);
        assertEquals(transaction.createdAt(), xmlCreatedAt.toString());

    }
}
