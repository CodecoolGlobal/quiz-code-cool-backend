package com.codecool.codecoolquiz;

import com.codecool.codecoolquiz.model.Question;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class Util {

    public List<Question> getRandomQuestionsFromList(List<Question> list, int numOfNeededItems) {

        if (list.size() == 0 ||
            numOfNeededItems == 0 ||
            numOfNeededItems > list.size()) {
            return null;
        }

        Random rand = new Random();

        List<Question> copyList = new ArrayList<>(list);
        List<Question> newList = new ArrayList<>();

        for (int i = 0; i < numOfNeededItems; i++) {

            int randomIndex = rand.nextInt(copyList.size());
            newList.add(copyList.get(randomIndex));
            copyList.remove(randomIndex);
        }
        return newList;
    }

}
