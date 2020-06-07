package com.codecool.codecoolquiz.repository;

import com.codecool.codecoolquiz.model.Question;
import com.codecool.codecoolquiz.model.Type;
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

    @Test
    public void testFindAllTypes() {
        Question question1 = Question.builder()
                .question("Question1?")
                .incorrectAnswer("fdf")
                .correctAnswer("Correct")
                .type(Type.BOOLEAN)
                .build();
        Question question2 = Question.builder()
                .question("Question2?")
                .incorrectAnswers(Lists.newArrayList("fddddf", "fdfd", "fdfdf"))
                .type(Type.MULTIPLE)
                .build();
    questionRepository.saveAll(Lists.newArrayList(question1, question2));
    List<Type> types = questionRepository.findTypes();
    assertThat(types).containsExactlyInAnyOrder(Type.MULTIPLE, Type.BOOLEAN);

    }

    @Test
    public void saveQuestionsWithTheSameName() {
        Question question1 = Question.builder()
                .question("question test")
                .build();

        questionRepository.save(question1);

        Question question2 = Question.builder()
                .question("question test")
                .build();

        questionRepository.save(question2);

        List<Question> questionList = questionRepository.findAll();
        assertThat(questionList).hasSize(2);
    }

    @Test
    public void findTypesInQuestion() {
        Question question1 = Question.builder()
                .question("question1")
                .type(Type.MULTIPLE)
                .build();

        Question question2 = Question.builder()
                .question("question2")
                .type(Type.BOOLEAN)
                .build();

        Question question3 = Question.builder()
                .question("question3")
                .type(Type.BOOLEAN)
                .build();

        Question question4 = Question.builder()
                .question("question4")
                .type(Type.MULTIPLE)
                .build();

        questionRepository.saveAll(Lists.newArrayList(question1, question2, question3, question4));

        List<Type> allTypes = questionRepository.findTypes();

        assertThat(allTypes)
                .hasSize(2)
                .containsOnlyOnce(Type.MULTIPLE, Type.BOOLEAN);
    }
}