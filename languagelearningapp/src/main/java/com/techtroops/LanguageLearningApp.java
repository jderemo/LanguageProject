package com.techtroops;

import java.util.Scanner;

/**
 * The Main class for the Language Learning App
 * Will allow to specify either the GUI version or the Console version
 * When main is run. For now, only the console version is implemented.
 */
public class LanguageLearningApp {

    // Variables
    public static Scanner scanner = new Scanner(System.in);
    private static UserFactory userFactory;
    private static LessonFactory lessonFactory;
    private static QuizFactory quizFactory;
    private static User user;

    // Main method
    public static void main(String[] args) {
        new LanguageLearningApp();
        return;
    }

    // Constructor
    private LanguageLearningApp() {
        user = null; // Initially no user is logged in

        // Initialize Factories
        userFactory = UserFactory.getInstance();
        lessonFactory = LessonFactory.getInstance();
        quizFactory = QuizFactory.getInstance();

        System.out.println("Welcome to our Language Learning App!");
        runProgram();
        scanner.close();
    }

    private void runProgram() {
        boolean quit = false;
        while (!quit) {
            if (user == null){
                System.out.println("What would you like to do? (register, login, quit)");
                switch (scanner.nextLine().toLowerCase()) {
                    case "register": {
                        userFactory.registerConsoleUi();
                        break;
                    }
                    case "login": {
                        user = userFactory.loginConsoleUi();
                        break;
                    }
                    case "quit": {
                        quit = true;
                        break;
                    }
                    default:{
                        System.out.println("That was an invalid choice, try again.");
                        break;
                    }
                }
            } else {
                System.out.println("What would you like to do? (lesson, quiz, logout)");
                switch (scanner.nextLine().toLowerCase()) {
                    case "lesson":{
                        lessonFactory.findLessonConsoleUi();
                        break;
                    }
                    case "quiz":{
                        quizFactory.listAvailableQuizzesConsoleUi();
                        break;
                    }
                    case "logout":{
                        userFactory.logout(user);
                        user = null;
                        break;
                    }
                    default:{
                        System.out.println("That was an invalid choice, try again.");
                        break;
                    }
                }
            }
        }
    }
    public static User getCurrentUser(){
        return user;
    }


    
}
