package com.techtroops;

/**
 * Lesson class that links to a quiz
 */
public class Lesson {
    private String lessonID;
    private String language;
    private Difficulty difficultyLevel; // Assuming Difficulty is an enum or class
    private String content;
    private String duration;
    private String quizID;

    // Constructor
    public Lesson(String lessonID, String language, Difficulty difficultyLevel, String content, String duration, String quizID) {
        this.lessonID = lessonID;
        this.language = language;
        this.difficultyLevel = difficultyLevel;
        this.content = content;
        this.duration = duration;
        this.quizID = quizID;
    }

    // Getters and Setters
    public String getLessonID() {
        return lessonID;
    }

    public void setLessonID(String lessonID) {
        this.lessonID = lessonID;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Difficulty getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(Difficulty difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getQuizID(){
        return quizID;
    }

    public void setQuizID(String quizID){
        this.quizID = quizID;
    }

}
