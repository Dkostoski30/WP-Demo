package com.example.av1.Repository;

import com.example.av1.data.DataHolder;
import com.example.av1.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryUserRepository {
    public List<User> all(){
        return DataHolder.users;
    };

    public Optional<User> find(String username){
        return all().stream().filter(user -> user.getUsername().equals(username)).findFirst();
    }
    public Optional<User> find(String username, String password){
        return all().stream().filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password)).findFirst();
    }
    public User save(User user){
        all().removeIf(user1 -> user1.getUsername().equals(user.getUsername()));
        all().add(user);
        return user;
    }

    public void delete(String username){
        all().removeIf(user -> user.getUsername().equals(username));
    }
    public void delete(User user){
        all().removeIf(user1 -> user1.getUsername().equals(user.getUsername()));
    }
    public void delete(String username, String password){
        all().removeIf(user -> user.getUsername().equals(username) && user.getPassword().equals(password));
    }
}
