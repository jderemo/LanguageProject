package com.techtroops;

import java.util.ArrayList;
import java.util.List;

public class Lesson {
    // Variables
    private String lessonID;
    private String language;
    private Difficulty difficultyLevel; // Assuming Difficulty is an enum or class
    private String content;
    private String duration;
    private List<Question> questions; // Use List<Question> for questions

    // Constructor
    public Lesson(String lessonID, String language, Difficulty difficultyLevel, String content, String duration, List<Question> questions) {
        this.lessonID = lessonID;
        this.language = language;
        this.difficultyLevel = difficultyLevel;
        this.content = content;
        this.duration = duration;
        this.questions = questions != null ? questions : new ArrayList<>(); // Initialize the questions list
    }

    public void startLesson() {
        // Display lesson content
        System.out.println("Lesson Content: " + this.content);
        Narriator.playSound(content);
        System.out.println("Duration: " + this.duration);
        
        // Optionally, you can add code here to allow the user to choose a quiz or proceed in another way.
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

    public List<Question> getQuestions() {
        return questions;
    }
    public void displayContent() {
        System.out.println("Lesson Content: " + content); // Assuming 'content' holds the lesson content
        Narriator.playSound(content);
        System.out.println("Duration: " + duration);
        System.out.println("Difficulty Level: " + difficultyLevel);
    }

}