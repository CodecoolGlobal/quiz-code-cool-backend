package com.codecool.codecoolquiz.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@JsonIgnoreProperties({"questions"})
public class Category {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    @NotNull
    private String name;

    @Singular
    @OneToMany(mappedBy = "category")
    @EqualsAndHashCode.Exclude
    private Set<Question> questions;
}
