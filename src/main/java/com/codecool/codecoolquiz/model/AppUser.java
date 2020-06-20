package com.codecool.codecoolquiz.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @Column(unique = true)
    @NotNull
    private String username;

    @NotNull
    private String password;

    @Column(unique = true)
    @NotNull
    private String email;

    @NotNull
    private LocalDate registrationDate;

    @ElementCollection
    @Singular
    @NotNull
    private List<String> roles;

    @OneToMany(mappedBy = "appUser")
    @EqualsAndHashCode.Exclude
    private List<Question> questions;

    @OneToMany(mappedBy = "appUser")
    @EqualsAndHashCode.Exclude
    private List<CustomQuiz> customQuizzes;
}
