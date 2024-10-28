package com.narriation;

import java.util.List;

public abstract class Question {

    public abstract void startExercise(String exerciseID);

    public abstract String getQuestion();

    public abstract String getAnswer();

    public abstract String getExerciseID();

    public abstract List<String> getUserOptions();
}
