package com.bank.account.model.output;

public class TransactionAcknowledgement {


    private Long accountNumber;
    private Long trsanctionAmount;
    private String transactionType;
    private Long availableBalance;
    private String transactionMessage;

    public Long getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }
    public Long getTrsanctionAmount() {
        return trsanctionAmount;
    }
    public void setTrsanctionAmount(Long trsanctionAmount) {
        this.trsanctionAmount = trsanctionAmount;
    }
    public String getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    public Long getAvailableBalance() {
        return availableBalance;
    }
    public void setAvailableBalance(Long availableBalance) {
        this.availableBalance = availableBalance;
    }
    public String getTransactionMessage() {
        return transactionMessage;
    }
    public void setTransactionMessage(String transactionMessage) {
        this.transactionMessage = transactionMessage;
    }

    




    
}
