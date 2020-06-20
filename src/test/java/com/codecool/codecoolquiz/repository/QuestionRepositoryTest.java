package com.codecool.codecoolquiz.repository;

import com.codecool.codecoolquiz.model.AppUser;
import com.codecool.codecoolquiz.model.Category;
import com.codecool.codecoolquiz.model.Question;
import com.codecool.codecoolquiz.model.Type;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
class QuestionRepositoryTest {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    AppUser appUser;

    @BeforeEach
    public void setup() {
        appUser = AppUser.builder().role("ADMIN").email("vvv@gd.de").password("valami").username("valaki").registrationDate(LocalDate.now()).build();
        appUserRepository.save(appUser);
    }

    @Test
    public void testAddNewQuestion() {
        Question question = Question.builder()
                .question("Question?")
                .incorrectAnswers(Lists.newArrayList("2", "1", "3"))
                .correctAnswer("4")
                .type(Type.MULTIPLE)
                .appUser(appUserRepository.findByUsername("valaki").orElse(null))
                .category(Category.builder().name("Web").build())
                .creationDate(LocalDate.now())
                .isValidated(false)
                .build();
        questionRepository.save(question);
        List<Question> questions = questionRepository.findAll();
        assertThat(questions).hasSize(1);
    }

    @Test
    public void testFindAllTypes() {
        Question question1 = Question.builder()
                .question("Question1?")
                .incorrectAnswer("v")
                .correctAnswer("Correct")
                .type(Type.BOOLEAN)
                .appUser(appUserRepository.findById(1).orElse(null))
                .category(Category.builder().name("Web").build())
                .creationDate(LocalDate.now())
                .isValidated(false)
                .build();
        Question question2 = Question.builder()
                .question("Question?")
                .incorrectAnswers(Lists.newArrayList("2", "1", "3"))
                .correctAnswer("4")
                .type(Type.MULTIPLE)
                .appUser(appUserRepository.findByUsername("valaki").orElse(null))
                .category(Category.builder().name("SQL").build())
                .creationDate(LocalDate.now())
                .isValidated(false)
                .build();
    questionRepository.saveAll(Lists.newArrayList(question1, question2));
    List<Type> types = questionRepository.findTypes();
    assertThat(types).containsExactlyInAnyOrder(Type.MULTIPLE, Type.BOOLEAN);

    }

    @Test
    public void saveQuestionsWithTheSameName() {
        Question question1 = Question.builder()
                .question("Question?")
                .incorrectAnswer("v")
                .correctAnswer("Correct")
                .type(Type.BOOLEAN)
                .appUser(appUserRepository.findByUsername("valaki").orElse(null))
                .category(Category.builder().name("Web").build())
                .creationDate(LocalDate.now())
                .isValidated(false)
                .build();
        Question question2 = Question.builder()
                .question("Question?")
                .incorrectAnswers(Lists.newArrayList("2", "1", "3"))
                .correctAnswer("4")
                .type(Type.MULTIPLE)
                .appUser(appUserRepository.findByUsername("valaki").orElse(null))
                .category(Category.builder().name("SQL").build())
                .creationDate(LocalDate.now())
                .isValidated(false)
                .build();
        questionRepository.save(question1);
        questionRepository.save(question2);

        List<Question> questionList = questionRepository.findAll();
        assertThat(questionList).hasSize(2);
    }
}