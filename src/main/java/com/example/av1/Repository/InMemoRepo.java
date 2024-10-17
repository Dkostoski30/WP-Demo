package com.example.av1.Repository;

import com.example.av1.data.DataHolder;
import com.example.av1.model.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoRepo {
   public List<Category> all(){
       return DataHolder.categories;
   }

   public Category save(Category category){

       delete(category.getName());
       DataHolder.categories.add(category);
       return category;
   }

   public Optional<Category> findByName(String name){

       return DataHolder.categories
               .stream()
                .filter(c -> c.getName().equals(name))
                 .findFirst();
   }

   public List<Category> search(String text){

       return DataHolder.categories
               .stream()
               .filter(c -> c.getName().contains(text) || c.getDescription().contains(text))
               .toList();
   }

   public void delete(String name){
       DataHolder.categories.removeIf(item -> item.getName().equals(name));
   }
}
