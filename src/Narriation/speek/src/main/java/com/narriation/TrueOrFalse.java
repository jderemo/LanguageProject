package com.narriation;

import java.util.List;


public class TrueOrFalse extends Question {

    private String questionText;
    private String exerciseID;
    private List<String> userOptions;
    private List<String> wordBank;
    private String correctAnswer;

    public TrueOrFalse(String questionText, String exerciseID, List<String> userOptions, String correctAnswer) {
        this.questionText = questionText;
        this.exerciseID = exerciseID;
        this.userOptions = userOptions;
        this.correctAnswer = correctAnswer;
        this.wordBank = wordBank;
        expandWordBank(correctAnswer); // Add the correct answer to the wordBank list
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
    public boolean checkAnswer(String userAnswer) {
        return getWordBank().contains(userAnswer); // Check if the userAnswer is in wordBank    }
    }
    
    @Override
    public String getExerciseID() {
        return this.exerciseID;
    }

    @Override
    public List<String> getUserOptions() {
        return List.of("True", "False"); // Options for True/False questions
    }

    @Override
    public void startExercise(String exerciseID) {
        this.exerciseID = exerciseID; // Set the exerciseID when starting the exercise
    }
}


