package com.example.CH04.Util;

import com.example.CH04.domain.GermanZipcode;
import com.example.CH04.domain.SwissZipcode;
import com.example.CH04.domain.Zipcode;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ZipcodeConverter implements AttributeConverter<Zipcode,String> {
    @Override
    public String convertToDatabaseColumn(Zipcode zipcode) {
        return zipcode.getValue();
    }

    @Override
    public Zipcode convertToEntityAttribute(String s) {
        if(s.length()==5)
            return new GermanZipcode(s);
        else if(s.length()==4)
            return new SwissZipcode(s);
        throw new IllegalArgumentException("Invalid zipcode format " + s);
    }
}
