package com.example.lab11.Repository;

import com.example.lab11.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Comment findCommentByCommentId(Integer commentId);

    List<Comment> findCommentByPostId(Integer postId);

    @Query("select c from Comment c where c.content like?1")
    List<Comment> findCommentContaining(String content);




}
