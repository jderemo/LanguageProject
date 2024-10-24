package com.narriation;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        // Load users with the DataLoader
        ArrayList<User> users = DataLoader.loadUsers();
        // Check if users were successfully loaded
        if (users != null && !users.isEmpty()) {
            System.out.println("Users loaded successfully:");

            // Print out each user's username
            for (User user : users) {
                System.out.println("Username: " + user.getUsername());
                System.out.println("Password: " + user.getPassword());
                System.out.println("---------------------");
            }
        } else {
            System.out.println("No users were loaded or the list is empty.");
        }

        //Load lessons with the DataLoader
        System.out.println("Loading lessons...");
        System.out.println();
        ArrayList<Lesson> lessons = DataLoader.loadLessons();

        if (lessons != null && !lessons.isEmpty()) {
            System.out.println("Theses lessons have been loaded: ");

            for (Lesson lesson : lessons) {
                System.out.println("Lesson ID: " + lesson.getLessonID());
                System.out.println("Language: " + lesson.getLanguage());
                System.out.println("Difficulty Level: " + lesson.getDifficultyLevel());
                System.out.println("Content: " + lesson.getContent());
                System.out.println("---------------------");
            }
        } else {
            System.out.println("No lessons were loaded.");
        }

        LessonList lessonList = new LessonList();
        lessonList.addLesson(new Lesson(1, "Spanish", Difficulty.BASIC, "This lesson is an introduction to the language of Spanish.", "Short", new ArrayList<>()));
        lessonList.addLesson(new Lesson(2, "Spanish", Difficulty.BASIC, "This lesson is an introduction to the basics of grammar in Spanish.", "Medium", new ArrayList<>()));
        lessonList.addLesson(new Lesson(11, "Spanish", Difficulty.ADVANCED, "This lesson is a continuation of more advanced Spanish grammar concepts.", "Long", new ArrayList<>()));

        System.out.println("Choose a lesson to start!");

        int lessonID = keyboard.nextInt();
        Lesson selectedLesson = lessonList.getLesson(lessonID);

        if (selectedLesson != null) {
            System.out.println("You have selected: " + selectedLesson.getContent());
            selectedLesson.startLesson();
        } else {
            System.out.println("Lesson " + lessonID + " couldn't be loaded");
        }
        keyboard.close();
    }
}



