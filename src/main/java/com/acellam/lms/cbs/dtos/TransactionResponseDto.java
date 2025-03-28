package com.acellam.lms.cbs.dtos;

import lombok.Builder;

@Builder
public record TransactionResponseDto(
        String accountNumber,
        double alternativechanneltrnscrAmount,
        int alternativechanneltrnscrNumber,
        double alternativechanneltrnsdebitAmount,
        int alternativechanneltrnsdebitNumber,
        int atmTransactionsNumber,
        double atmtransactionsAmount,
        int bouncedChequesDebitNumber,
        int bouncedchequescreditNumber,
        double bouncedchequetransactionscrAmount,
        double bouncedchequetransactionsdrAmount,
        double chequeDebitTransactionsAmount,
        int chequeDebitTransactionsNumber,
        String createdAt,
        String createdDate,
        double credittransactionsAmount,
        double debitcardpostransactionsAmount,
        int debitcardpostransactionsNumber,
        double fincominglocaltransactioncrAmount,
        Long id,
        double incominginternationaltrncrAmount,
        int incominginternationaltrncrNumber,
        int incominglocaltransactioncrNumber,
        Integer intrestAmount,
        String lastTransactionDate,
        String lastTransactionType,
        Integer lastTransactionValue,
        double maxAtmTransactions,
        double maxMonthlyBebitTransactions,
        double maxalternativechanneltrnscr,
        double maxalternativechanneltrnsdebit,
        double maxbouncedchequetransactionscr,
        double maxchequedebittransactions,
        double maxdebitcardpostransactions,
        double maxincominginternationaltrncr,
        double maxincominglocaltransactioncr,
        double maxmobilemoneycredittrn,
        double maxmobilemoneydebittransaction,
        double maxmonthlycredittransactions,
        double maxoutgoinginttrndebit,
        double maxoutgoinglocaltrndebit,
        double maxoverthecounterwithdrawals,
        double minAtmTransactions,
        double minMonthlyDebitTransactions,
        double minalternativechanneltrnscr,
        double minalternativechanneltrnsdebit,
        double minbouncedchequetransactionscr,
        double minchequedebittransactions,
        double mindebitcardpostransactions,
        double minincominginternationaltrncr,
        double minincominglocaltransactioncr,
        double minmobilemoneycredittrn,
        double minmobilemoneydebittransaction,
        double minmonthlycredittransactions,
        double minoutgoinginttrndebit,
        double minoutgoinglocaltrndebit,
        double minoverthecounterwithdrawals,
        double mobilemoneycredittransactionAmount,
        int mobilemoneycredittransactionNumber,
        double mobilemoneydebittransactionAmount,
        int mobilemoneydebittransactionNumber,
        double monthlyBalance,
        double monthlydebittransactionsAmount,
        double outgoinginttransactiondebitAmount,
        int outgoinginttrndebitNumber,
        double outgoinglocaltransactiondebitAmount,
        int outgoinglocaltransactiondebitNumber,
        double overdraftLimit,
        double overthecounterwithdrawalsAmount,
        int overthecounterwithdrawalsNumber,
        double transactionValue,
        String updatedAt) {

}
