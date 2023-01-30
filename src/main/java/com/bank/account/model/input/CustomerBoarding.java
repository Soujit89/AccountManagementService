package com.bank.account.model.input;

import javax.validation.constraints.NotBlank;

public class CustomerBoarding {

    public enum AccountType { SAVINGS, CURRENT} ;

    @NotBlank(message = "Name cannot be empty")
    private String customerName;
    @NotBlank(message = "Address cannot be empty")
    private String customerAddress;

    private AccountType accountType;

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

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    


    
}
