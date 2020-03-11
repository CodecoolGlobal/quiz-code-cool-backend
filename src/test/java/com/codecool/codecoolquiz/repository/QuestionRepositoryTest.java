package com.codecool.codecoolquiz.repository;

import com.codecool.codecoolquiz.model.Question;
import com.codecool.codecoolquiz.model.Type;
import com.codecool.codecoolquiz.service.CategoryStorage;
import com.codecool.codecoolquiz.service.CustomQuizStorage;
import com.codecool.codecoolquiz.service.Initializer;
import com.codecool.codecoolquiz.service.QuestionStorage;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
class QuestionRepositoryTest {

    @Autowired
    QuestionRepository questionRepository;



    @Test
    public void testAddNewQuestion() {
        Question question = Question.builder()
                .question("Question?")
                .incorrectAnswers(Lists.newArrayList("fdf", "fdfd"))
                .type(Type.BOOLEAN)
                .build();
        questionRepository.save(question);
        List<Question> questions = questionRepository.findAll();
        assertThat(questions).hasSize(1);
    }
}