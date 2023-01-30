package com.bank.account.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.account.model.input.CustomerBoarding;
import com.bank.account.model.input.TransactionRequest;
import com.bank.account.model.output.AccountBalance;
import com.bank.account.model.output.CustomerReceipt;
import com.bank.account.model.output.TransactionAcknowledgement;
import com.bank.account.model.output.TransactionDetails;
import com.bank.account.service.AccountManagementService;

@RestController
@RequestMapping("/account")
public class AccountManagementController {

    @Autowired
    private AccountManagementService accountManagementService;


    @PostMapping("/createCustomer")
    public ResponseEntity<CustomerReceipt> createCustomer(@Valid @RequestBody CustomerBoarding customerBoarding){
        return ResponseEntity.ok(accountManagementService.createCustomer(customerBoarding));
    }

    @GetMapping("/checkBalance/{accountNumber}")
    public ResponseEntity<AccountBalance> createBalance(@Valid @NotEmpty @PathVariable("accountNumber") Long accountNumber){
        return ResponseEntity.ok(accountManagementService.getAvailableBalance(accountNumber));
    }

    @PostMapping("/makeTransaction")
    public ResponseEntity<TransactionAcknowledgement> makeTransaction(@Valid @RequestBody TransactionRequest transactionRequest){
        TransactionAcknowledgement transactionAcknowledgement = accountManagementService.performTransaction(transactionRequest);
        if(transactionAcknowledgement != null){
            if(transactionAcknowledgement.getTransactionMessage().contains("SUCCESS")){
                return ResponseEntity.ok(transactionAcknowledgement);
            }else{
                return ResponseEntity.badRequest().body(transactionAcknowledgement);
            }
        }
        return ResponseEntity.ok(null);
    }

    @GetMapping("/listTransactions/{accountNumber}")
    public ResponseEntity<List<TransactionDetails>> listTransactions(@Valid @NotEmpty @PathVariable("accountNumber") Long accountNumber){
        return ResponseEntity.ok(accountManagementService.getTransactionsList(accountNumber));
    }
}
