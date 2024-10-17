package com.example.av1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Category {

    private String Name;
    private String Description;

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }
}
