package com.example.CH04.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Data
@Accessors(chain = true)
@Table(name="USERS")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private LocalDate registrationDate;
    private String email;
    private int level;
    private boolean active;

    public User(String name,LocalDate registrationDate) {
        this.username = name;
        this.registrationDate = registrationDate;
    }
}
