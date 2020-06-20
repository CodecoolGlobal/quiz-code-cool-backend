package com.codecool.codecoolquiz.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
    @EqualsAndHashCode.Exclude
    private Category category;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Type type;

    @NotNull
    private String question;

    @NotNull
    private String correctAnswer;

    @ElementCollection
    @Singular
    @NotNull
    private List<String> incorrectAnswers;

    @NotNull
    private LocalDate creationDate;

    private LocalDate validationDate;

    @NotNull
    private boolean isValidated;

    @Singular
    @ManyToMany(mappedBy = "questions", cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    private Set<CustomQuiz> quizzes = new HashSet<>();

    @ManyToOne(optional = false)
    private AppUser appUser;

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
