package com.example.av1.data;

import com.example.av1.model.Category;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class DataHolder {
    public static List<Category> categories = null;

    @PostConstruct
    public void init(){
        categories = new ArrayList<>();
        categories.add(new Category("Movies", "Movies category"));
        categories.add(new Category("Toys", "Toys category"));
        categories.add(new Category("Books", "Books category"));
    }
}
