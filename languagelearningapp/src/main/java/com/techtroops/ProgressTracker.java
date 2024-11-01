package com.techtroops;

import java.util.ArrayList;

public class ProgressTracker {
    private String language;                        // Field to hold language
    private ArrayList<String> completedLessons;     //Field to hold completed lessons
    private double progress;                       // Field to hold user ID

    // Constructor with language parameter
    public ProgressTracker(double progress, String language, ArrayList<String> completedLessons) {
        this.progress = progress;
        this.language = language;
        this.completedLessons = completedLessons;
    }

    // To String method
    public String toString(){
        String lessons = "";
        for(String s : completedLessons){
            lessons+= s + ", ";
        }
        return "\nLanguage: " + language + ", Completed Lessons: " + lessons + "Progress: " + progress;
    }


    // Getter for progress
    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    // Getter for language
    public String getLanguage() {
        return language;
    }

    public ArrayList<String> getCompletedLessons(){
        return completedLessons;
    }

    // Method to update progress
    public void completeLesson(Lesson l) {
        if (completedLessons.contains(l.getLessonID())){
            System.out.println("User already has completed the lesson: " + l.getLessonID());
            return;
        }
    }
}
