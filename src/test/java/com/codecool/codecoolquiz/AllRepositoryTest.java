package com.codecool.codecoolquiz;

import com.codecool.codecoolquiz.model.CustomQuiz;
import com.codecool.codecoolquiz.repository.CategoryRepository;
import com.codecool.codecoolquiz.repository.CustomQuizRepository;
import com.codecool.codecoolquiz.repository.QuestionRepository;
import com.codecool.codecoolquiz.service.Initializer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class AllRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CustomQuizRepository customQuizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    public void saveOneCustomQuiz() {
        CustomQuiz quiz = CustomQuiz.builder()
                .name("quiz test")
                .build();

        customQuizRepository.save(quiz);

        List<CustomQuiz> quizList = customQuizRepository.findAll();
        assertThat(quizList).hasSize(1);

    }

}
