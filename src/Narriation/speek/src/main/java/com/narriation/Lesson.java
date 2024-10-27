package com.narriation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lesson {
    Scanner keyboard = new Scanner(System.in);
    // Variables
    private int lessonID;
    private String language;
    private Difficulty difficultyLevel; // Assuming Difficulty is an enum or class
    private String content;
    private String duration;
    private List<Question> questions; // Use List<Question> for questions

    // Constructor
    public Lesson(int lessonID, String language, Difficulty difficultyLevel, String content, String duration, List<Question> questions) {
        this.lessonID = lessonID;
        this.language = language;
        this.difficultyLevel = difficultyLevel;
        this.content = content;
        this.duration = duration;
        this.questions = questions != null ? questions : new ArrayList<>(); // Initialize the questions list
    }

    public void startLesson(String exerciseID, ArrayList<Quiz> quizzes) {
        for (Quiz quiz : quizzes) {
            for (Question question : quiz.getQuestion()) {
                if (question.getExerciseID().equals(exerciseID)) {
                    System.out.println(question.getQuestion());  // Prints the question text
                    return;  // Exit once the question is found
                }
            }
        }
        System.out.println("Question not found for exerciseID: " + exerciseID);  // If no match found
    }
    
    
    // Getters and Setters
    public int getLessonID() {
        return lessonID;
    }

    public void setLessonID(int lessonID) {
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
}
