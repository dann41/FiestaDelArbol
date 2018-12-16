package com.dglapps.fiestadelarbol.domain;

public class Question {

    private final String question;
    private final String answer;
    private final int categoryId;

    public Question(String question, String answer, int categoryId) {
        this.question = question;
        this.answer = answer;
        this.categoryId = categoryId;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public int getCategoryId() {
        return categoryId;
    }
}
