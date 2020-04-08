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

    @Enumerated(EnumType.STRING)
    private Type type;

    @Column( nullable = false )
    private String question;

    private String correctAnswer;

    @ElementCollection
    @Singular
    private List<String> incorrectAnswers;

    private LocalDate creationDate;

    private LocalDate validationDate;

    private boolean isValidated;

    @Singular
    @ManyToMany(mappedBy = "questions")
    @EqualsAndHashCode.Exclude
    private List<CustomQuiz> quizzes;

    public void addQuiz(CustomQuiz quiz)  {
        this.quizzes.add(quiz);
    }

    public void removeQuiz(CustomQuiz quiz)  {
        this.quizzes.remove(quiz);
    }

    @Override
    public String toString() {
        return
            "{" +
                "id=" + this.id + ", " +
                "question=" + this.question + "," +
                "creationDate=" + this.creationDate +
            "}";
    }
}
