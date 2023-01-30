package com.bank.account.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT_DETAILS")
@SequenceGenerator(name = "account_number_gen", sequenceName = "account_number_gen",  initialValue = 10000)
public class AccountDetails {

    @Id
    @Column(name = "account_number", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "account_number_gen")
    protected Long accountNumber;
    @Column(name = "account_type",nullable = false)
    protected String accountType;
    @Column(name = "account_balance",nullable = false)
    protected Long accountBalance;

    @Column(name = "currency",nullable = false)
    protected String currency = "USD";

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "customer_id", referencedColumnName = "customer_id")
    protected CustomerDetails customerDetails;

    @OneToMany(cascade = CascadeType.ALL)
    private List<AccountTransactionsDetails> accountTransactionsDetails;
    
    public Long getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }
    public String getAccountType() {
        return accountType;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    public Long getAccountBalance() {
        return accountBalance;
    }
    public void setAccountBalance(Long accountBalance) {
        this.accountBalance = accountBalance;
    }
    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }
    public void setCustomerDetails(CustomerDetails customerDetails) {
        this.customerDetails = customerDetails;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public List<AccountTransactionsDetails> getAccountTransactionsDetails() {
        return accountTransactionsDetails;
    }
    public void setAccountTransactionsDetails(List<AccountTransactionsDetails> accountTransactionsDetails) {
        this.accountTransactionsDetails = accountTransactionsDetails;
    }

    
    
    
}
