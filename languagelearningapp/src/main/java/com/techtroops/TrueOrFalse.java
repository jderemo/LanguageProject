package com.techtroops;

import java.util.ArrayList;
import java.util.List;


public class TrueOrFalse extends Question {

    private String questionText;
    private String exerciseID;
    private List<String> userOptions;
    private String correctAnswer;

    public TrueOrFalse(String questionText, String exerciseID, List<String> userOptions, String correctAnswer) {
        this.questionText = questionText;
        this.exerciseID = exerciseID;
        ArrayList<String> options = new ArrayList<String>();
        options.add("True");
        options.add("False");
        this.userOptions = options;
        this.correctAnswer = correctAnswer;

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
    public String getExerciseID() {
        return this.exerciseID;
    }

    @Override
    public List<String> getUserOptions() {
        return userOptions;
    }

    @Override
    public void startExercise(String exerciseID) {
        this.exerciseID = exerciseID; // Set the exerciseID when starting the exercise
    }
}


