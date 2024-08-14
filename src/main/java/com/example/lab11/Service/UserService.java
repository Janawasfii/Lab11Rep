package com.example.lab11.Service;

import com.example.lab11.APIResponse.APIException;
import com.example.lab11.Model.Post;
import com.example.lab11.Model.User;
import com.example.lab11.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class UserService {
    private final UserRepository userRepository;
    public List<User> getUser(){

        return userRepository.findAll();
    }
    public void addUser(User user){
        userRepository.save(user);
    }

    public void updateUser(Integer userId ,User user){
        User u = userRepository.findUserByUserId(userId);;
        if(u == null){
            throw new APIException("User not found");
        }
        u.setUsername(user.getUsername());
        u.setEmail(user.getEmail());
        u.setPassword(user.getPassword());
        u.setRegistration_date(user.getRegistration_date());
        userRepository.save(u);
    }

    public void deleteUser(Integer userId){
        User u = userRepository.findUserByUserId(userId);
        if(u == null){
            throw new APIException("User not found");
        }
        userRepository.delete(u);
    }

    public User findByEmailAndPassword(String email, String password){
        User u = userRepository.findByEmailAndPassword(email, password);
        if(u == null){
            throw new APIException("User not found");
        }
        return u;
    }

    public User findUserByUsername(String username){
        User u = userRepository.findUserByUsername(username);
        if(u == null){
            throw new APIException("User not found");
        }
        return u;
    }

}
