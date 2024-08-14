package com.example.lab11.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name="Comment")

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;


    @NotNull(message="User ID must not be null")
    @Column(columnDefinition = "int not null")
    private Integer userId;


    @NotNull(message="Post ID must not be null")
    @Column(columnDefinition = "int not null")
    private Integer postId;

    @NotEmpty(message = "Content must not be empty")
    @Column(columnDefinition = "varchar(500) not null ")
    private String content;


//    @NotNull(message = "Comment Date must not be null")
//    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(columnDefinition = "DATETIME default (CURRENT_TIMESTAMP)")
    private LocalDate commentDate;









}
