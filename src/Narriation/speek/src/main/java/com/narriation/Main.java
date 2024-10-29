package com.narriation;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        boolean isRunning = true;
        User loggedInUser = null;

        while (isRunning) {
            // Main login menu
            System.out.println("Create an account (y), load existing accounts (n), or quit (q)?");
            String option = keyboard.nextLine();

            if (option.equalsIgnoreCase("q")) {
                System.out.println("Exiting application. Goodbye!");
                break;
            } else if (option.equalsIgnoreCase("y")) {
                // New account creation
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
                System.out.println("Account created successfully.");

            } else if (option.equalsIgnoreCase("n")) {
                // Load existing users
                ArrayList<User> users = DataLoader.loadUsers();
                if (users == null || users.isEmpty()) {
                    System.out.println("No users found. Exiting.");
                    continue;
                }

                System.out.println("Users loaded successfully.");
                System.out.println("Enter username (or type 'quit' to exit):");
                String username = keyboard.nextLine();
                if (username.equalsIgnoreCase("quit")) break;

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
                    System.out.println("Login failed. Try again or exit with 'q'.");
                    continue;
                }

            } else {
                System.out.println("Invalid option. Exiting.");
                continue;
            }

            // If user successfully logged in or created an account, start lesson and quiz selection
            if (loggedInUser != null) {
                ArrayList<Lesson> lessons = DataLoader.loadLessons();
                boolean continueLearning = true;

                while (continueLearning) {
                    System.out.println("Available lessons:");
                    for (Lesson lesson : lessons) {
                        System.out.println("Lesson ID: " + lesson.getLessonID() + " - Language: " + lesson.getLanguage());
                    }
                    System.out.print("Select a lesson by entering its ID (or type 'logout' to log out): ");
                    String lessonInput = keyboard.nextLine();

                    if (lessonInput.equalsIgnoreCase("logout")) {
                        System.out.println("Logging out...");
                        loggedInUser = null;
                        break;
                    }

                    int selectedLessonID;
                    try {
                        selectedLessonID = Integer.parseInt(lessonInput);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid lesson ID.");
                        continue;
                    }

                    Lesson selectedLesson = null;
                    for (Lesson lesson : lessons) {
                        if (lesson.getLessonID() == selectedLessonID) {
                            selectedLesson = lesson;
                            break;
                        }
                    }

                    if (selectedLesson == null) {
                        System.out.println("Invalid lesson ID.");
                        continue;
                    }

                    System.out.println("Lesson content:");
                    selectedLesson.displayContent();

                    ArrayList<Quiz> quizzes = DataLoader.loadQuizzes();
                    boolean keepQuizzing = true;

                    while (keepQuizzing) {
                        System.out.println("Available quizzes:");
                        for (Quiz quiz : quizzes) {
                            System.out.println("Quiz ID: " + quiz.getQuizID());
                        }

                        System.out.print("Select a quiz by entering its ID (or type 'logout' to log out): ");
                        String selectedQuizID = keyboard.nextLine();

                        if (selectedQuizID.equalsIgnoreCase("logout")) {
                            System.out.println("Logging out...");
                            loggedInUser = null;
                            keepQuizzing = false;
                            continueLearning = false;
                            break;
                        }

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

                        System.out.println("Selected quiz: " + selectedQuiz.getQuizID());
                        selectedQuiz.startQuiz();
                        selectedQuiz.gradeQuiz();
                        System.out.println("Quiz result: " + selectedQuiz.getQuizGrade());
                        System.out.println("Suggested scoring 4/5 or above to progress to the next quiz.");

                        System.out.print("Take another quiz? (y/n/logout): ");
                        String tryAnotherQuiz = keyboard.nextLine();

                        if (tryAnotherQuiz.equalsIgnoreCase("n")) {
                            keepQuizzing = false;
                        } else if (tryAnotherQuiz.equalsIgnoreCase("logout")) {
                            System.out.println("Logging out...");
                            loggedInUser = null;
                            keepQuizzing = false;
                            continueLearning = false;
                            break;
                        }
                    }

                    if (loggedInUser == null) break;

                    System.out.print("Return to lesson selection? (y/n/logout): ");
                    String returnToLessons = keyboard.nextLine();

                    if (returnToLessons.equalsIgnoreCase("n")) {
                        continueLearning = false;
                        System.out.println("Goodbye!");
                    } else if (returnToLessons.equalsIgnoreCase("logout")) {
                        System.out.println("Logging out...");
                        loggedInUser = null;
                        continueLearning = false;
                        break;
                    }
                }
            }
        }
        keyboard.close();
    }
}
