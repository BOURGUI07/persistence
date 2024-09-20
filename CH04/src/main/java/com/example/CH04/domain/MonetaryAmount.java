package com.example.CH04.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

@Data
public class MonetaryAmount implements java.io.Serializable {
    private final BigDecimal value;
    private final Currency currency;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonetaryAmount that = (MonetaryAmount) o;
        return Objects.equals(value, that.value) && Objects.equals(currency, that.currency);
    }

    public static MonetaryAmount fromString(String s) {
        var split = s.split(" ");
        return new MonetaryAmount(new BigDecimal(split[0]), Currency.getInstance(split[1]));
    }
}
