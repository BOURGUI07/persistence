package com.example.CH04.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Filename {
    @Column(nullable = false)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filename filename = (Filename) o;
        return Objects.equals(name, filename.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
