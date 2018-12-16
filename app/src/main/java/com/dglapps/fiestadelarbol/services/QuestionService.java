package com.dglapps.fiestadelarbol.services;

import android.content.Context;
import com.dglapps.fiestadelarbol.domain.Category;
import com.dglapps.fiestadelarbol.domain.Question;
import com.dglapps.fiestadelarbol.repositories.QuestionRepository;

import java.util.*;

public class QuestionService {

    private List<Question> originalQuestions;
    private Map<Integer, List<Question>> questionsByCategory;

    public QuestionService() {
        questionsByCategory = new HashMap<>();
    }

    public void loadQuestions(Context context) {
        originalQuestions = new QuestionRepository(context).getAllQuestions();
        buildMap(originalQuestions);
    }

    private void buildMap(List<Question> questions) {
        for (Question q : questions) {
            List<Question> categoryList = questionsByCategory.get(q.getCategoryId());
            if (categoryList == null) {
                categoryList = new ArrayList<>();
                questionsByCategory.put(q.getCategoryId(), categoryList);
            }
            categoryList.add(q);
        }

        // Shuffle all questions
        for (List<Question> list : questionsByCategory.values()) {
            Collections.shuffle(list);
        }
    }

    public Question getNextQuestionForCategory(Category category) {
        List<Question> questions = questionsByCategory.get(category.getId());
        if (questions.isEmpty())
            return null;

        if (category.isRepetitionAllowed()) {
            return questions.get(new Random().nextInt(questions.size()));
        } else {
            return questions.remove(0);
        }
    }

    public void reset() {
        buildMap(originalQuestions);
    }
}
