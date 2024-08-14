package com.example.lab11.Controller;

import com.example.lab11.Model.User;
import com.example.lab11.Service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")

public class UserController {
    private final UserService userService;
    @GetMapping("/get")
    public ResponseEntity getUser(){
        return ResponseEntity.status(200).body(userService.getUser());
    }
    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body("Successfully added user");
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity updateUser(@PathVariable Integer userId, @Valid @RequestBody User user, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.updateUser(userId, user);
        return ResponseEntity.status(200).body("Successfully updated user");
    }


    @DeleteMapping("/delete/{userId}")
    public ResponseEntity deleteUser(@PathVariable Integer userId){
        userService.deleteUser(userId);
        return ResponseEntity.status(200).body("Successfully deleted user");
    }

    @GetMapping("/finds/{email}/{password}")
    public ResponseEntity findUserByUsernameAndPassword(@PathVariable String email, @PathVariable String password){
        User user = userService.findByEmailAndPassword(email,password);
        return ResponseEntity.status(200).body(user);
    }

    @GetMapping("/search/{username}")
    public ResponseEntity findUserByUsername(@PathVariable String username){
        User u = userService.findUserByUsername(username);
        return ResponseEntity.status(200).body(u);
    }







}
