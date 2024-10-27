package com.narriation;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        // Load users with the DataLoader
        System.out.println("Would you like to create an account (y or n) or load existing accounts?");
        String registerUser = keyboard.nextLine();

        if (registerUser.equals("y")) {
            // Prompt user for account information
            System.out.println("Enter your desired username:");
            String username = keyboard.nextLine();
            System.out.println("Enter your email:");
            String email = keyboard.nextLine();
            System.out.println("Enter your password:");
            String password = keyboard.nextLine();
            System.out.println("Enter your preferred language:");
            String preferredLanguage = keyboard.nextLine();

            // Create a new User instance and register
            User newUser = new User(0, username, email, password, preferredLanguage, null, null); // userID is set to 0 for new users
            newUser.register(); // Call the register method

        } else if (registerUser.equals("n")) {
            // Load existing users
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
        } else {
            System.out.println("Invalid option. Exiting.");
            return;
        }

        // Load quizzes with the DataLoader
        System.out.println("Loading quizzes...");
        ArrayList<Quiz> quizzes = DataLoader.loadQuizzes();

        // Display available quizzes
        System.out.println("Available quizzes:");
        for (Quiz quiz : quizzes) {
            System.out.println("Quiz ID: " + quiz.getQuizID()); // Assuming you have a getQuizID method
        }

        // Prompt user to select a quiz
        System.out.print("Please select a quiz by entering its ID: ");
        String selectedQuizID = keyboard.nextLine();

        Quiz selectedQuiz = null;
        for (Quiz quiz : quizzes) {
            if (quiz.getQuizID().equals(selectedQuizID)) {
                selectedQuiz = quiz;
                break;
            }
        }

        if (selectedQuiz == null) {
            System.out.println("Invalid quiz ID. Exiting.");
            return; // Exit if quiz ID is invalid
        }

        // Start the selected quiz
        System.out.println("You have selected the quiz: " + selectedQuiz.getQuizID());
        selectedQuiz.startQuiz(); // Assuming startQuiz handles user interaction

        // Optional: Grade the quiz and output results
        selectedQuiz.gradeQuiz();
        System.out.println("Quiz result: " + selectedQuiz.getQuizGrade());

        keyboard.close(); // Close the scanner resource
    }
}
