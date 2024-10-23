package com.example.av1.model;

import lombok.Getter;

@Getter

public class User {
    private String username;
    private String password;
    private String name;
    private String surname;
    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname="";
    }

    public User(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }
}
