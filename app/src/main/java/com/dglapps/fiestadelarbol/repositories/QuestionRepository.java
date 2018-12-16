package com.dglapps.fiestadelarbol.repositories;

import android.content.Context;
import com.dglapps.fiestadelarbol.domain.Question;
import com.dglapps.fiestadelarbol.services.CategoryService;
import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionRepository {

    private static final int CATEGORY_FIELD = 0;
    private static final int QUESTION_FIELD = 1;
    private static final int ANSWER_FIELD = 2;

    private static final String QUESTIONS_FILENAME = "preguntas.csv";

    private final Map<String, Integer> categoriesMap;

    private List<Question> questionList;
    private final Context context;

    public QuestionRepository(Context context) {
        this.context = context;

        questionList = new ArrayList<>();

        categoriesMap = new HashMap<>();
        categoriesMap.put("RAYO", CategoryService.QUICK);
        categoriesMap.put("MUSICAL", CategoryService.MUSIC);
        categoriesMap.put("V-F", CategoryService.TRUE_FALSE);
        categoriesMap.put("ABIERTA", CategoryService.QUESTION);
        categoriesMap.put("HABILIDAD", CategoryService.SKILL);
    }

    public List<Question> getAllQuestions() {
        if (questionList.isEmpty()) {
            readQuestionsFile();
        }
        return questionList;
    }

    private void readQuestionsFile() {
        InputStream is = null;
        try {
            is = context.getAssets().open(QUESTIONS_FILENAME);
            CSVReader reader = new CSVReader(new InputStreamReader(is));
            String[] nextRecord;
            while ((nextRecord = reader.readNext()) != null) {
                Question question = parseRecord(nextRecord);
                questionList.add(question);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Question parseRecord(String[] nextRecord) {
        int categoryId = categoriesMap.get(nextRecord[CATEGORY_FIELD]);
        String question = nextRecord[QUESTION_FIELD];
        String answer = nextRecord[ANSWER_FIELD];
        return new Question(question, answer, categoryId);
    }

}
