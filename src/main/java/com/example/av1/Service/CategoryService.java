package com.example.av1.Service;

import com.example.av1.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<Category> listCategories();

    Category create(String name, String description);
    Category update(String name, String description);
    void delete(String name);

    List<Category> search(String text);
}
