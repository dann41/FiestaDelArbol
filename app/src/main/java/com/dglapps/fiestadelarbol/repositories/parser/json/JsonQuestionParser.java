package com.dglapps.fiestadelarbol.repositories.parser.json;

import android.util.Log;
import com.dglapps.fiestadelarbol.domain.Question;
import com.dglapps.fiestadelarbol.repositories.CategoriesMap;
import com.dglapps.fiestadelarbol.repositories.parser.QuestionParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonQuestionParser implements QuestionParser {

    private static final String PREFIX = "/*O_o*/google.visualization.Query.setResponse(";
    private static final String SUFFIX = ");";

    private final CategoriesMap categoriesMap;

    public JsonQuestionParser() {
        categoriesMap = new CategoriesMap();
    }

    @Override
    public List<Question> parse(Reader reader) {
        try {
            BufferedReader br = new BufferedReader(reader);
            StringBuilder result = new StringBuilder();
            String line;
            while((line = br.readLine()) != null) {
                result.append(line);
            }
            return parse(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public List<Question> parse(String content) {
        if (content == null) {
            return Collections.emptyList();
        }

        String json = content.substring(PREFIX.length(), content.length() - SUFFIX.length());

        List<Question> list = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            CSVDocument document = objectMapper.readValue(json, CSVDocument.class);
            for (CSVRow row : document.getTable().getRows()) {
                Question question = parseQuestion(row);
                if (question != null) {
                    list.add(question);
                } else {
                    Log.e("GDRIVE", "Skip question " + row.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Question parseOne(String content) {
        try {
            CSVRow row = new ObjectMapper().readValue(content, CSVRow.class);
            return parseQuestion(row);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Question parseQuestion(CSVRow row) {
        List<CSVValue> cells = row.getCells();

        if (cells.size() >= 3) {
            int categoryId = categoriesMap.get(getValue(cells.get(0)));
            String question = getValue(cells.get(1));
            String answer = getValue(cells.get(2));
            return new Question(question, answer, categoryId);
        }
        return null;
    }

    private String getValue(CSVValue value) {
        return value != null ? value.getValue().trim() : null;
    }
}
