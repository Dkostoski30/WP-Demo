package com.example.av1.Service.implementation;

import com.example.av1.Repository.InMemoRepo;
import com.example.av1.Service.CategoryService;
import com.example.av1.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImplementation implements CategoryService {
    private final InMemoRepo repository;

    public CategoryServiceImplementation(InMemoRepo repository) {
        this.repository = repository;
    }

    @Override
    public List<Category> listCategories() {
        return repository.all();
    }

    @Override
    public Category create(String name, String description) {
        if (name.isEmpty() || description.isEmpty()){
            throw new IllegalArgumentException();
        }
        return repository.save(new Category(name, description));
    }

    @Override
    public Category update(String name, String description) {
        if (name.isEmpty() || description.isEmpty()){
            throw new IllegalArgumentException();
        }
        return repository.save(new Category(name, description));
    }

    @Override
    public void delete(String name) {
        repository.delete(name);
    }

    @Override
    public List<Category> search(String text) {
        return repository.search(text);
    }
}
