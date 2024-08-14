package com.example.lab11.Service;

import com.example.lab11.APIResponse.APIException;
import com.example.lab11.Model.Category;
import com.example.lab11.Model.User;
import com.example.lab11.Repository.CategoryRepository;
import com.example.lab11.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class CategoryService {
    private final CategoryRepository categoryRepository;
    public List<Category> getCategory(){

        return categoryRepository.findAll();
    }
    public void addCategory(Category category){
        categoryRepository.save(category);
    }

    public void updateCategory(Integer categoryId ,Category category){
        Category c = categoryRepository.findByCategoryId(categoryId);;
        if(c == null){
            throw new APIException("Category not found");
        }
        c.setName(category.getName());
        categoryRepository.save(c);
    }
    public void deleteCategory(Integer categoryId){
        Category c = categoryRepository.findByCategoryId(categoryId);
        if(c == null){
            throw new APIException("User not found");
        }
        categoryRepository.delete(c);
    }
    public Category findCategoryByName(String name){
        Category c = categoryRepository.findCategoryByName(name);
        if(c==null){
            throw new APIException("Category not found");
        }
        return c;
    }

}
