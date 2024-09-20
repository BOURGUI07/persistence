package com.example.CH04.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class City {
    @Embedded
    private Zipcode zipcode;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String country;
}
