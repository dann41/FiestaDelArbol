package com.dglapps.fiestadelarbol.repositories.parser.csv;

import android.util.Log;
import com.dglapps.fiestadelarbol.domain.Question;
import com.dglapps.fiestadelarbol.repositories.CategoriesMap;
import com.dglapps.fiestadelarbol.repositories.parser.QuestionParser;
import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CSVQuestionParser implements QuestionParser {


    private static final int CATEGORY_FIELD = 0;
    private static final int QUESTION_FIELD = 1;
    private static final int ANSWER_FIELD = 2;

    private final CategoriesMap categoriesMap;

    public CSVQuestionParser() {
        categoriesMap = new CategoriesMap();
    }

    @Override
    public List<Question> parse(Reader reader) {
        if (reader == null) {
            return Collections.emptyList();
        }

        List<Question> list = new ArrayList<>();
        try {
            CSVReader csvReader = new CSVReader(reader);
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                Question question = parseRecord(nextRecord);
                if (question == null) {
                    Log.e("CSV", "Couldn't parse question: " + Arrays.toString(nextRecord));
                } else {
                    list.add(question);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Question> parse(String content) {
        if (content == null) {
            return Collections.emptyList();
        }

        List<Question> list = new ArrayList<>();
        try {
            CSVReader reader = new CSVReader(new StringReader(content));
            String[] nextRecord;
            while ((nextRecord = reader.readNext()) != null) {
                Question question = parseRecord(nextRecord);
                if (question == null) {
                    Log.e("CSV", "Couldn't parse question: " + Arrays.toString(nextRecord));
                } else {
                    list.add(question);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Question parseOne(String content) {
        return parse(content).get(0);
    }

    private Question parseRecord(String[] nextRecord) {
        Integer categoryId = categoriesMap.get(nextRecord[CATEGORY_FIELD]);
        if (categoryId == null) {
            Log.e("CSV", "Category not found: " + nextRecord[CATEGORY_FIELD]);
            return null;
        }
        String question = nextRecord[QUESTION_FIELD];
        String answer = nextRecord[ANSWER_FIELD];
        return new Question(question, answer, categoryId);
    }
}
