package com.model;

import java.util.ArrayList;

/**
 * A class to track the user's completed lessons and 
 * overall progress in a language
 */
public class ProgressTracker {
    private String language;
    private ArrayList<String> completedLessons;
    private double progress;

    // Constructor
    public ProgressTracker(double progress, String language, ArrayList<String> completedLessons) {
        this.progress = progress;
        this.language = language;
        this.completedLessons = completedLessons;
    }

    // toString method
    public String toString(){
        String lessons = "";
        for(String s : completedLessons){
            lessons+= s + ", ";
        }
        return "\nLanguage: " + language + ", Completed Lessons: " + lessons + "Progress: " + progress;
    }


    // Getters and Setters
    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public String getLanguage() {
        return language;
    }

    public ArrayList<String> getCompletedLessons(){
        return completedLessons;
    }

    /**
     * Adds a lesson to the progress tracker's completed lessons and gets the user's overall progress across the language.
     * @param lesson Lesson to complete
     */
    public boolean completeLesson(Lesson lesson) {
        if (completedLessons.contains(lesson.getLessonID())){
            System.out.println("User already has completed the lesson: " + lesson.getLessonID());
            return false;
        }
        completedLessons.add(lesson.getLessonID());
        progress = (double) completedLessons.size() / (double) LessonList.getInstance().getLessons().size();
        return true;
    }
}
