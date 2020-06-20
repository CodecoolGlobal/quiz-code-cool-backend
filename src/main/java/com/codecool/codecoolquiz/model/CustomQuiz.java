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
    @NotNull
    private Set<Question> questions = new HashSet<>();

    @ManyToOne(optional = false)
    private AppUser appUser;

    @NotNull
    private LocalDate creationDate;

}
