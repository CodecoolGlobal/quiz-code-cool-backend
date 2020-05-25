package com.codecool.codecoolquiz.service;

import com.codecool.codecoolquiz.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

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
    public void loadInitData() {
        if (categoryStorage.categoryRepository.count() == 0) {
            loadInitCategories();
        }

        if (appUserStorage.appUserRepository.count() == 0) {
            loadUsers();
        }
        AppUser appUser = appUserStorage.getByName("admin");

        if (questionStorage.questionRepository.count() == 0) {
            loadProgBasicsQuestions(appUser);
            loadWebQuestions(appUser);
            loadOOPQuestions(appUser);
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
                        .username("username")
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
        categoryStorage.add(Category.builder().name("Network").build());
        categoryStorage.add(Category.builder().name("Javascript").build());
    }

    private void loadProgBasicsQuestions(AppUser appUser) {

        Question question = Question.builder()
                .category(categoryStorage.getById(1))
                .type(Type.MULTIPLE)
                .question("Which of the following is an invalid variable?")
                .correctAnswer("1st_string")
                .incorrectAnswers(Arrays.asList("my_string_1", "foo", "_"))
                .creationDate(LocalDate.of(2020, 1, 8))
                .validationDate(null)
                .isValidated(false)
                .appUser(appUser)
                .build();

        Question question1 = Question.builder()
                .category(categoryStorage.getById(6))
                .type(Type.MULTIPLE)
                .question("In any programming language, what is the most common way to iterate through an array?")
                .correctAnswer("For loops")
                .incorrectAnswers(Arrays.asList("If Statements", "Do-while loops", "While loops"))
                .creationDate(LocalDate.of(2010, 6, 14))
                .validationDate(null)
                .isValidated(false)
                .appUser(appUser)
                .build();

        Question question2 = Question.builder()
                .category(categoryStorage.getById(1))
                .type(Type.MULTIPLE)
                .question("Which of the following cannot be a variable?")
                .correctAnswer("in")
                .incorrectAnswers(Arrays.asList("__init__", "it", "on"))
                .creationDate(LocalDate.of(2020, 1, 10))
                .validationDate(null)
                .isValidated(false)
                .appUser(appUser)
                .build();

        Question question3 = Question.builder()
                .category(categoryStorage.getById(6))
                .type(Type.MULTIPLE)
                .question("Which is not a command line command?")
                .correctAnswer("body")
                .incorrectAnswers(Arrays.asList("head", "more", "tail"))
                .creationDate(LocalDate.now())
                .validationDate(null)
                .isValidated(false)
                .appUser(appUser)
                .build();

        Question question4 = Question.builder()
                .category(categoryStorage.getById(6))
                .type(Type.BOOLEAN)
                .question("In Linux a shell is a program which protects the system from hacker attacks.")
                .correctAnswer("false")
                .incorrectAnswers(Arrays.asList("true"))
                .creationDate(LocalDate.now())
                .validationDate(null)
                .isValidated(false)
                .appUser(appUser)
                .build();

        Question question5 = Question.builder()
                .category(categoryStorage.getById(6))
                .type(Type.BOOLEAN)
                .question("Finally statement will be executed when all the error where caught and handled.")
                .correctAnswer("false")
                .incorrectAnswers(Arrays.asList("true"))
                .creationDate(LocalDate.now())
                .validationDate(null)
                .isValidated(false)
                .appUser(appUser)
                .build();

        Question question6 = Question.builder()
                .category(categoryStorage.getById(6))
                .type(Type.BOOLEAN)
                .question("A remote repository is a common repository that all team members use to exchange their changes.")
                .correctAnswer("true")
                .incorrectAnswers(Arrays.asList("false"))
                .creationDate(LocalDate.now())
                .validationDate(null)
                .isValidated(false)
                .appUser(appUser)
                .build();

        Question question7 = Question.builder()
                .category(categoryStorage.getById(6))
                .type(Type.MULTIPLE)
                .question("Which is not a git command?")
                .correctAnswer("delete")
                .incorrectAnswers(Arrays.asList("init", "reset", "revert"))
                .creationDate(LocalDate.now())
                .validationDate(null)
                .isValidated(false)
                .appUser(appUser)
                .build();

        Question question8 = Question.builder()
                .category(categoryStorage.getById(1))
                .type(Type.BOOLEAN)
                .question("The order of the function definitions matters in Python.")
                .correctAnswer("false")
                .incorrectAnswers(Arrays.asList("true"))
                .creationDate(LocalDate.now())
                .validationDate(null)
                .isValidated(false)
                .appUser(appUser)
                .build();

        Question question9 = Question.builder()
                .category(categoryStorage.getById(1))
                .type(Type.BOOLEAN)
                .question("The sort() method returns a new list.")
                .correctAnswer("false")
                .incorrectAnswers(Arrays.asList("true"))
                .creationDate(LocalDate.now())
                .validationDate(null)
                .isValidated(false)
                .appUser(appUser)
                .build();

        List<Question> questions = Arrays.asList(
                question,
                question1,
                question2,
                question3,
                question4,
                question5,
                question6,
                question7,
                question8,
                question9
        );

        questionStorage.addAll(questions);
        CustomQuiz progbasics = CustomQuiz.builder()
                .name("ProgBasics module")
                .questions(questions)
                .build();
        customQuizStorage.add(progbasics);

    }

    private void loadWebQuestions(AppUser appUser) {

        Question question = Question.builder()
                .category(categoryStorage.getById(6))
                .type(Type.BOOLEAN)
                .question("Linked list is a data structure where multiple list are linked to each other.")
                .correctAnswer("false")
                .incorrectAnswers(Arrays.asList("true"))
                .creationDate(LocalDate.of(2020, 1, 15))
                .validationDate(null)
                .isValidated(false)
                .appUser(appUser)
                .build();

        Question question1 = Question.builder()
                .category(categoryStorage.getById(8))
                .type(Type.BOOLEAN)
                .question("With event capturing, the event is first captured by the outermost element and propagated to the inner elements.")
                .correctAnswer("true")
                .incorrectAnswers(Arrays.asList("false"))
                .creationDate(LocalDate.of(2020, 1, 15))
                .validationDate(null)
                .isValidated(false)
                .appUser(appUser)
                .build();

        Question question2 = Question.builder()
                .category(categoryStorage.getById(4))
                .type(Type.BOOLEAN)
                .question("Constrains allow you to summarize information about a group of rows of data.")
                .correctAnswer("false")
                .incorrectAnswers(Arrays.asList("true"))
                .creationDate(LocalDate.of(2020, 1, 15))
                .validationDate(null)
                .isValidated(false)
                .appUser(appUser)
                .build();

        Question question3 = Question.builder()
                .category(categoryStorage.getById(7))
                .type(Type.MULTIPLE)
                .question("418 status code stands for...")
                .correctAnswer("I'm a teapot")
                .incorrectAnswers(Arrays.asList("Upgrade Required", "Request Timeout", "Not Acceptable"))
                .creationDate(LocalDate.of(2020, 1, 15))
                .validationDate(null)
                .isValidated(false)
                .appUser(appUser)
                .build();
        Question question4 = Question.builder()
                .category(categoryStorage.getById(6))
                .type(Type.BOOLEAN)
                .question("Agile software development uses iterative development as a basis.")
                .correctAnswer("true")
                .incorrectAnswers(Arrays.asList("false"))
                .creationDate(LocalDate.of(2020, 1, 15))
                .validationDate(null)
                .isValidated(false)
                .appUser(appUser)
                .build();

        Question question5 = Question.builder()
                .category(categoryStorage.getById(6))
                .type(Type.MULTIPLE)
                .question("Which is not a SCRUM role?")
                .correctAnswer("Sprint Manager")
                .incorrectAnswers(Arrays.asList("Product Owner", "Scrum Master", "Development Team"))
                .creationDate(LocalDate.of(2020, 1, 15))
                .validationDate(null)
                .isValidated(false)
                .appUser(appUser)
                .build();

        Question question6 = Question.builder()
                .category(categoryStorage.getById(3))
                .type(Type.MULTIPLE)
                .question("Which of the following property is used to change the face of a font?")
                .correctAnswer("font-family")
                .incorrectAnswers(Arrays.asList("font-style", "font-variant", "font-weight"))
                .creationDate(LocalDate.of(2020, 1, 15))
                .validationDate(null)
                .isValidated(false)
                .appUser(appUser)
                .build();

        Question question7 = Question.builder()
                .category(categoryStorage.getById(3))
                .type(Type.MULTIPLE)
                .question("Which of the following property of a anchor element signifies visited hyperlinks?")
                .correctAnswer(":visited")
                .incorrectAnswers(Arrays.asList(":link", ":hover", ":active"))
                .creationDate(LocalDate.of(2020, 1, 19))
                .validationDate(null)
                .isValidated(false)
                .appUser(appUser)
                .build();

        Question question8 = Question.builder()
                .category(categoryStorage.getById(5))
                .type(Type.MULTIPLE)
                .question("How can you make a numbered list?")
                .correctAnswer("<ol>")
                .incorrectAnswers(Arrays.asList("<ul>", "<dl>", "<list>"))
                .creationDate(LocalDate.of(2020, 2, 20))
                .validationDate(null)
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question9 = Question.builder()
                .category(categoryStorage.getById(5))
                .type(Type.MULTIPLE)
                .question("Choose the correct HTML element to define important text.")
                .correctAnswer("<strong>")
                .incorrectAnswers(Arrays.asList("<b>", "<i>", "<important>"))
                .creationDate(LocalDate.of(2020, 2, 20))
                .validationDate(null)
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question10 = Question.builder()
                .category(categoryStorage.getById(3))
                .type(Type.MULTIPLE)
                .question("Which of the following selector selects an element that has no children?")
                .correctAnswer(":empty")
                .incorrectAnswers(Arrays.asList(":nochild", ":inheritance", ":no-child"))
                .creationDate(LocalDate.of(2020, 1, 19))
                .validationDate(null)
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question11 = Question.builder()
                .category(categoryStorage.getById(4))
                .type(Type.MULTIPLE)
                .question("What is returned by TRUNC(789.8389, 2)?")
                .correctAnswer("789.83")
                .incorrectAnswers(Arrays.asList("789.84", "78", "789.00"))
                .creationDate(LocalDate.of(2020, 1, 27))
                .validationDate(null)
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question12 = Question.builder()
                .category(categoryStorage.getById(4))
                .type(Type.MULTIPLE)
                .question("Which SQL function is used to count the number of rows in a SQL query?")
                .correctAnswer("COUNT(*)")
                .incorrectAnswers(Arrays.asList("SUM()", "NUMBER()", "COUNT()"))
                .creationDate(LocalDate.of(2020, 2, 4))
                .validationDate(null)
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question13 = Question.builder()
                .category(categoryStorage.getById(4))
                .type(Type.MULTIPLE)
                .question("______ removes all rows from a table without logging the individual row deletions.")
                .correctAnswer("TRUNCATE")
                .incorrectAnswers(Arrays.asList("DROP", "REMOVE", "DELETE"))
                .creationDate(LocalDate.of(2020, 2, 9))
                .validationDate(null)
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question14 = Question.builder()
                .category(categoryStorage.getById(5))
                .type(Type.MULTIPLE)
                .question("Choose the correct HTML element for the largest heading.")
                .correctAnswer("<h1>")
                .incorrectAnswers(Arrays.asList("<h6>", "<head>", "<heading>"))
                .creationDate(LocalDate.of(2020, 2, 13))
                .validationDate(null)
                .isValidated(true)
                .appUser(appUser)
                .build();

        List<Question> questions = Arrays.asList(
                question,
                question1,
                question2,
                question3,
                question4,
                question5,
                question6,
                question7,
                question8,
                question9,
                question10,
                question11,
                question12,
                question13,
                question14
        );

        questionStorage.addAll(questions);
        CustomQuiz web = CustomQuiz.builder()
                .name("Web module")
                .questions(questions)
                .build();
        customQuizStorage.add(web);
    }

    private void loadOOPQuestions(AppUser appUser) {

        Question question = Question.builder()
                .category(categoryStorage.getById(2))
                .type(Type.BOOLEAN)
                .question("You can create only one instance from an abstract class.")
                .correctAnswer("false")
                .incorrectAnswers(Arrays.asList("true"))
                .creationDate(LocalDate.now())
                .validationDate(null)
                .isValidated(false)
                .appUser(appUser)
                .build();

        Question question1 = Question.builder()
                .category(categoryStorage.getById(2))
                .type(Type.MULTIPLE)
                .question("Which is not an Object Oriented Principle?")
                .correctAnswer("Single Responsibility")
                .incorrectAnswers(Arrays.asList("Abstraction", "Polymorphism", "Encapsulation"))
                .creationDate(LocalDate.of(2019, 9, 20))
                .validationDate(null)
                .isValidated(false)
                .appUser(appUser)
                .build();

        Question question2 = Question.builder()
                .category(categoryStorage.getById(2))
                .type(Type.BOOLEAN)
                .question("The JVM compiles the program to bytecode.")
                .correctAnswer("false")
                .incorrectAnswers(Arrays.asList("true"))
                .creationDate(LocalDate.now())
                .validationDate(null)
                .isValidated(false)
                .appUser(appUser)
                .build();

        Question question3 = Question.builder()
                .category(categoryStorage.getById(2))
                .type(Type.MULTIPLE)
                .question("Which is the default access modifier in a class?")
                .correctAnswer("package private")
                .incorrectAnswers(Arrays.asList("public", "private", "there's no default access modifier"))
                .creationDate(LocalDate.of(2019, 9, 20))
                .validationDate(null)
                .isValidated(false)
                .appUser(appUser)
                .build();

        Question question4 = Question.builder()
                .category(categoryStorage.getById(2))
                .type(Type.MULTIPLE)
                .question("______ compares the two given strings based on the data/content of the string.")
                .correctAnswer("equals()")
                .incorrectAnswers(Arrays.asList("compare()", "==", "==="))
                .creationDate(LocalDate.of(2019, 9, 20))
                .validationDate(null)
                .isValidated(false)
                .appUser(appUser)
                .build();

        Question question5 = Question.builder()
                .category(categoryStorage.getById(2))
                .type(Type.BOOLEAN)
                .question("Converting an object of a wrapper type to its corresponding primitive value is called unboxing.")
                .correctAnswer("true")
                .incorrectAnswers(Arrays.asList("false"))
                .creationDate(LocalDate.of(2019, 9, 20))
                .validationDate(null)
                .isValidated(false)
                .appUser(appUser)
                .build();

        Question question6 = Question.builder()
                .category(categoryStorage.getById(4))
                .type(Type.BOOLEAN)
                .question("Database normalization is the process of deleting not valid data from the database.")
                .correctAnswer("false")
                .incorrectAnswers(Arrays.asList("true"))
                .creationDate(LocalDate.of(2017, 9, 20))
                .validationDate(null)
                .isValidated(false)
                .appUser(appUser)
                .build();

        Question question7 = Question.builder()
                .category(categoryStorage.getById(2))
                .type(Type.MULTIPLE)
                .question("Which is a valid keyword in java?")
                .correctAnswer("interface")
                .incorrectAnswers(Arrays.asList("string", "Float", "unsigned"))
                .creationDate(LocalDate.of(2017, 9, 20))
                .validationDate(null)
                .isValidated(false)
                .appUser(appUser)
                .build();

        Question question8 = Question.builder()
                .category(categoryStorage.getById(2))
                .type(Type.MULTIPLE)
                .question("Which is the valid declarations within an interface definition?")
                .correctAnswer("public double methoda();")
                .incorrectAnswers(Arrays.asList("public final double methoda();", "static void methoda(double d1);", "protected void methoda(double d1);"))
                .creationDate(LocalDate.of(2019, 11, 12))
                .validationDate(null)
                .isValidated(false)
                .appUser(appUser)
                .build();

        Question question9 = Question.builder()
                .category(categoryStorage.getById(2))
                .type(Type.MULTIPLE)
                .question("Which is a valid declarations of a String?")
                .correctAnswer("String s1 = null;")
                .incorrectAnswers(Arrays.asList("String s2 = 'null';", "String s3 = (String) 'abc';", "String s4 = (String) '\\ufeed';"))
                .creationDate(LocalDate.of(2020, 1, 3))
                .validationDate(null)
                .isValidated(false)
                .appUser(appUser)
                .build();

        Question question10 = Question.builder()
                .category(categoryStorage.getById(2))
                .type(Type.MULTIPLE)
                .question("What is output for − 'search'. find('S') ?")
                .correctAnswer("interface")
                .incorrectAnswers(Arrays.asList("s", "-1", "''"))
                .creationDate(LocalDate.of(2020, 1, 5))
                .validationDate(null)
                .isValidated(false)
                .appUser(appUser)
                .build();

        List<Question> questions = Arrays.asList(
                question,
                question1,
                question2,
                question3,
                question4,
                question5,
                question6,
                question7,
                question8,
                question9,
                question10
        );

        questionStorage.addAll(questions);
        CustomQuiz oop = CustomQuiz.builder()
                .name("OOP module")
                .questions(questions)
                .build();
        customQuizStorage.add(oop);
    }
}
