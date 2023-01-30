package com.bank.account.model.input;

import javax.validation.constraints.NotBlank;

public class TransactionRequest {

    public enum TransactionType { DEPOSIT, WITHDRAW} ;

    private Long accountNumber;
    private Long transactionAmount;
    private TransactionType transactionType;

    public Long getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }
    public Long getTransactionAmount() {
        return transactionAmount;
    }
    public void setTransactionAmount(Long transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
    public TransactionType getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    
    
}
