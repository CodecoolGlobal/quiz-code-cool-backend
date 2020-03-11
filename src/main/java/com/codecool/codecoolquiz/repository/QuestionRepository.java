package com.codecool.codecoolquiz.repository;

import com.codecool.codecoolquiz.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface QuestionRepository extends JpaRepository<Question, Integer>, JpaSpecificationExecutor {
}
