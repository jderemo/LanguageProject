package com.narriation;

import java.util.ArrayList;

public class ProgressTracker {

    // Variables
    private String language;
    private double progress;
    private ArrayList<Lesson> completedLessons;
    private ArrayList<Lesson> incompleteLessons;

    // Constructor
    public ProgressTracker(String lessonID, double progress) {
        this.language = language;
        this.progress = 0.0;
        this.completedLessons = new ArrayList<>();
        this.incompleteLessons = new ArrayList<>();
    }

    // Method Stubs
    public void updateProgress(String language, int lessonId) {
        // Logic to update progress based on lesson completion
    }

    // Getters and Setters
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public ArrayList<Lesson> getCompletedLessons() {
        return completedLessons;
    }

    public void setCompletedLessons(ArrayList<Lesson> completedLessons) {
        this.completedLessons = completedLessons;
    }

    public ArrayList<Lesson> getIncompleteLessons() {
        return incompleteLessons;
    }

    public void setIncompleteLessons(ArrayList<Lesson> incompleteLessons) {
        this.incompleteLessons = incompleteLessons;
    }
}
