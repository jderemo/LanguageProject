package com.model;

import java.util.List;
/**
 * Base question class
 */
public abstract class Question {

    public abstract void setQuestionId(String questionID);

    public abstract String getQuestion();

    public abstract String getAnswer();

    public abstract String getQuestionID();

    public abstract List<String> getUserOptions();
}
