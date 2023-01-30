package com.bank.account.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bank.account.entity.AccountTransactionsDetails;

@Repository
public interface AccountTransactionsDetailsRepository extends JpaRepository<AccountTransactionsDetails,Long> {
    @Query(nativeQuery = true,
            value = "SELECT * FROM account_transactions_details atd " +
                    " WHERE atd.account_number=:accountNumber and rownum < 11 order by atd.transaction_time_stamp desc")
    List<AccountTransactionsDetails> findTransactionsList(@Param("accountNumber") Long accountNumber);
}
