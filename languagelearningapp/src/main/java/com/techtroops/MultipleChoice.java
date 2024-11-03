package com.techtroops;

import java.util.List;

/**
 * Multiple choice question type
 */
public class MultipleChoice extends Question {
    private String questionText;
    private String questionID;
    private List<String> userOptions;
    private String correctAnswer;

    // Constructor
    public MultipleChoice(String questionText, String questionID, List<String> userOptions, String correctAnswer) {
        this.questionText = questionText;
        this.questionID = questionID;
        this.userOptions = userOptions;
        this.correctAnswer = correctAnswer;
    }

    // Getters and Setters
    @Override
    public void setQuestionId(String questionID) {
        this.questionID = questionID;
    }

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
        return this.userOptions;
    }


    
}
