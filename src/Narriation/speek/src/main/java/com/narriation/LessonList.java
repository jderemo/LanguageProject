package com.narriation;
import java.util.ArrayList;

public class LessonList {

    // Variables
    private ArrayList<Lesson> lessons;

    // Constructor
    public LessonList() {
        this.lessons = new ArrayList<>();
    }

    // Method to add a lesson
    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    // Method to remove a lesson by ID
    public void removeLesson(int lessonID) {
        lessons.removeIf(lesson -> lesson.getLessonID() == lessonID);
    }

    // Method to get a lesson by ID
    public Lesson getLesson(int lessonID) {
        for (Lesson lesson : lessons) {
            if (lesson.getLessonID() == lessonID) {
                return lesson;
            }
        }
        return null; // Return null if lesson not found
    }

    // Method to get all lessons
    public ArrayList<Lesson> getEveryLesson() {
        return new ArrayList<>(lessons); // Return a copy of the lessons list
    }
}
