package com.codecool.codecoolquiz.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@JsonIgnoreProperties({"quizzes"})
public class Question {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    private Category category;

    private String type;

    private String question;

    private String correctAnswer;

    @ElementCollection
    @Singular
    private List<String> incorrectAnswers;

    private LocalDate creationDate;

    private LocalDate validationDate;

    private boolean isValidated;

    @Singular
    @ManyToMany
    @EqualsAndHashCode.Exclude
    private List<CustomQuiz> quizzes;

}
