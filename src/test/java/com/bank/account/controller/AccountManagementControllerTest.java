package com.bank.account.controller;


import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.bank.account.TestBase;
import com.bank.account.model.output.AccountBalance;
import com.bank.account.model.output.CustomerReceipt;
import com.bank.account.model.output.TransactionAcknowledgement;
import com.bank.account.model.output.TransactionDetails;
import com.fasterxml.jackson.databind.ObjectMapper;





public class AccountManagementControllerTest extends TestBase{

  

    @Test
    public void testGetAvailableBalance() throws Exception{
       

        var result = mockMvc.perform(get("/account/checkBalance/1000")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andReturn();

      var content = result.getResponse().getContentAsString();
      var response = objectMapper.readValue(content, AccountBalance.class);
            assertEquals(response.getAvailableBalance(),100);

    }

    @Test
    public void testGetAvailableBalanceFailure() throws Exception{

      mockMvc.perform(get("/account/checkBalance/")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().is4xxClientError())
      .andReturn();


    }

    @Test
    public void testListTransactions() throws Exception{
       

        var result = mockMvc.perform(get("/account/listTransactions/1000")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andReturn();

      var content = result.getResponse().getContentAsString();
      var response = objectMapper.readValue(content, List.class);
            assertTrue(!response.isEmpty());
            assertEquals(response.size(), 10);

    }

    @Test
    public void testListTransactionsFailure() throws Exception{

        mockMvc.perform(get("/account/listTransactions/")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().is4xxClientError())
      .andReturn();


    }

    @Test
    public void testListTransactionsEmpty() throws Exception{

        mockMvc.perform(get("/account/listTransactions/999")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andReturn();


    }

    @Test
    public void testMakeTransactionDeposit() throws Exception{
      var resource = new ClassPathResource("requests/makeTransaction.json");
      var json = new String(resource.getInputStream().readAllBytes());

      var result = mockMvc.perform(post("/account/makeTransaction").contentType(MediaType.APPLICATION_JSON).content(json))
              .andExpect(status().isOk()).andReturn();

      var output = objectMapper.readValue(result.getResponse().getContentAsString(), TransactionAcknowledgement.class);
      assertEquals(output.getTransactionMessage(), "DEPOSIT SUCCESS");
    }

    @Test
    public void testMakeTransactionWithdraw() throws Exception{
      var resource = new ClassPathResource("requests/makeTransactionWithdraw.json");
      var json = new String(resource.getInputStream().readAllBytes());

      var result = mockMvc.perform(post("/account/makeTransaction").contentType(MediaType.APPLICATION_JSON).content(json))
              .andExpect(status().isOk()).andReturn();

      var output = objectMapper.readValue(result.getResponse().getContentAsString(), TransactionAcknowledgement.class);
      assertEquals(output.getTransactionMessage(), "WITHDRAWAL SUCCESS");
    }

    @Test
    public void testMakeTransactionWithdrawInvalid() throws Exception{
      var resource = new ClassPathResource("requests/makeTransactionWithdrawInvalid.json");
      var json = new String(resource.getInputStream().readAllBytes());

      var result = mockMvc.perform(post("/account/makeTransaction").contentType(MediaType.APPLICATION_JSON).content(json))
              .andExpect(status().is4xxClientError()).andReturn();

      var output = objectMapper.readValue(result.getResponse().getContentAsString(), TransactionAcknowledgement.class);
      assertEquals(output.getTransactionMessage(), "WITHDRAWAL AMOUNT IS MORE THAN AVAILABLE BALANCE");
    }

    @Test
    public void testMakeTransactionAccountNoInvalid() throws Exception{
      var resource = new ClassPathResource("requests/makeTransactionAccountNoInvalid.json");
      var json = new String(resource.getInputStream().readAllBytes());

      var result = mockMvc.perform(post("/account/makeTransaction").contentType(MediaType.APPLICATION_JSON).content(json))
              .andExpect(status().is4xxClientError()).andReturn();

      var output = objectMapper.readValue(result.getResponse().getContentAsString(), TransactionAcknowledgement.class);
      assertEquals(output.getTransactionMessage(), "ACCOUNT NUMBER IS INVALID");
    }


    @Test
    public void testCreateCustomer() throws Exception{
      var resource = new ClassPathResource("requests/createCustomer.json");
      var json = new String(resource.getInputStream().readAllBytes());

      mockMvc.perform(post("/account/createCustomer").contentType(MediaType.APPLICATION_JSON).content(json))
              .andExpect(status().isOk()).andReturn();

    }



}
