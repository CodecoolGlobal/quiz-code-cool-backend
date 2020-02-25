package com.codecool.codecoolquiz.config;

import com.codecool.codecoolquiz.model.Category;
import com.codecool.codecoolquiz.model.CustomQuiz;
import com.codecool.codecoolquiz.model.Question;
import com.codecool.codecoolquiz.service.CategoryStorage;
import com.codecool.codecoolquiz.service.CustomQuizStorage;
import com.codecool.codecoolquiz.service.QuestionStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class Initializer {

    @Autowired
    CategoryStorage categoryStorage;

    @Autowired
    QuestionStorage questionStorage;

    @Autowired
    CustomQuizStorage customQuizStorage;

    public void loadInitData() throws Exception {
        loadInitCategories();
        loadInitQuestions();
        loadInitCustomQuizzes();
    }

    private void loadInitCategories() throws Exception {
        categoryStorage.add(new Category(1, "Python"));
        categoryStorage.add(new Category(2, "Java"));
        categoryStorage.add(new Category(3, "CSS"));
        categoryStorage.add(new Category(4, "SQL"));
        categoryStorage.add(new Category(5, "HTML"));
        categoryStorage.add(new Category(6, "General"));
    }

    private void loadInitQuestions() throws Exception {
        Question question1 = new Question(categoryStorage.getById(1),
                "multiple",
                "Which company was established on April 1st, 1976 by Steve Jobs, Steve Wozniak and Ronald Wayne?",
                "Apple",
                Arrays.asList("Microsoft", "Atari", "Commodore"));
        Question question2 = new Question(
                categoryStorage.getById(1),
                "multiple",
                "In any programming language, what is the most common way to iterate through an array?",
                "For loops",
                Arrays.asList("If Statements", "Do-while loops", "While loops")
        );
        Question question3 = new Question(
                categoryStorage.getById(2),
                "multiple",
                "Which is a valid keyword in java?",
                "interface",
                Arrays.asList("string", "Float", "unsigned")
        );
        Question question4 = new Question(
                categoryStorage.getById(2),
                "multiple",
                "Which is the valid declarations within an interface definition?",
                "public double methoda();",
                Arrays.asList("public final double methoda();", "static void methoda(double d1);", "protected void methoda(double d1);")
        );
        Question question5 = new Question(
                categoryStorage.getById(2),
                "multiple",
                "Which is a valid declarations of a String?",
                "String s1 = null;",
                Arrays.asList("String s2 = 'null';", "String s3 = (String) 'abc';", "String s4 = (String) '\\ufeed';")
        );
        Question question6 = new Question(
                categoryStorage.getById(1),
                "multiple",
                "What is output for − 'search'. find('S') ?",
                "interface",
                Arrays.asList("s", "-1", "''")
        );
        Question question7 = new Question(
                categoryStorage.getById(1),
                "multiple",
                "Which of the following is an invalid variable?",
                "1st_string",
                Arrays.asList("my_string_1", "foo", "_")
        );
        Question question8 = new Question(
                categoryStorage.getById(1),
                "multiple",
                "Which of the following cannot be a variable?",
                "in",
                Arrays.asList("__init__", "it", "on")
        );
        Question question9 = new Question(
                categoryStorage.getById(3),
                "multiple",
                "Which of the following property is used to change the face of a font?",
                "font-family",
                Arrays.asList("font-style", "font-variant", "font-weight")
        );
        Question question10 = new Question(
                categoryStorage.getById(3),
                "multiple",
                "Which of the following property of a anchor element signifies visited hyperlinks?",
                ":visited",
                Arrays.asList(":link", ":hover", ":active")
        );
        Question question11 = new Question(
                categoryStorage.getById(3),
                "multiple",
                "Which of the following selector selects an element that has no children?",
                ":empty",
                Arrays.asList(":nochild", ":inheritance", ":no-child")
        );
        Question question12 = new Question(
                categoryStorage.getById(4),
                "multiple",
                "What is returned by TRUNC(789.8389, 2)?",
                "789.83",
                Arrays.asList("789.84", "78", "789.00")
        );
        Question question13 = new Question(
                categoryStorage.getById(4),
                "multiple",
                "Which SQL function is used to count the number of rows in a SQL query?",
                "COUNT(*)",
                Arrays.asList("SUM()", "NUMBER()", "COUNT()")
        );
        Question question14 = new Question(
                categoryStorage.getById(4),
                "multiple",
                "______ removes all rows from a table without logging the individual row deletions.",
                "TRUNCATE",
                Arrays.asList("DROP", "REMOVE", "DELETE")
        );
        Question question15 = new Question(
                categoryStorage.getById(5),
                "boolean",
                "Choose the correct HTML element for the largest heading.",
                "<h1>",
                Arrays.asList("<h6>", "<head>", "<heading>")
        );
        Question question16 = new Question(
                categoryStorage.getById(5),
                "boolean",
                "Choose the correct HTML element to define important text.",
                "<strong>",
                Arrays.asList("<b>", "<i>", "<important>")
        );
        Question question17 = new Question(
                categoryStorage.getById(5),
                "boolean",
                "How can you make a numbered list?",
                "<ol>",
                Arrays.asList("<ul>", "<dl>", "<list>")
        );
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

    public void loadInitCustomQuizzes() {
        CustomQuiz customQuiz1 = new CustomQuiz(1, "Eszti");
        CustomQuiz customQuiz2 = new CustomQuiz(2, "Mira");
        CustomQuiz customQuiz3 = new CustomQuiz(3, "Droszi");
        CustomQuiz customQuiz4 = new CustomQuiz(4, "Stofi");
        for(int i = 0; i < questionStorage.getAll().size(); i++) {
            if (i < 4) {
                customQuiz1.add(questionStorage.getAll().get(i));
            }
            if (i >= 4 && i < 8) {
                customQuiz2.add(questionStorage.getAll().get(i));
            }
            if (i >= 8 && i < 12) {
                customQuiz3.add(questionStorage.getAll().get(i));
            }
            if (i >= 12 && i < questionStorage.getAll().size()) {
                customQuiz4.add(questionStorage.getAll().get(i));
            }
        }
        customQuizStorage.add(customQuiz1);
        customQuizStorage.add(customQuiz2);
        customQuizStorage.add(customQuiz3);
        customQuizStorage.add(customQuiz4);
    }
}
