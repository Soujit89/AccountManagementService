package com.bank.account.model.output;

import java.util.Date;

public class TransactionDetails {
    private Long transactionId;
    private Long transactionAmount;
    private String transactionType;
    private Date transactionTime;

    public Long getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }
    public Long getTransactionAmount() {
        return transactionAmount;
    }
    public void setTransactionAmount(Long transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
    
    public Date getTransactionTime() {
        return transactionTime;
    }
    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }
    public String getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    
    
}
