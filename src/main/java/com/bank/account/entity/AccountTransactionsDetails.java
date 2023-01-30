package com.bank.account.entity;



import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT_TRANSACTIONS_DETAILS")
@SequenceGenerator(name = "id_gen", sequenceName = "id_gen",  initialValue = 1000)
public class AccountTransactionsDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id_gen")
    @Column(name = "transaction_id", updatable = false, nullable = false)
    protected Long transactionId;

    @Column(name = "transaction_amount", nullable = false)
    protected Long transactionAmount;

    @Column(name = "transaction_type", nullable = false)
    protected String transactionType;

    @Column(name = "transaction_time_stamp", nullable = false)
    protected Date transactionTimeStamp;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="account_number", referencedColumnName = "account_number")
    protected AccountDetails accountDetails;


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

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Date getTransactionTimeStamp() {
        return transactionTimeStamp;
    }

    public void setTransactionTimeStamp(Date transactionTimeStamp) {
        this.transactionTimeStamp = transactionTimeStamp;
    }

    public AccountDetails getAccountDetails() {
        return accountDetails;
    }

    public void setAccountDetails(AccountDetails accountDetails) {
        this.accountDetails = accountDetails;
    }

    
    
}
