package com.example.CH04.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Embeddable
@AttributeOverride(name="name", column = @Column(name="DIMENSIONS_NAME"))
@AttributeOverride(name="symbol", column = @Column(name="DIMENSIONS_SYMBOL"))
@NoArgsConstructor
@Setter
public class Dimensions extends Measurement{
    @Column(nullable = false)
    private BigDecimal height;
    @Column(nullable = false)
    private BigDecimal width;
    @Column(nullable = false)
    private BigDecimal depth;


}
