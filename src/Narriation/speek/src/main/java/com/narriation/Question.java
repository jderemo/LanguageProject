package com.narriation;

import java.util.ArrayList;
import java.util.List;

public abstract class Question {

    private static List<String> wordBank = new ArrayList<>();

    public List<String> getWordBank() {
        return wordBank;
    }

    public static void expandWordBank(String correctAnswer) {
        wordBank.add(correctAnswer);
    }

    public abstract void startExercise(String exerciseID);

    public abstract boolean checkAnswer(String userAnswer);

    public abstract String getQuestion();

    public abstract String getAnswer();

    public abstract String getExerciseID();

    public abstract List<String> getUserOptions();
}
