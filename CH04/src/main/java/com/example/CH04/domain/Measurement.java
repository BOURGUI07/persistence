package com.example.CH04.domain;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;

@MappedSuperclass
@Getter
public abstract class Measurement {
    private String name;
    private String symbol;
}
