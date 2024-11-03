package com.techtroops;

import java.util.ArrayList;

/**
 * Singleton list of Lessons from DataLoader
 * Has a few helper methods that can sort the list
 */
public class LessonList {
    private static LessonList instance;
    private ArrayList<Lesson> lessons;

    // Constructor
    private LessonList() {
        lessons = DataLoader.loadLessons();
    }

    public static LessonList getInstance(){
        if (instance == null){
            instance = new LessonList();
        }
        return instance;
    }

    /**
     * Retrieves a lesson by a given ID
     * @param lessonID Lesson ID to search for
     * @return A lesson if there exists with the ID
     */
    public Lesson getLesson(String lessonID) {
        for (Lesson lesson : lessons) {
            if (lesson.getLessonID().equals(lessonID)) {
                return lesson;
            }
        }
        return null; // Return null if lesson not found
    }

    /**
     * Gets the ArrayList of lessons itself
     * @return The ArrayList of Lessons
     */
    public ArrayList<Lesson> getLessons(){
        return lessons;
    }

    /**
     * Gets an ArrayList of lessons that are for a specific language
     * @param language Language to look for
     * @return an ArrayList of lessons that are in the specified language. Returns null if the list is empty.
     */
    public ArrayList<Lesson> getLessonsByLanguage(String language){
        ArrayList<Lesson> sortedLessons = new ArrayList<Lesson>();
        for(Lesson l : lessons){
            if (l.getLanguage().equals(language)){
                sortedLessons.add(l);
            }
        }
        return sortedLessons.size() == 0 ? null : sortedLessons;
    }
}
