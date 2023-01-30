package com.bank.account.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bank.account.entity.AccountDetails;

@Repository
public interface AccountDetailsRepository extends JpaRepository<AccountDetails,Long> {
    @Query(nativeQuery = true,
            value = "SELECT * FROM account_details ad " +
                    " WHERE ad.customer_id=:customerId")
    List<AccountDetails> findByCustomerId(@Param("customerId") Long customerId);

    AccountDetails findByAccountNumber(Long accountNumber);
    
}
