package com.bank.account.model.output;

public class AccountBalance {

    private Long accountnumber;
    private Long availableBalance;
    private String currency;

    public Long getAccountnumber() {
        return accountnumber;
    }
    public void setAccountnumber(Long accountnumber) {
        this.accountnumber = accountnumber;
    }
    public Long getAvailableBalance() {
        return availableBalance;
    }
    public void setAvailableBalance(Long availableBalance) {
        this.availableBalance = availableBalance;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    

    

    
    
}
