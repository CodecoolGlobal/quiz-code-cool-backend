package com.codecool.codecoolquiz.repository;

import com.codecool.codecoolquiz.model.*;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.util.collections.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
class CustomQuizRepositoryTest {

    @Autowired
    private CustomQuizRepository customQuizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    AppUser appUser;
    Question question1;
    Question question2;

    @BeforeEach
    public void setup() {
        appUser = AppUser.builder().role("ADMIN").email("vvv@gd.de").password("valami").username("valaki").registrationDate(LocalDate.now()).build();
        appUserRepository.save(appUser);
        Category category1 = Category.builder()
                .name("category test")
                .build();

        question1 = Question.builder()
                .question("does it persist with category?")
                .correctAnswer("Yes")
                .incorrectAnswer("No")
                .creationDate(LocalDate.now())
                .category(category1)
                .type(Type.BOOLEAN)
                .appUser(appUser)
                .build();

        question2 = Question.builder()
                .question("new q")
                .category(category1)
                .correctAnswer("something")
                .incorrectAnswers(Arrays.asList("head", "more", "tail"))
                .type(Type.MULTIPLE)
                .creationDate(LocalDate.now())
                .appUser(appUser)
                .build();
        questionRepository.saveAll(Arrays.asList(question2, question1));

    }

    @Test
    public void testAddNewCustomQuiz() {

        CustomQuiz customQuiz = CustomQuiz.builder()
                .name("CustomQuiz1")
                .appUser(appUserRepository.findByUsername("valaki").orElse(null))
                .creationDate(LocalDate.now())
                .question(question1)
                .build();

        customQuizRepository.save(customQuiz);
        List<CustomQuiz> customQuizzes = customQuizRepository.findAll();
        assertThat(customQuizzes).hasSize(1);
    }

    @Test
    public void testAppUserCantBeNull() {
        CustomQuiz customQuiz = CustomQuiz.builder()
                .name("CustomQuiz1")
                .creationDate(LocalDate.now())
                .question(question1)
                .build();

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> customQuizRepository.saveAndFlush(customQuiz));
    }

    @Test
    public void customQuizHasQuestions() {
        CustomQuiz customQuiz = CustomQuiz.builder()
                .name("CustomQuiz1")
                .question(question1)
                .question(question2)
                .creationDate(LocalDate.now())
                .appUser(appUser)
                .build();
        customQuizRepository.saveAndFlush(customQuiz);
        assertThat(customQuizRepository.getOne(customQuiz.getId()).getQuestions().size() == 2);
    }

    @Test
    public void findAllCustomQuizzes() {
        CustomQuiz customQuiz = CustomQuiz.builder()
                .name("CustomQuiz1")
                .appUser(appUser)
                .question(question1)
                .creationDate(LocalDate.now())
                .build();
        CustomQuiz customQuiz2 = CustomQuiz.builder()
                .name("CustomQuiz2")
                .appUser(appUser)
                .question(question2)
                .creationDate(LocalDate.now())
                .build();
        CustomQuiz customQuiz3 = CustomQuiz.builder()
                .name("CustomQuiz3")
                .appUser(appUser)
                .questions(Arrays.asList(question1, question2))
                .creationDate(LocalDate.now())
                .build();
        customQuizRepository.saveAll(Lists.newArrayList(customQuiz, customQuiz2, customQuiz3));
        List<CustomQuiz> customQuizzes = customQuizRepository.findAll();
        assertThat(customQuizzes).containsExactlyInAnyOrder(customQuiz, customQuiz2, customQuiz3);
    }

    @Test
    public void getOneCustomQuizById() {
        CustomQuiz customQuiz = CustomQuiz.builder()
                .name("CustomQuiz3")
                .appUser(appUser)
                .questions(Arrays.asList(question1, question2))
                .creationDate(LocalDate.now())
                .build();
        customQuizRepository.saveAndFlush(customQuiz);
        assertThat(customQuizRepository.getOne(customQuiz.getId()).equals(customQuiz));
    }

    @Test
    public void deleteCustomQuiz() {

        CustomQuiz customQuiz = CustomQuiz.builder()
                .name("CustomQuiz1")
                .questions(Sets.newSet(question1, question2))
                .creationDate(LocalDate.now())
                .appUser(appUserRepository.findByUsername("valaki").orElse(null))
                .build();
        customQuizRepository.save(customQuiz);
        customQuizRepository.delete(customQuiz);
        assertThat(customQuizRepository.findAll().size() == 0);
        assertThat(questionRepository.findAll().size() == 2);
    }

    @Test
    public void saveUniqueFieldInCustomQuiz() {
        CustomQuiz customQuiz1 = CustomQuiz.builder()
                .name("quiz test")
                .questions(Sets.newSet(question2))
                .creationDate(LocalDate.now())
                .appUser(appUser)
                .build();

        customQuizRepository.save(customQuiz1);

        CustomQuiz customQuiz2 = CustomQuiz.builder()
                .name("quiz test")
                .questions(Sets.newSet(question1, question2))
                .creationDate(LocalDate.now())
                .appUser(appUser)
                .build();

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> customQuizRepository.saveAndFlush(customQuiz2));
    }

}