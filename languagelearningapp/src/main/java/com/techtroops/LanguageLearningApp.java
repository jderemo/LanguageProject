package com.techtroops;

import java.util.Scanner;

public class LanguageLearningApp {

    // Variables
    public static Scanner scanner = new Scanner(System.in);
    private static User user;

    // Main method
    public static void main(String[] args) {
        new LanguageLearningApp();
        return;
    }

    // Constructor
    private LanguageLearningApp() {
        user = null; // Initially no user is logged in

        System.out.println("Welcome to our Language Learning App!");
        idle();
        scanner.close();
    }

    private void idle() {
        boolean quit = false;
        while (!quit) {
            if (user == null){
                System.out.println("What would you like to do? (register, login, quit)");
                switch (scanner.nextLine().toLowerCase()) {
                    case "register": {
                        UserFactory.register();
                        break;
                    }
                    case "login": {
                        user = UserFactory.login();
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
                        LessonFactory.findLesson();
                        break;
                    }
                    case "quiz":{
                        QuizFactory.listAvailableQuizzes();
                        break;
                    }
                    case "logout":{
                        UserFactory.logout(user);
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
