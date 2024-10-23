package com.example.av1.model.exceptions;

public class InvalidArgumentException extends RuntimeException{
    public InvalidArgumentException() {
        super("Please enter username and password");
    }
}
