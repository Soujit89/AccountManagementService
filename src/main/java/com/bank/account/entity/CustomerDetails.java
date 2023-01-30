package com.bank.account.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER_DETAILS")
@SequenceGenerator(name = "customer_id_gen", sequenceName = "customer_id_gen",  initialValue = 20000)
public class CustomerDetails {
    @Id
    @Column(name = "customer_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "customer_id_gen")
    protected Long customerId;   
    @Column(name = "customer_name",nullable = false)
    protected String customerName;
    @Column(name = "customer_address",nullable = false)
    protected String customerAddress;

    @OneToMany(cascade = CascadeType.ALL)
    private List<AccountDetails> accountDetails;


    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getCustomerAddress() {
        return customerAddress;
    }
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
    public List<AccountDetails> getAccountDetails() {
        return accountDetails;
    }
    public void setAccountDetails(List<AccountDetails> accountDetails) {
        this.accountDetails = accountDetails;
    }

    


}
