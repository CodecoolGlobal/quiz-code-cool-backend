package com.codecool.codecoolquiz.repository;

import com.codecool.codecoolquiz.model.Category;
import com.codecool.codecoolquiz.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer>, JpaSpecificationExecutor {
}
