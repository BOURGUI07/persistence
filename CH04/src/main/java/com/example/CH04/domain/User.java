package com.example.CH04.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Data
@Accessors(chain = true)
@Table(name="USERS")
@DynamicInsert
@DynamicUpdate
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
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
