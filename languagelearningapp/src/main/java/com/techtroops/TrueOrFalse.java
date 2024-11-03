package com.techtroops;

import java.util.ArrayList;
import java.util.List;

/**
 * True or False question class
 */
public class TrueOrFalse extends Question {
    private String questionText;
    private String questionID;
    private List<String> userOptions;
    private String correctAnswer;

    // Constructor
    public TrueOrFalse(String questionText, String questionID, List<String> userOptions, String correctAnswer) {
        this.questionText = questionText;
        this.questionID = questionID;
        ArrayList<String> options = new ArrayList<String>();
        options.add("True");
        options.add("False");
        this.userOptions = options;
        this.correctAnswer = correctAnswer;

    }

    // Getters and Setters
    @Override
    public String getQuestion() {
        return this.questionText;
    }

    @Override
    public String getAnswer() {
        return this.correctAnswer;
    }

    @Override
    public String getQuestionID() {
        return this.questionID;
    }

    @Override
    public List<String> getUserOptions() {
        return userOptions;
    }

    @Override
    public void setQuestionId(String questionID) {
        this.questionID = questionID;
    }
}


