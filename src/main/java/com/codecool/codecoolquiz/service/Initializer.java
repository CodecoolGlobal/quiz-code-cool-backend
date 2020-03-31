package com.codecool.codecoolquiz.service;

import com.codecool.codecoolquiz.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service
@Profile("production")
public class Initializer {

    @Autowired
    private AppUserStorage appUserStorage;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    CategoryStorage categoryStorage;

    @Autowired
    QuestionStorage questionStorage;

    @Autowired
    CustomQuizStorage customQuizStorage;

    @PostConstruct
    public void loadInitData() throws Exception {
        if (categoryStorage.categoryRepository.count() == 0) {
            loadInitCategories();
        }
        if (questionStorage.questionRepository.count() == 0) {
            loadProgBasicsQuestions();
        }
        if (customQuizStorage.customQuizRepository.count() == 0) {
            loadInitCustomQuizzes();
        }

        if (appUserStorage.appUserRepository.count() == 0) {
            loadUsers();
        }
    }

    private void loadUsers() {
        appUserStorage.add(
                AppUser.builder()
                        .username("admin")
                        .password(encoder.encode("password"))
                        .role("USER")
                        .role("ADMIN")
                        .email("admin@codecool.com")
                        .registrationDate(LocalDate.now())
                        .build()
        );

        appUserStorage.add(
                AppUser.builder()
                        .username("user")
                        .password(encoder.encode("password"))
                        .role("USER")
                        .email("user@codecool.com")
                        .registrationDate(LocalDate.now())
                        .build()
        );
    }

    private void loadInitCategories() {
        categoryStorage.add(Category.builder().name("Python").build());
        categoryStorage.add(Category.builder().name("Java").build());
        categoryStorage.add(Category.builder().name("CSS").build());
        categoryStorage.add(Category.builder().name("SQL").build());
        categoryStorage.add(Category.builder().name("HTML").build());
        categoryStorage.add(Category.builder().name("General").build());
    }

    private void loadProgBasicsQuestions() {
        CustomQuiz progbasics = CustomQuiz.builder()
                .name("ProgBasics module")
                .question(Question.builder()
                        .category(categoryStorage.getById(6))
                        .type(Type.MULTIPLE)
                        .question("Which is not a command line command?")
                        .correctAnswer("body")
                        .incorrectAnswers(Arrays.asList("head", "more", "tail"))
                        .creationDate(LocalDate.now())
                        .validationDate(null)
                        .isValidated(false)
                        .build())
                .question(Question.builder()
                        .category(categoryStorage.getById(6))
                        .type(Type.BOOLEAN)
                        .question("In Linux a shell is a program which protects the system from hacker attacks.")
                        .correctAnswer("false")
                        .incorrectAnswers(Arrays.asList("true"))
                        .creationDate(LocalDate.now())
                        .validationDate(null)
                        .isValidated(false)
                        .build())
                .question(Question.builder()
                        .category(categoryStorage.getById(6))
                        .type(Type.BOOLEAN)
                        .question("Finally statement will be executed when all the error where caught and handled.")
                        .correctAnswer("false")
                        .incorrectAnswers(Arrays.asList("true"))
                        .creationDate(LocalDate.now())
                        .validationDate(null)
                        .isValidated(false)
                        .build())
                .question(Question.builder()
                        .category(categoryStorage.getById(6))
                        .type(Type.BOOLEAN)
                        .question("A remote repository is a common repository that all team members use to exchange their changes.")
                        .correctAnswer("true")
                        .incorrectAnswers(Arrays.asList("false"))
                        .creationDate(LocalDate.now())
                        .validationDate(null)
                        .isValidated(false)
                        .build())
                .question(Question.builder()
                        .category(categoryStorage.getById(6))
                        .type(Type.MULTIPLE)
                        .question("Which is not a git command?")
                        .correctAnswer("delete")
                        .incorrectAnswers(Arrays.asList("init", "reset", "revert"))
                        .creationDate(LocalDate.now())
                        .validationDate(null)
                        .isValidated(false)
                        .build())
                .question(Question.builder()
                        .category(categoryStorage.getById(1))
                        .type(Type.BOOLEAN)
                        .question("The order of the function definitions matters in Python.")
                        .correctAnswer("false")
                        .incorrectAnswers(Arrays.asList("true"))
                        .creationDate(LocalDate.now())
                        .validationDate(null)
                        .isValidated(false)
                        .build())
                .question(Question.builder()
                        .category(categoryStorage.getById(1))
                        .type(Type.BOOLEAN)
                        .question("The sort() method returns a new list.")
                        .correctAnswer("false")
                        .incorrectAnswers(Arrays.asList("true"))
                        .creationDate(LocalDate.now())
                        .validationDate(null)
                        .isValidated(false)
                        .build())
                .build();

        customQuizStorage.add(progbasics);
    }

