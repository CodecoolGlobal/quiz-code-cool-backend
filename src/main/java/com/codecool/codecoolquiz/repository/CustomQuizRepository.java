package com.codecool.codecoolquiz.repository;

import com.codecool.codecoolquiz.model.CustomQuiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomQuizRepository extends JpaRepository<CustomQuiz, Integer> {
    List<CustomQuiz> findAllByAppUserId(int id);
}
