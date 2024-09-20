package com.example.CH04.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AttributeOverride(name="owner", column = @Column(name="CC_OWNER", nullable=false))
public class CreditCard extends BillingDetails{

    @Column(nullable = false)
    private String cardNumber;
    @Column(nullable = false)
    private String expiryMonth;
    @Column(nullable = false)
    private String expiryYear;
}
