package com.example.CH04.Util;

import com.example.CH04.domain.MonetaryAmount;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class MonetaryAmountConverter implements AttributeConverter<MonetaryAmount, String> {
    @Override
    public String convertToDatabaseColumn(MonetaryAmount monetaryAmount) {
        return monetaryAmount.toString();
    }

    @Override
    public MonetaryAmount convertToEntityAttribute(String s) {
        return MonetaryAmount.fromString(s);
    }
}
