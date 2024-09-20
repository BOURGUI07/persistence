package com.example.CH04.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class BankAccount extends BillingDetails{
    @Column(nullable = false)
    private String account;
    @Column(nullable = false)
    private String bankName;
    @Column(nullable = false)
    private String swift;
}
