package com.example.lab11.Service;

import com.example.lab11.APIResponse.APIException;
import com.example.lab11.Model.Category;
import com.example.lab11.Model.Post;
import com.example.lab11.Model.User;
import com.example.lab11.Repository.CategoryRepository;
import com.example.lab11.Repository.PostRepository;
import com.example.lab11.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor

public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public List<Post> getPost(){
        return postRepository.findAll();
    }
    public void addPost(Post post){
        User user = userRepository.findUserByUserId(post.getUserId());
        Category category = categoryRepository.findByCategoryId(post.getCategoryId());
        if(user == null || category == null){
            throw new APIException("User or Category not found");
        }
        post.setPublishDate(LocalDate.now());
        postRepository.save(post);
    }
    public void updatePost(Integer postId ,Post post){
        Post p = postRepository.findPostByPostId(postId);;
        if(p == null){
            throw new APIException("Post not found");
        }
        p.setTitle(post.getTitle());
        p.setContent(post.getContent());

        postRepository.save(p);
    }

    public void deletePost(Integer postId){
        Post post = postRepository.findPostByPostId(postId);
        if(post == null){
            throw new APIException("Post not found");
        }
        postRepository.delete(post);
    }

    public List<Post> findPostByUserId(Integer userId){
        List<Post> p = postRepository.findPostByUserId(userId);
        if(p == null){
            throw new APIException("Post not found");
        }
        return p;
    }
    public Post findPostByTitle(String title){
        Post p = postRepository.findPostByTitle(title);
        if(p == null){
            throw new APIException("Post not found");
        }
        return p;
    }
    public List<Post> findPostByPublishDateBefore(LocalDate publishDate){
        List<Post> p = postRepository.findPostByPublishDateBefore(publishDate);
        if(p == null){
            throw new APIException("Post not found");
        }
        return p;
    }




}
