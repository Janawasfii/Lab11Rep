package com.example.lab11.Controller;

import com.example.lab11.Model.Comment;
import com.example.lab11.Model.Post;
import com.example.lab11.Service.CommentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/comment")

public class CommentController {
    private final CommentService commentService;
@GetMapping("/get")
    public ResponseEntity getComment(){
        return ResponseEntity.status(200).body(commentService.getComment());
    }
    @PostMapping("/add")
    public ResponseEntity addComment(@Valid @RequestBody Comment comment, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        commentService.addComment(comment);
        return ResponseEntity.status(200).body("Successfully added comment");
    }
    @PutMapping("/update/{commentId}")
    public ResponseEntity updateUser(@PathVariable Integer commentId, @Valid @RequestBody Comment comment, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        commentService.updateComment(commentId, comment);
        return ResponseEntity.status(200).body("Successfully updated comment");
    }


    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity deleteComment(@PathVariable Integer commentId){
        commentService.deleteComment(commentId);
        return ResponseEntity.status(200).body("Successfully deleted comment");
    }

    @GetMapping("/find-comments/{postId}")
    public ResponseEntity findCommentByPostId(@PathVariable Integer postId){
    List<Comment> c = commentService.findCommentByPostId(postId);
    return ResponseEntity.status(200).body(c);
    }

    @GetMapping("/contain/{content}")
    public ResponseEntity findCommentContaining(@PathVariable String content){
    List<Comment> c = commentService.findCommentContaining(content);
    return ResponseEntity.status(200).body(c);
    }

}
