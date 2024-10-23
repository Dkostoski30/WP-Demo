package com.example.av1.data;

import com.example.av1.model.Category;
import com.example.av1.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class DataHolder {
    public static List<Category> categories = null;
    public static List<User> users = null;
    @PostConstruct
    public void init(){
        categories = new ArrayList<>();
        users = new ArrayList<>();

        categories.add(new Category("Movies", "Movies category"));
        categories.add(new Category("Toys", "Toys category"));
        categories.add(new Category("Books", "Books category"));

        users.add(new User("daniel.kostoski", "dk", "Daniel", "Kostoski"));
        users.add(new User("acko.dujkovic", "ad", "Acko", "Dujkovic"));
        users.add(new User("zoran.elvis", "ze", "Zoran", "Elvis"));
    }
}
