package com.codecool.codecoolquiz.repository;

import com.codecool.codecoolquiz.model.Category;
import com.codecool.codecoolquiz.model.CustomQuiz;
import com.codecool.codecoolquiz.model.Question;
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
class CustomQuizRepositoryTest {

    @Autowired
    CustomQuizRepository customQuizRepository;

    @Test
    public void testAddNewCustomQuiz() {
        CustomQuiz customQuiz = CustomQuiz.builder()
                .name("CustomQuiz1")
                .build();

        customQuizRepository.save(customQuiz);
        List<CustomQuiz> customquizzes = customQuizRepository.findAll();
        assertThat(customquizzes).hasSize(1);
    }

    @Test
    public void customQuizHasQuestions() {
        CustomQuiz customQuiz = CustomQuiz.builder()
                .name("CustomQuiz1")
                .question(Question.builder().question("Question1").build())
                .question(Question.builder().question("Question2").build())
                .build();
        customQuizRepository.save(customQuiz);
        assertThat(customQuizRepository.getOne(customQuiz.getId()).getQuestions().size() == 2);
    }

    @Test
    public void findAllCustomQuizzes() {
        CustomQuiz customQuiz = CustomQuiz.builder()
                .name("CustomQuiz1")
                .build();
        CustomQuiz customQuiz2 = CustomQuiz.builder()
                .name("CustomQuiz2")
                .build();
        CustomQuiz customQuiz3 = CustomQuiz.builder()
                .name("CustomQuiz3")
                .build();
        customQuizRepository.saveAll(Lists.newArrayList(customQuiz, customQuiz2, customQuiz3));
        List<CustomQuiz> customQuizzes = customQuizRepository.findAll();
        assertThat(customQuizzes).containsExactlyInAnyOrder(customQuiz, customQuiz2, customQuiz3);
    }

    @Test
    public void getOneCustomQuizById() {
        CustomQuiz customQuiz = CustomQuiz.builder()
                .name("CustomQuiz1")
                .build();
        customQuizRepository.save(customQuiz);
        assertThat(customQuizRepository.getOne(customQuiz.getId()).equals(customQuiz));
    }


}