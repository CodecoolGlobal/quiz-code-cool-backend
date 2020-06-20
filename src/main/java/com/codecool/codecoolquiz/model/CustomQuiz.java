package com.codecool.codecoolquiz.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CustomQuiz {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    @NotNull
    private String name;

    @Singular
    @ManyToMany
    @EqualsAndHashCode.Exclude
    private Set<Question> questions = new HashSet<>();

    @ManyToOne
    @NotNull
    private AppUser appUser;

    @NotNull
    private LocalDate creationDate;

}
