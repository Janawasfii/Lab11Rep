package com.example.lab11.Service;

import com.example.lab11.APIResponse.APIException;
import com.example.lab11.Model.Category;
import com.example.lab11.Model.Comment;
import com.example.lab11.Model.Post;
import com.example.lab11.Model.User;
import com.example.lab11.Repository.CategoryRepository;
import com.example.lab11.Repository.CommentRepository;
import com.example.lab11.Repository.PostRepository;
import com.example.lab11.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor

public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public List<Comment> getComment(){
        return commentRepository.findAll();
    }

    public void addComment(Comment comment){
        User user = userRepository.findUserByUserId(comment.getUserId());
        Post post = postRepository.findPostByPostId(comment.getPostId());
        if(user == null || post == null){
            throw new APIException("User or Post not found");
        }
        comment.setCommentDate(LocalDate.now());
        commentRepository.save(comment);
    }
    public void updateComment(Integer commentId ,Comment comment){
        Comment c = commentRepository.findCommentByCommentId(commentId);;
        if(c == null){
            throw new APIException(" Comment not found");
        }
        c.setContent(comment.getContent());
        commentRepository.save(c);
    }
    public void deleteComment(Integer commentId){
        Comment c = commentRepository.findCommentByCommentId(commentId);
        if(c == null){
            throw new APIException("Comment not found");
        }
        commentRepository.delete(c);
    }
    public List<Comment> findCommentByPostId(Integer postId){
        List<Comment> comments = commentRepository.findCommentByPostId(postId);
        if(comments.isEmpty()){
            throw new APIException("Comment not found");
        }
        return comments;
    }

    public List<Comment> findCommentContaining(String content){
        List<Comment> comments = commentRepository.findCommentContaining(content);
        if(comments.isEmpty()){
            throw new APIException("Comment not found");
        }
        return comments;
    }

}
