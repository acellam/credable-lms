package com.acellam.lms.cbs;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.credable.trxapitest.wsdl.TransactionData;
import io.credable.trxapitest.wsdl.TransactionsResponse;

import com.acellam.lms.cbs.dtos.FetchTransactionDto;
import com.acellam.lms.cbs.dtos.TransactionResponseDto;
import com.acellam.lms.cbs.soap.TransactionClient;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionClient transactionClient;

    public TransactionServiceImpl(TransactionClient transactionClient) {
        this.transactionClient = transactionClient;
    }

    @Override
    public List<TransactionResponseDto> fetchTransactions(FetchTransactionDto fetchTransactionDto) {
        TransactionsResponse transactionsResponse = this.transactionClient
                .fetchTransactions(fetchTransactionDto.customerNumber());

        if (transactionsResponse == null) {
            return null;
        }

        List<TransactionData> transactions = transactionsResponse.getTransactions();
        List<TransactionResponseDto> transactionResponses = transactions
                .stream()
                .map(transactionData -> new TransactionResponseDto(
                        transactionData.getAccountNumber(),
                        transactionData.getAlternativechanneltrnscrAmount(),
                        transactionData.getAlternativechanneltrnscrNumber(),
                        transactionData.getAlternativechanneltrnsdebitAmount(),
                        transactionData.getAlternativechanneltrnsdebitNumber(),
                        transactionData.getAtmTransactionsNumber(),
                        transactionData.getAtmtransactionsAmount(),
                        transactionData.getBouncedChequesDebitNumber(),
                        transactionData.getBouncedchequescreditNumber(),
                        transactionData.getBouncedchequetransactionscrAmount(),
                        transactionData.getBouncedchequetransactionsdrAmount(),
                        transactionData.getChequeDebitTransactionsAmount(),
                        transactionData.getChequeDebitTransactionsNumber(),
                        transactionData.getCreatedAt().toString(),
                        transactionData.getCreatedDate().toString(),
                        transactionData.getCredittransactionsAmount(),
                        transactionData.getDebitcardpostransactionsAmount(),
                        transactionData.getDebitcardpostransactionsNumber(),
                        transactionData.getFincominglocaltransactioncrAmount(),
                        transactionData.getId(),
                        transactionData.getIncominginternationaltrncrAmount(),
                        transactionData.getIncominginternationaltrncrNumber(),
                        transactionData.getIncominglocaltransactioncrNumber(),
                        transactionData.getIntrestAmount(),
                        transactionData.getLastTransactionDate().toString(),
                        transactionData.getLastTransactionType(),
                        transactionData.getLastTransactionValue(),
                        transactionData.getMaxAtmTransactions(),
                        transactionData.getMaxMonthlyBebitTransactions(),
                        transactionData.getMaxalternativechanneltrnscr(),
                        transactionData.getMaxalternativechanneltrnsdebit(),
                        transactionData.getMaxbouncedchequetransactionscr(),
                        transactionData.getMaxchequedebittransactions(),
                        transactionData.getMaxdebitcardpostransactions(),
                        transactionData.getMaxincominginternationaltrncr(),
                        transactionData.getMaxincominglocaltransactioncr(),
                        transactionData.getMaxmobilemoneycredittrn(),
                        transactionData.getMaxmobilemoneydebittransaction(),
                        transactionData.getMaxmonthlycredittransactions(),
                        transactionData.getMaxoutgoinginttrndebit(),
                        transactionData.getMaxoutgoinglocaltrndebit(),
                        transactionData.getMaxoverthecounterwithdrawals(),
                        transactionData.getMinAtmTransactions(),
                        transactionData.getMinMonthlyDebitTransactions(),
                        transactionData.getMinalternativechanneltrnscr(),
                        transactionData.getMinalternativechanneltrnsdebit(),
                        transactionData.getMinbouncedchequetransactionscr(),
                        transactionData.getMinchequedebittransactions(),
                        transactionData.getMindebitcardpostransactions(),
                        transactionData.getMinincominginternationaltrncr(),
                        transactionData.getMinincominglocaltransactioncr(),
                        transactionData.getMinmobilemoneycredittrn(),
                        transactionData.getMinmobilemoneydebittransaction(),
                        transactionData.getMinmonthlycredittransactions(),
                        transactionData.getMinoutgoinginttrndebit(),
                        transactionData.getMinoutgoinglocaltrndebit(),
                        transactionData.getMinoverthecounterwithdrawals(),
                        transactionData.getMobilemoneycredittransactionAmount(),
                        transactionData.getMobilemoneycredittransactionNumber(),
                        transactionData.getMobilemoneydebittransactionAmount(),
                        transactionData.getMobilemoneydebittransactionNumber(),
                        transactionData.getMonthlyBalance(),
                        transactionData.getMonthlydebittransactionsAmount(),
                        transactionData.getOutgoinginttransactiondebitAmount(),
                        transactionData.getOutgoinginttrndebitNumber(),
                        transactionData.getOutgoinglocaltransactiondebitAmount(),
                        transactionData.getOutgoinglocaltransactiondebitNumber(),
                        transactionData.getOverdraftLimit(),
                        transactionData.getOverthecounterwithdrawalsAmount(),
                        transactionData.getOverthecounterwithdrawalsNumber(),
                        transactionData.getTransactionValue(),
                        transactionData.getUpdatedAt().toString()))
                .collect(Collectors.toList());

        return transactionResponses;
    }

}
