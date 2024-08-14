package com.example.lab11.Controller;

import com.example.lab11.Model.Post;
import com.example.lab11.Model.User;
import com.example.lab11.Service.PostService;
import com.example.lab11.Service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/post")

public class PostController {
    private final PostService postService;
    @GetMapping("/get")
    public ResponseEntity getPost(){

        return ResponseEntity.status(200).body(postService.getPost());
    }
    @PostMapping("/add")
    public ResponseEntity addPost(@Valid @RequestBody Post post, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        postService.addPost(post);
        return ResponseEntity.status(200).body("Successfully added post");
    }

    @PutMapping("/update/{postId}")
    public ResponseEntity updateUser(@PathVariable Integer postId, @Valid @RequestBody Post post, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        postService.updatePost(postId, post);
        return ResponseEntity.status(200).body("Successfully updated post");
    }


    @DeleteMapping("/delete/{postId}")
    public ResponseEntity deletePost(@PathVariable Integer postId){
        postService.deletePost(postId);
        return ResponseEntity.status(200).body("Successfully deleted post");
    }

    @GetMapping("/get-post/{userId}")
    public ResponseEntity findPostByUserId(@PathVariable Integer userId){
        List<Post> posts = postService.findPostByUserId(userId);
        return ResponseEntity.status(200).body(posts);
    }

    @GetMapping("/find/{title}")
    public ResponseEntity findPostByTitle(@PathVariable String title){
        Post p = postService.findPostByTitle(title);
        return ResponseEntity.status(200).body(p);
    }
    @GetMapping("/gets/{publishDate}")
    public ResponseEntity findPostByPublishDate(@PathVariable LocalDate publishDate){
        List<Post> p = postService.findPostByPublishDateBefore(publishDate);
        return ResponseEntity.status(200).body(p);
    }



}
