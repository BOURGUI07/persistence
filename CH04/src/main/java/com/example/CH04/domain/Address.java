package com.example.CH04.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Address {
    @Column(nullable = false)
    private String street;
    @AttributeOverride(
            name="name",column=@Column(name="CITY",nullable=false)
    )
    private City city;

}
