package com.narriation;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        // User account creation or loading
        System.out.println("Create an account (y) or load existing accounts (n)?");
        String option = keyboard.nextLine();
        User loggedInUser = null;

        if (option.equals("y")) {
            // New account details
            System.out.println("Enter username:");
            String username = keyboard.nextLine();
            System.out.println("Enter email:");
            String email = keyboard.nextLine();
            System.out.println("Enter password:");
            String password = keyboard.nextLine();
            System.out.println("Enter preferred language:");
            String preferredLanguage = keyboard.nextLine();

            // Register new user
            loggedInUser = new User(0, username, email, password, preferredLanguage, null, null);
            loggedInUser.register();

        } else if (option.equals("n")) {
            // Load existing users
            ArrayList<User> users = DataLoader.loadUsers();
            if (users != null && !users.isEmpty()) {
                System.out.println("Users loaded successfully.");
                System.out.println("Enter username:");
                String username = keyboard.nextLine();
                System.out.println("Enter password:");
                String password = keyboard.nextLine();

                // Validate login
                for (User user : users) {
                    if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                        loggedInUser = user;
                        System.out.println("Login successful!");
                        break;
                    }
                }

                if (loggedInUser == null) {
                    System.out.println("Login failed. Exiting.");
                    return;
                }
            } else {
                System.out.println("No users found. Exiting.");
                return;
            }
        } else {
            System.out.println("Invalid option. Exiting.");
            return;
        }

        // Load lessons
        ArrayList<Lesson> lessons = DataLoader.loadLessons();

        boolean continueLearning = true;
        while (continueLearning) {
            // Display available lessons
            System.out.println("Available lessons:");
            for (Lesson lesson : lessons) {
                System.out.println("Lesson ID: " + lesson.getLessonID() + " - Language: " + lesson.getLanguage());
            }
            System.out.print("Select a lesson by entering its ID: ");
            int selectedLessonID = keyboard.nextInt();
            keyboard.nextLine(); // Consume newline

            Lesson selectedLesson = null;
            for (Lesson lesson : lessons) {
                if (lesson.getLessonID() == selectedLessonID) {
                    selectedLesson = lesson;
                    break;
                }
            }

            if (selectedLesson == null) {
                System.out.println("Invalid lesson ID. Exiting.");
                return;
            }

            // Display lesson content
            System.out.println("Lesson content:");
            selectedLesson.displayContent();

            // Load quizzes for the selected lesson
            ArrayList<Quiz> quizzes = DataLoader.loadQuizzes();

            boolean keepQuizzing = true;
            while (keepQuizzing) {
                // Display available quizzes
                System.out.println("Available quizzes:");
                for (Quiz quiz : quizzes) {
                    System.out.println("Quiz ID: " + quiz.getQuizID());
                }

                // Select a quiz
                System.out.print("Select a quiz by entering its ID: ");
                String selectedQuizID = keyboard.nextLine();

                Quiz selectedQuiz = null;
                for (Quiz quiz : quizzes) {
                    if (quiz.getQuizID().equals(selectedQuizID)) {
                        selectedQuiz = quiz;
                        break;
                    }
                }

                if (selectedQuiz == null) {
                    System.out.println("Invalid quiz ID. Try again.");
                    continue;
                }

                // Start the quiz
                System.out.println("Selected quiz: " + selectedQuiz.getQuizID());
                selectedQuiz.startQuiz();

                // Grade the quiz
                selectedQuiz.gradeQuiz();
                System.out.println("Quiz result: " + selectedQuiz.getQuizGrade());
                System.out.println("If you passed, you can proceed! Otherwise, retake the quiz.");

                // Ask if they want to take another quiz
                System.out.print("Take another quiz? (y/n): ");
                String tryAnotherQuiz = keyboard.nextLine();
                if (tryAnotherQuiz.equalsIgnoreCase("n")) {
                    keepQuizzing = false; // Stop quizzing
                }
            }

            // Ask if they want to return to lesson selection
            System.out.print("Return to lesson selection? (y/n): ");
            String returnToLessons = keyboard.nextLine();
            if (returnToLessons.equalsIgnoreCase("n")) {
                continueLearning = false; // Exit learning loop
                System.out.println("Goodbye!");
            }
        }

        keyboard.close(); // Close the scanner
    }
}
