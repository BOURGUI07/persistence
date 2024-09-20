package com.example.CH04.domain;

import jakarta.persistence.*;
import lombok.Getter;
//table-per-class
@MappedSuperclass
@Getter
public abstract class BillingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String owner;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
