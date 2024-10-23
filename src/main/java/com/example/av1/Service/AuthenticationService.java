package com.example.av1.Service;

import com.example.av1.model.User;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    User login(String username, String password);
    User register(String username, String password, String repeatPassword, String name, String surname);
}
