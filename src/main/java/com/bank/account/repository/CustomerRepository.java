package com.bank.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.account.entity.CustomerDetails;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerDetails,Long> {
    
}
