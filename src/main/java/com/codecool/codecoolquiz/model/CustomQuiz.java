package com.codecool.codecoolquiz.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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
    private List<Question> questions;

    @ManyToOne
    private AppUser appUser;

    public void removeQuestion(Question question)  {
        this.questions.remove(question);
    }
}
