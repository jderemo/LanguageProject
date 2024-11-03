package com.techtroops;

import java.util.List;

/**
 * Quiz class that holds questions that relate
 * to the lesson specified in the Lesson ID
 */
public class Quiz {
    private String quizID;
    private String languguage;
    private String lessonID;
    private List<Question> questionsList;
    private String result;

    // Constructor
    public Quiz(String quizID, String language, String lessonID, List<Question> questionsList) {
        this.quizID = quizID;
        this.questionsList = questionsList;
        this.languguage = language;
        this.result = "";
        this.lessonID = lessonID;
    }

    /**
     * Gets the question at the specified index
     * @param index index of question
     * @return the question if it exists
     */
    public Question getQuestion(int index) {
        if (index >= 0 && index < questionsList.size()) {
            return questionsList.get(index);
        }
        return null;
    }

    // Getters and Setters
    
    public String getQuizID() {
        return quizID;
    }

    public String getQuizGrade() {
        return result;
    }

    public void addQuestion(Question question) {
        questionsList.add(question);
    }

    public List<Question> getQuestionsList(){
        return this.questionsList;
    }

    public String getLanguage(){
        return languguage;
    }

    public void setLanguage(String language){
        this.languguage = language;
    }

    public String getLessonID(){
        return lessonID;
    }

    public void setLessonID(String lessonID){
        this.lessonID = lessonID;
    }
}
