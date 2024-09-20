package com.example.CH04.repo;

import com.example.CH04.domain.CreditCard;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditCardRepo extends BillingDetailRepo<CreditCard,Long>{
    List<CreditCard> findByExpiryYear(String expYear);
}
