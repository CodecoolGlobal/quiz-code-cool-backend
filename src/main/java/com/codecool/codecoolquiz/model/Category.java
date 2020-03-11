package com.codecool.codecoolquiz.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
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

    @Column(nullable = false, unique = true)
    private String name;

    @Singular
    @OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    private Set<Question> questions;
}