    private void loadInitQuestions() {
        Question question1 = Question.builder()
                .category(categoryStorage.getById(1))
                .type(Type.MULTIPLE)
                .question("Which company was established on April 1st, 1976 by Steve Jobs, Steve Wozniak and Ronald Wayne?")
                .correctAnswer("Apple")
                .incorrectAnswers(Arrays.asList("Microsoft", "Atari", "Commodore"))
                .creationDate(LocalDate.of(2009, 3, 10))
                .validationDate(null)
                .isValidated(false)
                .build();
        Question question2 = Question.builder()
                .category(categoryStorage.getById(1))
                .type(Type.MULTIPLE)
                .question("In any programming language, what is the most common way to iterate through an array?")
                .correctAnswer("For loops")
                .incorrectAnswers(Arrays.asList("If Statements", "Do-while loops", "While loops"))
                .creationDate(LocalDate.of(2010, 6, 14))
                .validationDate(null)
                .isValidated(false)
                .build();
        Question question3 = Question.builder()
                .category(categoryStorage.getById(2))
                .type(Type.MULTIPLE)
                .question("Which is a valid keyword in java?")
                .correctAnswer("interface")
                .incorrectAnswers(Arrays.asList("string", "Float", "unsigned"))
                .creationDate(LocalDate.of(2017, 9, 20))
                .validationDate(null)
                .isValidated(false)
                .build();
        Question question4 = Question.builder()
                .category(categoryStorage.getById(2))
                .type(Type.MULTIPLE)
                .question("Which is the valid declarations within an interface definition?")
                .correctAnswer("public double methoda();")
                .incorrectAnswers(Arrays.asList("public final double methoda();", "static void methoda(double d1);", "protected void methoda(double d1);"))
                .creationDate(LocalDate.of(2019, 11, 12))
                .validationDate(null)
                .isValidated(false)
                .build();
        Question question5 = Question.builder()
                .category(categoryStorage.getById(2))
                .type(Type.MULTIPLE)
                .question("Which is a valid declarations of a String?")
                .correctAnswer("String s1 = null;")
                .incorrectAnswers(Arrays.asList("String s2 = 'null';", "String s3 = (String) 'abc';", "String s4 = (String) '\\ufeed';"))
                .creationDate(LocalDate.of(2020, 1, 3))
                .validationDate(null)
                .isValidated(false)
                .build();
        Question question6 = Question.builder()
                .category(categoryStorage.getById(2))
                .type(Type.MULTIPLE)
                .question("What is output for − 'search'. find('S') ?")
                .correctAnswer("interface")
                .incorrectAnswers(Arrays.asList("s", "-1", "''"))
                .creationDate(LocalDate.of(2020, 1, 5))
                .validationDate(null)
                .isValidated(false)
                .build();
        Question question7 = Question.builder()
                .category(categoryStorage.getById(1))
                .type(Type.MULTIPLE)
                .question("Which of the following is an invalid variable?")
                .correctAnswer("1st_string")
                .incorrectAnswers(Arrays.asList("my_string_1", "foo", "_"))
                .creationDate(LocalDate.of(2020, 1, 8))
                .validationDate(null)
                .isValidated(false)
                .build();
        Question question8 = Question.builder()
                .category(categoryStorage.getById(1))
                .type(Type.MULTIPLE)
                .question("Which of the following cannot be a variable?")
                .correctAnswer("in")
                .incorrectAnswers(Arrays.asList("__init__", "it", "on"))
                .creationDate(LocalDate.of(2020, 1, 10))
                .validationDate(null)
                .isValidated(false)
                .build();
        Question question9 = Question.builder()
                .category(categoryStorage.getById(3))
                .type(Type.MULTIPLE)
                .question("Which of the following property is used to change the face of a font?")
                .correctAnswer("font-family")
                .incorrectAnswers(Arrays.asList("font-style", "font-variant", "font-weight"))
                .creationDate(LocalDate.of(2020, 1, 15))
                .validationDate(null)
                .isValidated(false)
                .build();
        Question question10 = Question.builder()
                .category(categoryStorage.getById(3))
                .type(Type.MULTIPLE)
                .question("Which of the following property of a anchor element signifies visited hyperlinks?")
                .correctAnswer(":visited")
                .incorrectAnswers(Arrays.asList(":link", ":hover", ":active"))
                .creationDate(LocalDate.of(2020, 1, 19))
                .validationDate(null)
                .isValidated(false)
                .build();
        Question question11 = Question.builder()
                .category(categoryStorage.getById(3))
                .type(Type.MULTIPLE)
                .question("Which of the following selector selects an element that has no children?")
                .correctAnswer(":empty")
                .incorrectAnswers(Arrays.asList(":nochild", ":inheritance", ":no-child"))
                .creationDate(LocalDate.of(2020, 1, 19))
                .validationDate(null)
                .isValidated(true)
                .build();
        Question question12 = Question.builder()
                .category(categoryStorage.getById(4))
                .type(Type.MULTIPLE)
                .question("What is returned by TRUNC(789.8389, 2)?")
                .correctAnswer("789.83")
                .incorrectAnswers(Arrays.asList("789.84", "78", "789.00"))
                .creationDate(LocalDate.of(2020, 1, 27))
                .validationDate(null)
                .isValidated(true)
                .build();
        Question question13 = Question.builder()
                .category(categoryStorage.getById(4))
                .type(Type.MULTIPLE)
                .question("Which SQL function is used to count the number of rows in a SQL query?")
                .correctAnswer("COUNT(*)")
                .incorrectAnswers(Arrays.asList("SUM()", "NUMBER()", "COUNT()"))
                .creationDate(LocalDate.of(2020, 2, 4))
                .validationDate(null)
                .isValidated(true)
                .build();
        Question question14 = Question.builder()
                .category(categoryStorage.getById(4))
                .type(Type.MULTIPLE)
                .question("______ removes all rows from a table without logging the individual row deletions.")
                .correctAnswer("TRUNCATE")
                .incorrectAnswers(Arrays.asList("DROP", "REMOVE", "DELETE"))
                .creationDate(LocalDate.of(2020, 2, 9))
                .validationDate(null)
                .isValidated(true)
                .build();
        Question question15 = Question.builder()
                .category(categoryStorage.getById(5))
                .type(Type.MULTIPLE)
                .question("Choose the correct HTML element for the largest heading.")
                .correctAnswer("<h1>")
                .incorrectAnswers(Arrays.asList("<h6>", "<head>", "<heading>"))
                .creationDate(LocalDate.of(2020, 2, 13))
                .validationDate(null)
                .isValidated(true)
                .build();
        Question question16 = Question.builder()
                .category(categoryStorage.getById(5))
                .type(Type.MULTIPLE)
                .question("Choose the correct HTML element to define important text.")
                .correctAnswer("<strong>")
                .incorrectAnswers(Arrays.asList("<b>", "<i>", "<important>"))
                .creationDate(LocalDate.of(2020, 2, 20))
                .validationDate(null)
                .isValidated(true)
                .build();
        Question question17 = Question.builder()
                .category(categoryStorage.getById(5))
                .type(Type.MULTIPLE)
                .question("How can you make a numbered list?")
                .correctAnswer("<ol>")
                .incorrectAnswers(Arrays.asList("<ul>", "<dl>", "<list>"))
                .creationDate(LocalDate.of(2020, 2, 20))
                .validationDate(null)
                .isValidated(true)
                .build();
        questionStorage.add(question1);
        questionStorage.add(question2);
        questionStorage.add(question3);
        questionStorage.add(question4);
        questionStorage.add(question5);
        questionStorage.add(question6);
        questionStorage.add(question7);
        questionStorage.add(question8);
        questionStorage.add(question9);
        questionStorage.add(question10);
        questionStorage.add(question11);
        questionStorage.add(question12);
        questionStorage.add(question13);
        questionStorage.add(question14);
        questionStorage.add(question15);
        questionStorage.add(question16);
        questionStorage.add(question17);
    }

    private void loadInitCustomQuizzes() {
        CustomQuiz quiz1 = CustomQuiz.builder().name("Eszti").build();
        CustomQuiz quiz2 = CustomQuiz.builder().name("Mira").build();
        CustomQuiz quiz3 = CustomQuiz.builder().name("Droszi").build();
        CustomQuiz quiz4 = CustomQuiz.builder().name("Stofi").build();

        quiz1.setQuestions(questionStorage.getAll().subList(0, 5));
        quiz2.setQuestions(questionStorage.getAll().subList(5, 10));
        quiz3.setQuestions(questionStorage.getAll().subList(10, 15));
        quiz4.setQuestions(questionStorage.getAll().subList(12, 16));

        customQuizStorage.add(quiz1);
        customQuizStorage.add(quiz2);
        customQuizStorage.add(quiz3);
        customQuizStorage.add(quiz4);
    }
}
