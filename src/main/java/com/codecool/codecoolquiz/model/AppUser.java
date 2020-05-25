package com.codecool.codecoolquiz.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUser {

    @Id
    @GeneratedValue
    private Integer id;

    private String username;

    private String password;

    private String email;

    private LocalDate registrationDate;

    @ElementCollection
    @Singular
    private List<String> roles;

    @OneToMany(mappedBy = "appUser")
    @EqualsAndHashCode.Exclude
    private List<Question> questions;

    @OneToMany(mappedBy = "appUser")
    @EqualsAndHashCode.Exclude
    private List<CustomQuiz> customQuizzes;


}
