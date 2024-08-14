package com.example.lab11.Repository;

import com.example.lab11.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUserId(Integer user_id);


  // @Query("select u from User u where u=?1 and u=?2")
    User findByEmailAndPassword(String email, String password);


    @Query("select u from User u where u.username=?1")
    User findUserByUsername(String username);

}
