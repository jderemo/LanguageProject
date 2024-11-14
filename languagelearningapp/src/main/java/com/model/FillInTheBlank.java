package com.model;

import java.util.List;

/**
 * Fill In The Blank Question type
 */
public class FillInTheBlank extends Question {

    private String question;
    private String questionID;
    private List<String> userOptions;
    private String correctAnswer;

    // Constructor
    public FillInTheBlank(String question, String questionID, List<String> userOptions, String correctAnswer) {
        this.question = question;
        this.questionID = questionID;
        this.userOptions = userOptions;
    }

    // Getters and Setters
    @Override
    public void setQuestionId(String questionID) {
        this.questionID = questionID;
    }

    @Override
    public String getQuestion() {
        return this.question;
    }

    @Override
    public String getAnswer() {
        return this.correctAnswer;
    }

    @Override
    public String getQuestionID() {
        return this.questionID;
    }

    public List<String> getUserOptions() {
        return this.userOptions;
    }
}
