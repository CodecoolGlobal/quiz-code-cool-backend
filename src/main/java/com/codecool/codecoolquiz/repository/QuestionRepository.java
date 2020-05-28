package com.codecool.codecoolquiz.repository;

import com.codecool.codecoolquiz.model.Question;
import com.codecool.codecoolquiz.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Integer>, JpaSpecificationExecutor {

    @Query("SELECT distinct q.type from Question q")
    List<Type> findTypes();

}
