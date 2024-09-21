package com.example.CH04.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.annotations.Parent;

import java.util.Objects;

@Embeddable
public class Image {
    @Column(nullable = false)
    private String fileName;
    private int width;
    private int height;
    @Parent
    private Item item;

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o==null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return width == image.width
                && height == image.height
                && fileName.equals(image.fileName)
                && item.equals(image.item);
    }

    public int hashCode(){
        return Objects.hash(width, height, fileName, item);
    }
}
