package com.codecool.codecoolquiz.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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

    private String name;

    @Singular
    @ManyToMany
    @EqualsAndHashCode.Exclude
    private List<Question> questions;
}
