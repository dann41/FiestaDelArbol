package com.dglapps.fiestadelarbol.repositories;

import com.dglapps.fiestadelarbol.domain.Question;
import com.dglapps.fiestadelarbol.repositories.parser.QuestionParser;
import com.dglapps.fiestadelarbol.repositories.parser.csv.CSVQuestionParser;
import com.dglapps.fiestadelarbol.repositories.parser.json.JsonQuestionParser;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GoogleDriveQuestionRepository implements QuestionRepository{

    private static class CSV {
        private static final String FORMAT = "csv";
        private static final QuestionParser PARSER = new CSVQuestionParser();
    }

    private static class JSON {
        private static final String FORMAT = "json";
        private static final QuestionParser PARSER = new JsonQuestionParser();
    }

    private static final String FORMAT = CSV.FORMAT;
    private static final QuestionParser PARSER = CSV.PARSER;

    private static final String SHEET_NAME = "asd";

    //private static final String URL_FILE = "https://docs.google.com/spreadsheets/d/1Vq9Y3-VAVVxStE1YeBeRd2YluauIKHUxjQ--pqL-TBQ/gviz/tq?tqx=out:" + FORMAT + "&sheet=" + SHEET_NAME;
    private static final String URL_FILE = "https://docs.google.com/spreadsheets/u/1/d/1Vq9Y3-VAVVxStE1YeBeRd2YluauIKHUxjQ--pqL-TBQ/export?format=csv&id=1Vq9Y3-VAVVxStE1YeBeRd2YluauIKHUxjQ--pqL-TBQ&gid=0";

    private final List<Question> questionList;

    public GoogleDriveQuestionRepository() {
        questionList = new ArrayList<>();
    }

    @Override
    public List<Question> getAllQuestions() {
        if (questionList.isEmpty()) {
            questionList.addAll(PARSER.parse(getReader()));
        }
        return questionList;
    }

    private HttpsURLConnection getURLConnection() {
        try {
            URL url = new URL(URL_FILE);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setReadTimeout(60 * 1000);
            urlConnection.setConnectTimeout(60 * 1000);
            urlConnection.setDoInput(true);
            urlConnection.setSSLSocketFactory((SSLSocketFactory) SSLSocketFactory.getDefault());
            urlConnection.connect();
            return urlConnection;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Reader getReader() {
        HttpsURLConnection urlConnection = getURLConnection();

        try {
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = urlConnection.getInputStream();
                return new InputStreamReader(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
