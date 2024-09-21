package com.example.CH04.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

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

    @ElementCollection
    @CollectionTable(
            name="CONTACT",
            joinColumns = @JoinColumn(name="USER_ID")
    )
    @Column(name="NAME",nullable=false)
    private Set<String> contacts = new HashSet<>();

}
