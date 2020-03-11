package com.codecool.codecoolquiz;

import com.codecool.codecoolquiz.model.Category;
import com.codecool.codecoolquiz.model.CustomQuiz;
import com.codecool.codecoolquiz.model.Question;
import com.codecool.codecoolquiz.repository.CategoryRepository;
import com.codecool.codecoolquiz.repository.CustomQuizRepository;
import com.codecool.codecoolquiz.repository.QuestionRepository;
import com.codecool.codecoolquiz.service.Initializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
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

    @Test
    public void saveOneCategory() {
        Category category = Category.builder()
                .name("category test")
                .build();

        categoryRepository.save(category);

        List<Category> categoryList = categoryRepository.findAll();
        assertThat(categoryList).hasSize(1);
    }

    @Test
    public void saveOneQuestion() {
        Question question = Question.builder()
                .question("Does it work?")
                .type("test")
                .correctAnswer("saves")
                .incorrectAnswer("does not save")
                .incorrectAnswer("saves incorrectly")
                .build();

        questionRepository.save(question);

        List<Question> questionList = questionRepository.findAll();
        assertThat(questionList).hasSize(1);

    }

    @Test
    public void saveUniqueFieldTwiceInCategory() {
        Category category1 = Category.builder()
                .name("category test")
                .build();

        categoryRepository.save(category1);

        Category category2 = Category.builder()
                .name("category test")
                .build();

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            categoryRepository.saveAndFlush(category2);
        });
    }

    @Test
    public void saveUniqueFieldTwiceInQuestion() {
        Question question1 = Question.builder()
                .question("question test")
                .build();

        questionRepository.save(question1);

        Question question2 = Question.builder()
                .question("question test")
                .build();

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            questionRepository.save(question2);
        });
    }

    @Test
    public void saveUniqueFieldInCustomQuiz() {
        CustomQuiz customQuiz1 = CustomQuiz.builder()
                .name("quiz test")
                .build();

        customQuizRepository.save(customQuiz1);

        CustomQuiz customQuiz2 = CustomQuiz.builder()
                .name("quiz test")
                .build();

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            customQuizRepository.saveAndFlush(customQuiz2);
        });
    }

    @Test
    public void persistQuestionWithCategory() {
        Category category = Category.builder()
                .name("category test")
                .build();

        Question question = Question.builder()
                .question("does it persist with category?")
                .category(category)
                .build();

        questionRepository.save(question);

        List<Category> categoryList = categoryRepository.findAll();
        assertThat(categoryList).hasSize(1);
    }

}
