package com.codecool.codecoolquiz.repository;

import com.codecool.codecoolquiz.model.Category;
import com.codecool.codecoolquiz.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    List<Question> findByCategoryAndType(Category questionCategory, String type);

    List<Question> findByCategory(Category questionCategory);

    List<Question> findByType(String type);
}
