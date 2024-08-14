package com.example.lab11.Repository;

import com.example.lab11.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository

public interface PostRepository extends JpaRepository<Post, Integer> {
    Post findPostByPostId(Integer postId);

    List<Post> findPostByUserId(Integer userId);

     Post findPostByTitle(String title);

     List<Post> findPostByPublishDateBefore(LocalDate publishDate);

}
