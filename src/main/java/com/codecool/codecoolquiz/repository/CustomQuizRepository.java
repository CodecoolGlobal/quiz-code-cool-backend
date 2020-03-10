package com.codecool.codecoolquiz.repository;

import com.codecool.codecoolquiz.model.CustomQuiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomQuizRepository extends JpaRepository<CustomQuiz, Integer> {
}
