package com.example.av1.Service.implementation;

import com.example.av1.Repository.InMemoryUserRepository;
import com.example.av1.Service.AuthenticationService;
import com.example.av1.model.User;
import com.example.av1.model.exceptions.InvalidArgumentException;
import com.example.av1.model.exceptions.InvalidUserCredentialsException;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;

@Service
public class AuthServiceImplementation implements AuthenticationService {
    private final InMemoryUserRepository userRepository;

    public AuthServiceImplementation(InMemoryUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if(username.isEmpty() || password.isEmpty()){
            throw new InvalidArgumentException();
        }
        return userRepository.find(username, password).orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname) {
        // Check if any field is empty or null
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username is required.");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password is required.");
        }
        if (repeatPassword == null || repeatPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("Please confirm your password.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name is required.");
        }
        if (surname == null || surname.trim().isEmpty()) {
            throw new IllegalArgumentException("Surname is required.");
        }

        // Check if passwords match
        if (!password.equals(repeatPassword)) {
            throw new IllegalArgumentException("Passwords do not match.");
        }

        // Check password complexity (example: at least 8 characters)
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long.");
        }

        if(userRepository.find(username).isPresent()){
            throw new IllegalArgumentException("User already exists!");
        }

        User userNew = new User(username, password, name, surname);
        return userRepository.save(userNew);
    }
}
