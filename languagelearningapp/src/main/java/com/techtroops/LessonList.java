package com.techtroops;

import java.util.ArrayList;

public class LessonList {

    // Variables
    private static LessonList instance;
    private ArrayList<Lesson> lessons;

    // Constructor
    private LessonList() {
        this.lessons = DataLoader.loadLessons();
    }

    public static LessonList getInstance(){
        if (instance == null){
            instance = new LessonList();
        }
        return instance;
    }

    // Method to add a lesson
    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    // Method to remove a lesson by ID
    public void removeLesson(String lessonID) {
        lessons.removeIf(lesson -> lesson.getLessonID() == lessonID);
    }

    // Method to get a lesson by ID
    public Lesson getLesson(String lessonID) {
        for (Lesson lesson : lessons) {
            if (lesson.getLessonID().equals(lessonID)) {
                return lesson;
            }
        }
        return null; // Return null if lesson not found
    }

    // Method to get all lessons
    public ArrayList<Lesson> getLessons(){
        return lessons;
    }

    public ArrayList<Lesson> getLessonsByLanguage(String language){
        ArrayList<Lesson> sortedLessons = new ArrayList<Lesson>();
        for(Lesson l : lessons){
            if (l.getLanguage().equals(language)){
                sortedLessons.add(l);
            }
        }
        return sortedLessons;
    }
}
