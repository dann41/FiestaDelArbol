package com.dglapps.fiestadelarbol.services;

import android.content.Context;
import android.util.Log;
import com.dglapps.fiestadelarbol.domain.Category;
import com.dglapps.fiestadelarbol.domain.Question;
import com.dglapps.fiestadelarbol.repositories.GoogleDriveQuestionRepository;
import com.dglapps.fiestadelarbol.repositories.LocalQuestionRepository;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import java.util.*;
import java.util.concurrent.Callable;

public class QuestionService {

    private List<Question> originalQuestions;
    private Map<Integer, List<Question>> questionsByCategory;

    public QuestionService() {
        questionsByCategory = new HashMap<>();
    }

    public Single<Integer> loadQuestions(final Context context) {
        return Single.fromCallable(new Callable<Integer>() {
            @Override
            public Integer call() {
                loadFromDrive();
                if (originalQuestions.isEmpty()) {
                    Log.i("QUIZ", "No questions found from internet, trying local file");
                    loadLocally(context);
                }
                buildMap(originalQuestions);
                return originalQuestions.size();
            }
        }).observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io());

    }

    private void loadFromDrive() {
        originalQuestions = new GoogleDriveQuestionRepository().getAllQuestions();
    }

    private void loadLocally(Context context) {
        originalQuestions = new LocalQuestionRepository(context).getAllQuestions();
    }

    private void buildMap(List<Question> questions) {
        questionsByCategory.clear();
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
