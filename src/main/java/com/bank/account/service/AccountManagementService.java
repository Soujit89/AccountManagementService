package com.bank.account.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.bank.account.entity.AccountDetails;
import com.bank.account.entity.AccountTransactionsDetails;
import com.bank.account.entity.CustomerDetails;
import com.bank.account.model.input.CustomerBoarding;
import com.bank.account.model.input.TransactionRequest;
import com.bank.account.model.output.AccountBalance;
import com.bank.account.model.output.CustomerReceipt;
import com.bank.account.model.output.TransactionAcknowledgement;
import com.bank.account.model.output.TransactionDetails;
import com.bank.account.repository.AccountDetailsRepository;
import com.bank.account.repository.AccountTransactionsDetailsRepository;
import com.bank.account.repository.CustomerRepository;

@Service
public class AccountManagementService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountDetailsRepository accountDetailsRepository;
    @Autowired
    private AccountTransactionsDetailsRepository accountTransactionsDetailsRepository;

    @Transactional
    public CustomerReceipt createCustomer(CustomerBoarding customerBoarding){

        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setCustomerName(customerBoarding.getCustomerName());
        customerDetails.setCustomerAddress(customerBoarding.getCustomerAddress());
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setAccountBalance(Long.parseLong("0"));
        accountDetails.setAccountType(customerBoarding.getAccountType().toString());
        accountDetails.setCustomerDetails(customerDetails);
        List<AccountDetails> accountDetailsList = new ArrayList<AccountDetails>();
        accountDetailsList.add(accountDetails);
        customerDetails.setAccountDetails(accountDetailsList);


        customerDetails = customerRepository.save(customerDetails);

        if(customerDetails!=null){
            CustomerReceipt customerReceipt = new CustomerReceipt(); 
            customerReceipt.setCustomerId(customerDetails.getCustomerId());
            customerReceipt.setCustomerName(customerDetails.getCustomerName());
            customerReceipt.setCustomerAddress(customerDetails.getCustomerAddress());
            if(!CollectionUtils.isEmpty(customerDetails.getAccountDetails())){
                customerReceipt.setAccountnumber(customerDetails.getAccountDetails().get(0).getAccountNumber()); 
            }

            return customerReceipt;
        }
        
        return null;
    }


    public AccountBalance getAvailableBalance(Long accountNumber){

        AccountDetails accountDetails = accountDetailsRepository.findByAccountNumber(accountNumber);

        if(accountDetails!=null){
            AccountBalance accountBalance = new AccountBalance();
            accountBalance.setAccountnumber(accountDetails.getAccountNumber());
            accountBalance.setAvailableBalance(accountDetails.getAccountBalance());
            accountBalance.setCurrency(accountDetails.getCurrency());
            return accountBalance;
        }

        return null;
    }

    public List<TransactionDetails> getTransactionsList(Long accountNumber){

        List<AccountTransactionsDetails> transactionsList = accountTransactionsDetailsRepository.findTransactionsList(accountNumber);

        if(!CollectionUtils.isEmpty(transactionsList)){
            List<TransactionDetails> transactionDetailsList = new ArrayList<TransactionDetails>();
            for(AccountTransactionsDetails accountTransactionsDetails : transactionsList){
                TransactionDetails transactionDetails = new TransactionDetails();
                transactionDetails.setTransactionId(accountTransactionsDetails.getTransactionId());
                transactionDetails.setTransactionAmount(accountTransactionsDetails.getTransactionAmount());
                transactionDetails.setTransactionType(accountTransactionsDetails.getTransactionType());
                transactionDetails.setTransactionTime(accountTransactionsDetails.getTransactionTimeStamp());
                transactionDetailsList.add(transactionDetails);
            }
            return transactionDetailsList;
        }

        return null;
    }

    @Transactional
    public TransactionAcknowledgement performTransaction(TransactionRequest transactionRequest){
        AccountDetails accountDetails = accountDetailsRepository.findByAccountNumber(transactionRequest.getAccountNumber());
        if(accountDetails == null){
            TransactionAcknowledgement transactionAcknowledgement = new TransactionAcknowledgement();
                transactionAcknowledgement.setAccountNumber(transactionRequest.getAccountNumber());
                transactionAcknowledgement.setTransactionMessage("ACCOUNT NUMBER IS INVALID");
                return transactionAcknowledgement;
                
        }
        if(transactionRequest.getTransactionType().toString().equals("DEPOSIT")){
            accountDetails.setAccountBalance(accountDetails.getAccountBalance() + transactionRequest.getTransactionAmount());
            accountDetails = accountDetailsRepository.save(accountDetails);
            if(accountDetails != null){
                AccountTransactionsDetails accountTransactionsDetails = new AccountTransactionsDetails();
                accountTransactionsDetails.setAccountDetails(accountDetails);
                accountTransactionsDetails.setTransactionAmount(transactionRequest.getTransactionAmount());
                accountTransactionsDetails.setTransactionTimeStamp(new Date());
                accountTransactionsDetails.setTransactionType(transactionRequest.getTransactionType().toString());
                accountTransactionsDetails = accountTransactionsDetailsRepository.save(accountTransactionsDetails);
                if(accountTransactionsDetails != null){
                    TransactionAcknowledgement transactionAcknowledgement = new TransactionAcknowledgement();
                    transactionAcknowledgement.setAccountNumber(accountDetails.getAccountNumber());
                    transactionAcknowledgement.setAvailableBalance(accountDetails.getAccountBalance());
                    transactionAcknowledgement.setTransactionMessage("DEPOSIT SUCCESS");
                    transactionAcknowledgement.setTransactionType(transactionRequest.getTransactionType().toString());
                    transactionAcknowledgement.setTrsanctionAmount(transactionRequest.getTransactionAmount());
                    return transactionAcknowledgement;
                }
            }
        }else if(transactionRequest.getTransactionType().toString().equals("WITHDRAW")){
            if(accountDetails!= null && accountDetails.getAccountBalance() < transactionRequest.getTransactionAmount()){
                TransactionAcknowledgement transactionAcknowledgement = new TransactionAcknowledgement();
                transactionAcknowledgement.setAccountNumber(accountDetails.getAccountNumber());
                transactionAcknowledgement.setAvailableBalance(accountDetails.getAccountBalance());
                transactionAcknowledgement.setTransactionMessage("WITHDRAWAL AMOUNT IS MORE THAN AVAILABLE BALANCE");
                transactionAcknowledgement.setTransactionType(transactionRequest.getTransactionType().toString());
                transactionAcknowledgement.setTrsanctionAmount(transactionRequest.getTransactionAmount());

                return transactionAcknowledgement;
            }else{
                accountDetails.setAccountBalance(accountDetails.getAccountBalance() - transactionRequest.getTransactionAmount());
                accountDetails = accountDetailsRepository.save(accountDetails);
                if(accountDetails != null){
                    AccountTransactionsDetails accountTransactionsDetails = new AccountTransactionsDetails();
                    accountTransactionsDetails.setAccountDetails(accountDetails);
                    accountTransactionsDetails.setTransactionAmount(transactionRequest.getTransactionAmount());
                    accountTransactionsDetails.setTransactionTimeStamp(new Date());
                    accountTransactionsDetails.setTransactionType(transactionRequest.getTransactionType().toString());
                    accountTransactionsDetails = accountTransactionsDetailsRepository.save(accountTransactionsDetails);
                    if(accountTransactionsDetails != null){
                        TransactionAcknowledgement transactionAcknowledgement = new TransactionAcknowledgement();
                        transactionAcknowledgement.setAccountNumber(accountDetails.getAccountNumber());
                        transactionAcknowledgement.setAvailableBalance(accountDetails.getAccountBalance());
                        transactionAcknowledgement.setTransactionMessage("WITHDRAWAL SUCCESS");
                        transactionAcknowledgement.setTransactionType(transactionRequest.getTransactionType().toString());
                        transactionAcknowledgement.setTrsanctionAmount(transactionRequest.getTransactionAmount());
                        return transactionAcknowledgement;
                    }
                }
                
            }
        }

        
        
        return null;
    }
    
}
