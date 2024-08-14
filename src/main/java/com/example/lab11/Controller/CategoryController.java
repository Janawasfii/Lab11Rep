package com.example.lab11.Controller;

import com.example.lab11.Model.Category;
import com.example.lab11.Model.User;
import com.example.lab11.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("/get")
    public ResponseEntity getCategory(){
        return ResponseEntity.status(200).body(categoryService.getCategory());
    }
    @PostMapping("/add")
    public ResponseEntity addCategory(@Valid @RequestBody Category category, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body("Successfully added Category");
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity updateUser(@PathVariable Integer categoryId, @Valid @RequestBody Category category, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        categoryService.updateCategory(categoryId, category);
        return ResponseEntity.status(200).body("Successfully updated category");
    }


    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity deleteUser(@PathVariable Integer categoryId){
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.status(200).body("Successfully deleted category");
    }

    @GetMapping("/get-category/{name}")
    public ResponseEntity findCategoryByName(@PathVariable String name){
        Category c= categoryService.findCategoryByName(name);
        return ResponseEntity.status(200).body(c);
    }


}
