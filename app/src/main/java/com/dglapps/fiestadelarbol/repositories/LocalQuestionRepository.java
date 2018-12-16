package com.dglapps.fiestadelarbol.repositories;

import android.content.Context;
import com.dglapps.fiestadelarbol.domain.Question;
import com.dglapps.fiestadelarbol.repositories.parser.csv.CSVQuestionParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LocalQuestionRepository implements QuestionRepository{

    private static final String QUESTIONS_FILENAME = "preguntas.csv";

    private final List<Question> questionList;
    private final Context context;
    private final CSVQuestionParser questionParser;

    public LocalQuestionRepository(Context context) {
        this.context = context;
        questionList = new ArrayList<>();
        questionParser = new CSVQuestionParser();
    }

    @Override
    public List<Question> getAllQuestions() {
        if (questionList.isEmpty()) {
            questionList.addAll(readQuestionsFile());
        }
        return questionList;
    }

    private List<Question> readQuestionsFile() {
        try {
            InputStream is = context.getAssets().open(QUESTIONS_FILENAME);
            return questionParser.parse(new InputStreamReader(is));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

}
