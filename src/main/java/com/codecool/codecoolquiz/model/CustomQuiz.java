package com.codecool.codecoolquiz.model;

import lombok.*;

import javax.persistence.*;
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

    @Column(nullable = false, unique = true)
    private String name;

    @Singular
    @ManyToMany
    @EqualsAndHashCode.Exclude
    private Set<Question> questions = new HashSet<>();

    @ManyToOne
    private AppUser appUser;

    private LocalDate creationDate;

}
