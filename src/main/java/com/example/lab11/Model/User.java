package com.example.lab11.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name="User")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;


    @NotEmpty(message = "Username must not be empty")
    @Size(min=5)
    @Column(columnDefinition = "varchar(20) not null unique ")
    private String username;


    @NotEmpty(message = "Password should not be empty")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
    @Column(columnDefinition = "varchar(20) not null")
    private String password;

    @Email
    @NotEmpty(message="Email must not be empty")
    @Column(columnDefinition = "varchar(30) unique not null")
    private String email;


    @NotNull(message = "Registration Date must not be null")
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(columnDefinition = "DATETIME default (CURRENT_TIMESTAMP)")
    private LocalDate registration_date;

}
