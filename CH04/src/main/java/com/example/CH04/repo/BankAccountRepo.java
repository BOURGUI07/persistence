package com.example.CH04.repo;

import com.example.CH04.domain.BankAccount;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepo extends BillingDetailRepo<BankAccount,Long>{
    List<BankAccount> findBySwift(String swift);
}
