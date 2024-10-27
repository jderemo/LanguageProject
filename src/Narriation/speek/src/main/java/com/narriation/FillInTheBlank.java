package com.narriation;

import java.util.List;

public class FillInTheBlank extends Question {

    private String question;
    private String exerciseID;
    private List<String> userOptions;
    private String correctAnswer;

    public FillInTheBlank(String question, String exerciseID, List<String> userOptions, String correctAnswer) {
        this.question = question;
        this.exerciseID = exerciseID;
        this.userOptions = userOptions;
        this.correctAnswer = correctAnswer;
        expandWordBank(correctAnswer); // Add correct answer to wordBank
    }

    @Override
    public void startExercise(String exerciseID) {
        this.exerciseID = exerciseID;
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        return userAnswer.equals(correctAnswer);
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
    public String getExerciseID() {
        return this.exerciseID;
    }

    public List<String> getUserOptions() {
        return this.userOptions;
    }
}
