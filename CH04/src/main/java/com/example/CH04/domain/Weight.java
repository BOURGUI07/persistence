package com.example.CH04.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Embeddable
@AttributeOverride(name="name", column = @Column(name="WEIGHT_NAME"))
@AttributeOverride(name="symbol", column = @Column(name="WEIGHT_SYMBOL"))
@NoArgsConstructor
@Setter
public class Weight extends Measurement{
    @Column(nullable=false)
    private BigDecimal value;
}
