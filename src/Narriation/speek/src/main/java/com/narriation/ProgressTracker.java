package com.narriation;

public class ProgressTracker {
    private String exerciseID;   // Field to hold exercise ID
    private double progress;     // Field to hold the progress value
    private int userID;          // Field to hold user ID
    private String language;     // Field to hold language

    // Constructor with language parameter
    public ProgressTracker(String exerciseID, double progress, int userID, String language) {
        this.exerciseID = exerciseID;
        this.progress = progress;
        this.userID = userID;
        this.language = language;
    }

    // Getter for exerciseID
    public String getExerciseID() {
        return exerciseID;
    }

    // Getter for progress
    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    // Getter for userID
    public int getUserID() {
        return userID;
    }

    // Getter for language
    public String getLanguage() {
        return language;
    }

    // Method to update progress
    public void updateProgress(double increment) {
        this.progress += increment;  // Increment progress
    }

    public void increaseProgress(double amount) {
        this.progress += amount; // Assuming progress is a field in ProgressTracker
    }
}
