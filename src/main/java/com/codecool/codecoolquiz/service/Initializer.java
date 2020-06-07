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

        if (questionStorage.questionRepository.count() == 0) {
            AppUser admin = appUserStorage.getByName("admin");
            AppUser username = appUserStorage.getByName("username");
            loadPythonQuestionsWithoutQuiz(username);
            loadNetworkQuestionsWithoutQuiz(username);
            loadCssQuestionsWithoutQuiz(username);
            loadSQLQuestionsWithoutQuiz(admin);
            loadJSQuestionsWithoutQuiz(admin);
            loadProgBasicsQuestions(username);
            loadWebQuestions(admin);
            loadOOPJavaQuestions(admin);
        }

    }

    private void loadUsers() {
        appUserStorage.add(
                AppUser.builder()
                        .username("admin")
                        .password(encoder.encode("password1"))
                        .role("USER")
                        .role("ADMIN")
                        .email("admin@codecool.com")
                        .registrationDate(LocalDate.now())
                        .build()
        );

        appUserStorage.add(
                AppUser.builder()
                        .username("username")
                        .password(encoder.encode("password1"))
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
        categoryStorage.add(Category.builder().name("Test").build());
        categoryStorage.add(Category.builder().name("C#").build());
    }

    private void loadPythonQuestionsWithoutQuiz(AppUser appUser) {

        Question question = Question.builder()
                .category(categoryStorage.getById(1))
                .type(Type.MULTIPLE)
                .question("In Python 3.x you can print \"Hello World!\" onto the screen with:")
                .correctAnswer("print('Hello World!')")
                .incorrectAnswers(Arrays.asList("print \"Hello World!\"", "System.out.println(\"Hello World!\")", "print(\"Hello world\")"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question2 = Question.builder()
                .category(categoryStorage.getById(1))
                .type(Type.BOOLEAN)
                .question("Floats can be created by using operations such as division on integers.")
                .correctAnswer("true")
                .incorrectAnswer("false")
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question3 = Question.builder()
                .category(categoryStorage.getById(1))
                .type(Type.MULTIPLE)
                .question("Which is not a valid arithmetic operator in Python?")
                .correctAnswer("++")
                .incorrectAnswers(Arrays.asList("//", "%", "**"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question4 = Question.builder()
                .category(categoryStorage.getById(1))
                .type(Type.MULTIPLE)
                .question("Which error will be produced?\n>>> 1 + '2' + 3 + '4'")
                .correctAnswer("TypeError")
                .incorrectAnswers(Arrays.asList("ValueError", "SyntaxError", "RuntimeError"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question5 = Question.builder()
                .category(categoryStorage.getById(1))
                .type(Type.MULTIPLE)
                .question("What error is caused by importing an unknown module?")
                .correctAnswer("ImportError")
                .incorrectAnswers(Arrays.asList("ModuleError", "UnknownModuleError", "NameError"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question6 = Question.builder()
                .category(categoryStorage.getById(1))
                .type(Type.MULTIPLE)
                .question("Which of the following cannot be a variable?")
                .correctAnswer("in")
                .incorrectAnswers(Arrays.asList("__init__", "it", "on"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question7 = Question.builder()
                .category(categoryStorage.getById(1))
                .type(Type.MULTIPLE)
                .question("Which is a valid conditional expression? (ternary operator)")
                .correctAnswer("b = 1 if a >= 5 else 2")
                .incorrectAnswers(Arrays.asList(
                        "if a >= 5 then b = 1 else b = 2",
                        "b = a >= 5 ? 1 : 2",
                        "b = 1 when a >= 5 else 2"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question8 = Question.builder()
                .category(categoryStorage.getById(1))
                .type(Type.BOOLEAN)
                .question("To ensure that python code won't be run if the file containing your code is imported, put your code inside if __name__ == \"__main__\".")
                .correctAnswer("true")
                .incorrectAnswer("false")
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question9 = Question.builder()
                .category(categoryStorage.getById(1))
                .type(Type.MULTIPLE)
                .question("Which is not true?")
                .correctAnswer("\"7\" + 'eight' will produce an error")
                .incorrectAnswers(Arrays.asList(
                        "Strings can be multiplied by integers.",
                        "You can’t concatenate strings with integers.",
                        "Strings can't be multiplied by floats, even if the floats are whole numbers."
                         ))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question10 = Question.builder()
                .category(categoryStorage.getById(1))
                .type(Type.BOOLEAN)
                .question("The insert method adds an item to the end of an existing list.")
                .correctAnswer("false")
                .incorrectAnswer("true")
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question11 = Question.builder()
                .category(categoryStorage.getById(1))
                .type(Type.MULTIPLE)
                .question("To get the number of items in my_list, you can use:")
                .correctAnswer("len(my_list)")
                .incorrectAnswers(Arrays.asList("my_list.size()", "my_list.length", "my_list.length()"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question12 = Question.builder()
                .category(categoryStorage.getById(1))
                .type(Type.BOOLEAN)
                .question("Tuples can be sliced")
                .correctAnswer("true")
                .incorrectAnswer("false")
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question13 = Question.builder()
                .category(categoryStorage.getById(1))
                .type(Type.MULTIPLE)
                .question("What is output for − 'search'.find('S') ?")
                .correctAnswer("-1")
                .incorrectAnswers(Arrays.asList("1", "0", "false"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        List<Question> questions = Arrays.asList(
                question,
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
                question13
        );
        questionStorage.addAll(questions);
    }

    private void loadSQLQuestionsWithoutQuiz(AppUser appUser) {

        Question question = Question.builder()
                .category(categoryStorage.getById(4))
                .type(Type.MULTIPLE)
                .question("Which is not a valid SQL statement?")
                .correctAnswer("ORDER BY")
                .incorrectAnswers(Arrays.asList("INSERT", "UPDATE", "DELETE"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question2 = Question.builder()
                .category(categoryStorage.getById(4))
                .type(Type.MULTIPLE)
                .question("What is returned by TRUNC(789.8389, 2)?")
                .correctAnswer("789.83")
                .incorrectAnswers(Arrays.asList("789.84", "78", "789.00"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        List<Question> questions = Arrays.asList(
                question,
                question2
        );
        questionStorage.addAll(questions);
    }

    private void loadCssQuestionsWithoutQuiz(AppUser appUser) {

        Question question = Question.builder()
                .category(categoryStorage.getById(3))
                .type(Type.MULTIPLE)
                .question("Which of the following a anchor pseudo class signifies visited hyperlinks?")
                .correctAnswer(":visited")
                .incorrectAnswers(Arrays.asList(":link", ":hover", ":active"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question2 = Question.builder()
                .category(categoryStorage.getById(3))
                .type(Type.MULTIPLE)
                .question("Which is a valid CSS comment?")
                .correctAnswer("/* Comment goes here */")
                .incorrectAnswers(Arrays.asList("// Comment goes here", "<!-- Comment goes here -->", "\"\"\"Comment goes here\"\"\""))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question3 = Question.builder()
                .category(categoryStorage.getById(3))
                .type(Type.MULTIPLE)
                .question("What is the \"style\", when creating an internal CSS?")
                .correctAnswer("tag")
                .incorrectAnswers(Arrays.asList("property", "value", "import"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question4 = Question.builder()
                .category(categoryStorage.getById(3))
                .type(Type.BOOLEAN)
                .question("When specifying font family if the name of it is more than one word, it must be in quotation marks: \"Times New Roman\".")
                .correctAnswer("true")
                .incorrectAnswer("false")
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question5 = Question.builder()
                .category(categoryStorage.getById(3))
                .type(Type.MULTIPLE)
                .question("How do you make a list not display bullet points?")
                .correctAnswer("list-style-type: none")
                .incorrectAnswers(Arrays.asList("bulletpoints: none", "list-style-type: no-bullet", "bulletpoints: hidden"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question6 = Question.builder()
                .category(categoryStorage.getById(3))
                .type(Type.MULTIPLE)
                .question("What is the default value of the overflow property?")
                .correctAnswer("visible")
                .incorrectAnswers(Arrays.asList("scroll", "hidden", "auto"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question7 = Question.builder()
                .category(categoryStorage.getById(3))
                .type(Type.MULTIPLE)
                .question("In order to make the z-index property work you must ...")
                .correctAnswer("position elements")
                .incorrectAnswers(Arrays.asList("clear floats", "use floating", "use the overflow property"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question8 = Question.builder()
                .category(categoryStorage.getById(3))
                .type(Type.MULTIPLE)
                .question("Which of the following is NOT a pseudo element in CSS?")
                .correctAnswer("::hidden")
                .incorrectAnswers(Arrays.asList("::selection", "::before", "::first-line"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question9 = Question.builder()
                .category(categoryStorage.getById(3))
                .type(Type.MULTIPLE)
                .question("In the following code snippet, what value is given for the bottom-right corner? border-radius: 10px 20px 30px 40px;")
                .correctAnswer("30")
                .incorrectAnswers(Arrays.asList("10", "20", "40"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question10 = Question.builder()
                .category(categoryStorage.getById(3))
                .type(Type.BOOLEAN)
                .question("The opacity property value must be a number between 0 (fully transparent) and 100 (fully opaque).")
                .correctAnswer("false")
                .incorrectAnswer("true")
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question11 = Question.builder()
                .category(categoryStorage.getById(3))
                .type(Type.BOOLEAN)
                .question("Visibility property with hidden value hides an element, but it will still take up the same space as before.")
                .correctAnswer("true")
                .incorrectAnswer("false")
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        List<Question> questions = Arrays.asList(
                question,
                question2,
                question3,
                question4,
                question5,
                question6,
                question7,
                question8,
                question9,
                question10,
                question11
        );
        questionStorage.addAll(questions);
    }

    private void loadJSQuestionsWithoutQuiz(AppUser appUser) {

        Question question = Question.builder()
                .category(categoryStorage.getById(8))
                .type(Type.BOOLEAN)
                .question("Declarations with var keyword are globally scoped or function scoped.")
                .correctAnswer("true")
                .incorrectAnswer("false")
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question2 = Question.builder()
                .category(categoryStorage.getById(8))
                .type(Type.BOOLEAN)
                .question("The const and let keywords define variables with block scope.")
                .correctAnswer("true")
                .incorrectAnswer("false")
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question3 = Question.builder()
                .category(categoryStorage.getById(8))
                .type(Type.MULTIPLE)
                .question("Which statement is true?")
                .correctAnswer("JavaScript only hoists declarations, not initializations")
                .incorrectAnswers(Arrays.asList("Const and let keywords define variables globally or with function scope", "Var defines variables with block scope", "Const and let are subject to variable hoisting"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question4 = Question.builder()
                .category(categoryStorage.getById(8))
                .type(Type.BOOLEAN)
                .question("The for...in loop is intended for iterating over the enumerable keys of an object.")
                .correctAnswer("true")
                .incorrectAnswer("false")
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question5 = Question.builder()
                .category(categoryStorage.getById(8))
                .type(Type.BOOLEAN)
                .question("'And', 'or' and 'not' are valid javascript operators.")
                .correctAnswer("false")
                .incorrectAnswer("true")
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question6 = Question.builder()
                .category(categoryStorage.getById(8))
                .type(Type.MULTIPLE)
                .question("What tag contains the JavaScript code?")
                .correctAnswer("script")
                .incorrectAnswers(Arrays.asList("body", "style", "code"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question7 = Question.builder()
                .category(categoryStorage.getById(8))
                .type(Type.BOOLEAN)
                .question("A variable declared without a value will have the value null.")
                .correctAnswer("false")
                .incorrectAnswer("true")
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question8 = Question.builder()
                .category(categoryStorage.getById(8))
                .type(Type.BOOLEAN)
                .question("The continue statement \"jumps out\" of a loop and continues executing the code after the loop.")
                .correctAnswer("false")
                .incorrectAnswer("true")
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question9 = Question.builder()
                .category(categoryStorage.getById(8))
                .type(Type.MULTIPLE)
                .question("How can we not define a function?")
                .correctAnswer("with function builder")
                .incorrectAnswers(Arrays.asList("with function declaration", "with function expression", "with arrow function expression"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question10 = Question.builder()
                .category(categoryStorage.getById(8))
                .type(Type.MULTIPLE)
                .question("Which is not true?")
                .correctAnswer("ECMAScript 6 is also known as ES6 and ECMAScript 2016")
                .incorrectAnswers(Arrays.asList(
                        "ES6 introduced new OOP concepts such as classes",
                        "ES6 adds new syntax including modules, arrow functions, promises, for/of loops",
                        "JavaScript is one of the most popular implementations of ES"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question11 = Question.builder()
                .category(categoryStorage.getById(8))
                .type(Type.MULTIPLE)
                .question("In switch statement the \"default\" statement is used ...")
                .correctAnswer("when no match is found")
                .incorrectAnswers(Arrays.asList("to finish the \"case\" statement", "because it is obligatory", "to keep the code as readable as possible"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question12 = Question.builder()
                .category(categoryStorage.getById(8))
                .type(Type.BOOLEAN)
                .question("ECMAScript is a scripting language specification created to standardize JavaScript.")
                .correctAnswer("true")
                .incorrectAnswer("false")
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question13 = Question.builder()
                .category(categoryStorage.getById(8))
                .type(Type.MULTIPLE)
                .question("Which is true?")
                .correctAnswer("The document object is the root of the DOM")
                .incorrectAnswers(Arrays.asList("DOM stands for Document Orientation Model", "innerHTML is a method", "A node in the DOM can have multiple parent nodes"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        List<Question> questions = Arrays.asList(
                question,
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
                question13
        );
        questionStorage.addAll(questions);
    }

    private void loadNetworkQuestionsWithoutQuiz(AppUser appUser) {

        Question question = Question.builder()
                .category(categoryStorage.getById(7))
                .type(Type.MULTIPLE)
                .question("418 status code stands for...")
                .correctAnswer("I'm a teapot")
                .incorrectAnswers(Arrays.asList("Upgrade Required", "Request Timeout", "Not Acceptable"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question2 = Question.builder()
                .category(categoryStorage.getById(7))
                .type(Type.MULTIPLE)
                .question("In the network HTTP resources are located by")
                .correctAnswer("uniform resource identifier")
                .incorrectAnswers(Arrays.asList("unique resource locator", "unique resource identifier", "union resource locator"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question3 = Question.builder()
                .category(categoryStorage.getById(7))
                .type(Type.MULTIPLE)
                .question("DNS stands for")
                .correctAnswer("Domain Name System")
                .incorrectAnswers(Arrays.asList(
                        "Domain Name Server",
                        "Domain Network Server", "Destination Network Server"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        List<Question> questions = Arrays.asList(
                question,
                question2,
                question3
        );
        questionStorage.addAll(questions);
    }

    private void loadProgBasicsQuestions(AppUser appUser) {

        Question question = Question.builder()
                .category(categoryStorage.getById(1))
                .type(Type.MULTIPLE)
                .question("Which of the following is an invalid variable name?")
                .correctAnswer("1st_string")
                .incorrectAnswers(Arrays.asList("my_string_1", "foo", "_"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question1 = Question.builder()
                .category(categoryStorage.getById(6))
                .type(Type.MULTIPLE)
                .question("In any programming language, what is the most common way to iterate through an array?")
                .correctAnswer("For loops")
                .incorrectAnswers(Arrays.asList("If Statements", "Do-while loops", "While loops"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question2 = Question.builder()
                .category(categoryStorage.getById(6))
                .type(Type.MULTIPLE)
                .question("What is the automatic process by which unnecessary objects are deleted to free memory?")
                .correctAnswer("Garbage collection")
                .incorrectAnswers(Arrays.asList("Rubbish deletion", "Bit-trash exfoliation", "Deep cleaning"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question3 = Question.builder()
                .category(categoryStorage.getById(6))
                .type(Type.MULTIPLE)
                .question("Which is not a command line command?")
                .correctAnswer("body")
                .incorrectAnswers(Arrays.asList("head", "more", "tail"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question4 = Question.builder()
                .category(categoryStorage.getById(6))
                .type(Type.BOOLEAN)
                .question("In Linux a shell is a program which protects the system from hacker attacks.")
                .correctAnswer("false")
                .incorrectAnswer("true")
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question5 = Question.builder()
                .category(categoryStorage.getById(6))
                .type(Type.BOOLEAN)
                .question("Finally statement will be executed when all the exceptions where caught and handled.")
                .correctAnswer("false")
                .incorrectAnswer("true")
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question6 = Question.builder()
                .category(categoryStorage.getById(6))
                .type(Type.BOOLEAN)
                .question("A remote repository is a common repository that all team members use to exchange their changes.")
                .correctAnswer("true")
                .incorrectAnswer("false")
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question7 = Question.builder()
                .category(categoryStorage.getById(1))
                .type(Type.BOOLEAN)
                .question("Python code must be compiled before execution.")
                .correctAnswer("false")
                .incorrectAnswer("true")
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question8 = Question.builder()
                .category(categoryStorage.getById(1))
                .type(Type.BOOLEAN)
                .question("The order of the function definitions matters in Python.")
                .correctAnswer("false")
                .incorrectAnswer("true")
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question9 = Question.builder()
                .category(categoryStorage.getById(1))
                .type(Type.BOOLEAN)
                .question("The sort() method returns a new list.")
                .correctAnswer("false")
                .incorrectAnswer("true")
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question10 = Question.builder()
                .category(categoryStorage.getById(1))
                .type(Type.MULTIPLE)
                .question("Which is a correct import statement?")
                .correctAnswer("from math import sqrt, cos")
                .incorrectAnswers(Arrays.asList("import sqrt", "import cos from math", "import sqrt from math as square_root"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question11 = Question.builder()
                .category(categoryStorage.getById(1))
                .type(Type.MULTIPLE)
                .question("Which will cause an error?")
                .correctAnswer("[\"spam\", \"eggs\"].join(\",\")")
                .incorrectAnswers(Arrays.asList("\"spam, eggs, ham\".split(\", \")", "\"Hello ME\".replace(\"ME\", \"world\")", "\"This is a sentence.\".endswith(\"sentence.\")"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question12 = Question.builder()
                .category(categoryStorage.getById(1))
                .type(Type.BOOLEAN)
                .question("10 / 2 evaluates to 5.0")
                .correctAnswer("true")
                .incorrectAnswer("false")
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question13 = Question.builder()
                .category(categoryStorage.getById(1))
                .type(Type.MULTIPLE)
                .question("Which is the incorrect statement?")
                .correctAnswer("Python is statically-typed language")
                .incorrectAnswers(Arrays.asList("Python is processed at runtime by the interpreter", "Python was created by Guido van Rossum", "CPython is an implementation of Python"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question14 = Question.builder()
                .category(categoryStorage.getById(1))
                .type(Type.MULTIPLE)
                .question("Which of the following data types does not allow duplicate values?")
                .correctAnswer("Sets")
                .incorrectAnswers(Arrays.asList("Tuples", "Lists", "Dictionaries"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question15 = Question.builder()
                .category(categoryStorage.getById(1))
                .type(Type.BOOLEAN)
                .question("Named parameters to a function can be made optional by giving them a default value.")
                .correctAnswer("true")
                .incorrectAnswer("false")
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
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
                question14,
                question15
        );

        questionStorage.addAll(questions);
        CustomQuiz progbasics = CustomQuiz.builder()
                .name("ProgBasics module")
                .questions(questions)
                .appUser(appUser)
                .creationDate(LocalDate.now())
                .build();
        customQuizStorage.add(progbasics);

    }

    private void loadWebQuestions(AppUser appUser) {

        Question question = Question.builder()
                .category(categoryStorage.getById(8))
                .type(Type.BOOLEAN)
                .question("Use querySelector() method to get all elements in the document with a specified class.")
                .correctAnswer("false")
                .incorrectAnswer("true")
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question2 = Question.builder()
                .category(categoryStorage.getById(8))
                .type(Type.BOOLEAN)
                .question("With event capturing, the event is first captured by the outermost element and propagated to the inner elements.")
                .correctAnswer("true")
                .incorrectAnswer("false")
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question3 = Question.builder()
                .category(categoryStorage.getById(4))
                .type(Type.BOOLEAN)
                .question("Constraints allow you to summarize information about a group of rows of data.")
                .correctAnswer("false")
                .incorrectAnswer("true")
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question4 = Question.builder()
                .category(categoryStorage.getById(6))
                .type(Type.BOOLEAN)
                .question("Agile software development uses iterative development as a basis.")
                .correctAnswer("true")
                .incorrectAnswer("false")
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question5 = Question.builder()
                .category(categoryStorage.getById(6))
                .type(Type.MULTIPLE)
                .question("Which is not a SCRUM role?")
                .correctAnswer("Sprint Manager")
                .incorrectAnswers(Arrays.asList("Product Owner", "Scrum Master", "Development Team"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question6 = Question.builder()
                .category(categoryStorage.getById(3))
                .type(Type.MULTIPLE)
                .question("Which of the following property is used to change the face of a font?")
                .correctAnswer("font-family")
                .incorrectAnswers(Arrays.asList("font-style", "font-variant", "font-weight"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question7 = Question.builder()
                .category(categoryStorage.getById(3))
                .type(Type.MULTIPLE)
                .question("Which is not true?")
                .correctAnswer("CSS stands for Custom Style Sheets.")
                .incorrectAnswers(Arrays.asList("CSS allows you to separate style from content.", "Cascading refers to the way CSS applies one style on top of another.", "CSS defines how HTML elements are displayed."))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question8 = Question.builder()
                .category(categoryStorage.getById(5))
                .type(Type.MULTIPLE)
                .question("How can you make a numbered list?")
                .correctAnswer("<ol>")
                .incorrectAnswers(Arrays.asList("<ul>", "<dl>", "<list>"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question9 = Question.builder()
                .category(categoryStorage.getById(5))
                .type(Type.MULTIPLE)
                .question("Choose the correct HTML element to define important text.")
                .correctAnswer("<strong>")
                .incorrectAnswers(Arrays.asList("<b>", "<i>", "<important>"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question10 = Question.builder()
                .category(categoryStorage.getById(3))
                .type(Type.MULTIPLE)
                .question("Which of the following pseudo class represents an element that has no children?")
                .correctAnswer(":empty")
                .incorrectAnswers(Arrays.asList(":nochild", ":inheritance", ":no-child"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question11 = Question.builder()
                .category(categoryStorage.getById(3))
                .type(Type.MULTIPLE)
                .question("The \"overflow\" property is used to:")
                .correctAnswer("Specify the behavior that occurs when the content overflows the element's box")
                .incorrectAnswers(Arrays.asList("Make the box fit to the data it contains", "Make the box be compatible with the web standards", "Display a scrollbar next to the element"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question12 = Question.builder()
                .category(categoryStorage.getById(4))
                .type(Type.MULTIPLE)
                .question("Which SQL function is used to count the number of rows in a SQL query?")
                .correctAnswer("COUNT(*)")
                .incorrectAnswers(Arrays.asList("SUM()", "NUMBER()", "COUNT()"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question13 = Question.builder()
                .category(categoryStorage.getById(4))
                .type(Type.MULTIPLE)
                .question("______ removes all rows from a table without logging the individual row deletions.")
                .correctAnswer("TRUNCATE")
                .incorrectAnswers(Arrays.asList("DROP", "REMOVE", "DELETE"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question14 = Question.builder()
                .category(categoryStorage.getById(5))
                .type(Type.MULTIPLE)
                .question("Choose the correct HTML element for the largest heading.")
                .correctAnswer("<h1>")
                .incorrectAnswers(Arrays.asList("<h6>", "<head>", "<heading>"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question15 = Question.builder()
                .category(categoryStorage.getById(6))
                .type(Type.MULTIPLE)
                .question("Which is not a git command?")
                .correctAnswer("delete")
                .incorrectAnswers(Arrays.asList("init", "reset", "revert"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question16 = Question.builder()
                .category(categoryStorage.getById(4))
                .type(Type.MULTIPLE)
                .question("Which is not an SQL operator?")
                .correctAnswer("LIMIT")
                .incorrectAnswers(Arrays.asList("<>", "AND", "NOT"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question17 = Question.builder()
                .category(categoryStorage.getById(3))
                .type(Type.MULTIPLE)
                .question("Which is not a CSS selector?")
                .correctAnswer("dotted")
                .incorrectAnswers(Arrays.asList(".intro", "#top", "h2"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question18 = Question.builder()
                .category(categoryStorage.getById(3))
                .type(Type.BOOLEAN)
                .question("Border property is a shorthand format for other properties: border-width, border-style, border-color.")
                .correctAnswer("true")
                .incorrectAnswer("false")
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question19 = Question.builder()
                .category(categoryStorage.getById(3))
                .type(Type.MULTIPLE)
                .question("Which is not a CSS value?")
                .correctAnswer("margin")
                .incorrectAnswers(Arrays.asList("1.5rem", "lowercase", "underline"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question20 = Question.builder()
                .category(categoryStorage.getById(4))
                .type(Type.MULTIPLE)
                .question("Which is not an aggregate function?")
                .correctAnswer("DISTINCT")
                .incorrectAnswers(Arrays.asList("AVG", "MAX", "SUM"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        List<Question> questions = Arrays.asList(
                question,
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
                question14,
                question15,
                question16,
                question17,
                question18,
                question19,
                question20
        );

        questionStorage.addAll(questions);
        CustomQuiz web = CustomQuiz.builder()
                .name("Web module")
                .questions(questions)
                .appUser(appUser)
                .creationDate(LocalDate.now())
                .build();
        customQuizStorage.add(web);
    }

    private void loadOOPJavaQuestions(AppUser appUser) {

        Question question = Question.builder()
                .category(categoryStorage.getById(2))
                .type(Type.BOOLEAN)
                .question("You can create only one instance from an abstract class.")
                .correctAnswer("false")
                .incorrectAnswer("true")
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question1 = Question.builder()
                .category(categoryStorage.getById(2))
                .type(Type.MULTIPLE)
                .question("Which is not an Object Oriented Principle?")
                .correctAnswer("Single Responsibility")
                .incorrectAnswers(Arrays.asList("Abstraction", "Polymorphism", "Encapsulation"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question2 = Question.builder()
                .category(categoryStorage.getById(2))
                .type(Type.BOOLEAN)
                .question("The JVM compiles the program to bytecode.")
                .correctAnswer("false")
                .incorrectAnswer("true")
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question3 = Question.builder()
                .category(categoryStorage.getById(2))
                .type(Type.MULTIPLE)
                .question("Which is the default access modifier in a class?")
                .correctAnswer("package private")
                .incorrectAnswers(Arrays.asList("public", "private", "there's no default access modifier"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question4 = Question.builder()
                .category(categoryStorage.getById(2))
                .type(Type.MULTIPLE)
                .question("______ compares the two given strings based on the data/content of the string.")
                .correctAnswer("equals()")
                .incorrectAnswers(Arrays.asList("compare()", "==", "==="))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question5 = Question.builder()
                .category(categoryStorage.getById(2))
                .type(Type.BOOLEAN)
                .question("Converting an object of a wrapper type to its corresponding primitive value is called unboxing.")
                .correctAnswer("true")
                .incorrectAnswer("false")
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question6 = Question.builder()
                .category(categoryStorage.getById(4))
                .type(Type.BOOLEAN)
                .question("Database normalization is the process of deleting not valid data from the database.")
                .correctAnswer("false")
                .incorrectAnswer("true")
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question7 = Question.builder()
                .category(categoryStorage.getById(2))
                .type(Type.MULTIPLE)
                .question("Which is a valid keyword in java?")
                .correctAnswer("interface")
                .incorrectAnswers(Arrays.asList("string", "Float", "unsigned"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question8 = Question.builder()
                .category(categoryStorage.getById(2))
                .type(Type.MULTIPLE)
                .question("Which is the valid declaration within an interface definition?")
                .correctAnswer("public double methoda();")
                .incorrectAnswers(Arrays.asList("public final double methoda();", "static void methoda(double d1);", "protected void methoda(double d1);"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question9 = Question.builder()
                .category(categoryStorage.getById(2))
                .type(Type.MULTIPLE)
                .question("Which is a valid declarations of a String?")
                .correctAnswer("String s1 = null;")
                .incorrectAnswers(Arrays.asList("String s2 = 'null';", "String s3 = (String) 'abc';", "String s4 = (String) '\\ufeed';"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question10 = Question.builder()
                .category(categoryStorage.getById(6))
                .type(Type.BOOLEAN)
                .question("Linked list is a data structure where multiple lists are linked to each other.")
                .correctAnswer("false")
                .incorrectAnswer("true")
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question11 = Question.builder()
                .category(categoryStorage.getById(2))
                .type(Type.MULTIPLE)
                .question("How do you insert comments in Java code?")
                .correctAnswer("// Comment")
                .incorrectAnswers(Arrays.asList("# Comment", "<!-- Comment -->", "\"\"\"Comment\"\"\""))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question12 = Question.builder()
                .category(categoryStorage.getById(2))
                .type(Type.MULTIPLE)
                .question("How do you create a variable with the floating number 2.8?")
                .correctAnswer("float x = 2.8f")
                .incorrectAnswers(Arrays.asList("float x = 2.8", "x = 2.8f", "x = 2.8"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question13 = Question.builder()
                .category(categoryStorage.getById(2))
                .type(Type.BOOLEAN)
                .question("The value of a string variable can be surrounded by single quotes.")
                .correctAnswer("false")
                .incorrectAnswer("true")
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question14 = Question.builder()
                .category(categoryStorage.getById(2))
                .type(Type.MULTIPLE)
                .question("Which method can be used to return a string in upper case letters?")
                .correctAnswer("toUpperCase()")
                .incorrectAnswers(Arrays.asList("upperCase()", "tuc()", "uppercase()"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
                .isValidated(true)
                .appUser(appUser)
                .build();

        Question question15 = Question.builder()
                .category(categoryStorage.getById(2))
                .type(Type.MULTIPLE)
                .question("What is the correct way to create an object called myObj of MyClass?")
                .correctAnswer("MyClass myObj = new MyClass();")
                .incorrectAnswers(Arrays.asList(
                        "class myObj = new MyClass();",
                        "new myObj = MyClass();",
                        "class MyClass = new myObj();"))
                .creationDate(LocalDate.now())
                .validationDate(LocalDate.now())
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
                question14,
                question15
        );

        questionStorage.addAll(questions);
        CustomQuiz oop = CustomQuiz.builder()
                .name("OOP - Java module")
                .questions(questions)
                .appUser(appUser)
                .creationDate(LocalDate.now())
                .build();
        customQuizStorage.add(oop);
    }
}
