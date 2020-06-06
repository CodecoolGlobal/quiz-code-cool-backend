package com.codecool.codecoolquiz.service;

import com.codecool.codecoolquiz.model.AppUser;
import com.codecool.codecoolquiz.model.CustomQuiz;
import com.codecool.codecoolquiz.model.Question;
import com.codecool.codecoolquiz.model.RequestResponseBody.CustomQuizRequestBody;
import com.codecool.codecoolquiz.model.RequestResponseBody.CustomQuizResponseBody;
import com.codecool.codecoolquiz.model.RequestResponseBody.QuestionBody;
import com.codecool.codecoolquiz.model.exception.CustomQuizNameAlreadyExistException;
import com.codecool.codecoolquiz.model.exception.NotFoundException;
import com.codecool.codecoolquiz.repository.CustomQuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomQuizStorage {

    @Autowired
    AppUserStorage appUserStorage;

    @Autowired
    CustomQuizRepository customQuizRepository;

    @Autowired
    QuestionStorage questionStorage;

    public void add(CustomQuiz customQuiz) {
        customQuizRepository.save(customQuiz);
    }

    public void saveQuizByUserName(String username, CustomQuizRequestBody quizBody) {
        AppUser appUser = appUserStorage.getByName(username);
        String name = quizBody.getName();
        int[] questionIds = quizBody.getQuestionIds();
        List<Question> questionList = Arrays.stream(questionIds).mapToObj(e -> questionStorage.find(e)).collect(Collectors.toList());
        CustomQuiz customQuiz = CustomQuiz.builder()
                .name(name)
                .creationDate(LocalDate.now())
                .questions(questionList)
                .appUser(appUser)
                .build();
        try {
            add(customQuiz);
        } catch (DataIntegrityViolationException e) {
            throw new CustomQuizNameAlreadyExistException(name);
        }
    }

    public CustomQuiz find(int id) throws NotFoundException {
        return customQuizRepository.findById(id).orElseThrow(() -> new NotFoundException("Custom quiz not found."));
    }

    public List<Question> getCustomQuizQuestions(int id) {
        CustomQuiz customQuiz = find(id);
        return customQuiz.getQuestions();
    }

    public List<QuestionBody> getQuestionBodiesForCustomQuizById(int id) {
        List<Question> questions = getCustomQuizQuestions(id);
        Collections.shuffle(questions);
        return questions.stream().map(QuestionBody::new).collect(Collectors.toList());
    }

    public List<CustomQuizResponseBody> getCustomQuizResponseBodies() {
        List<CustomQuiz> customQuizzes = customQuizRepository.findAll();
        return customQuizzes.stream().map(CustomQuizResponseBody::new).collect(Collectors.toList());

    }

    public List<CustomQuizResponseBody> getCustomQuizResponseBodiesByUserId(int id) {
        List<CustomQuiz> customQuizzes = customQuizRepository.findAllByAppUserId(id);
        return customQuizzes.stream().map(CustomQuizResponseBody::new).collect(Collectors.toList());
    }

}
