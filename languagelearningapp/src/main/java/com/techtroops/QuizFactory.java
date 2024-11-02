package com.techtroops;

import java.util.ArrayList;
import java.util.Scanner;

public class QuizFactory {
    private static ArrayList<Quiz> quizList = DataLoader.loadQuizzes();
    private static Scanner scanner = LanguageLearningApp.scanner;

    public static void listAvailableQuizzes(){
        ArrayList<String> choices = new ArrayList<String>();
        System.out.println("Here are the past quizzes you have completed: \n");
        for (ProgressTracker pT : LanguageLearningApp.getCurrentUser().getProgressTrackers()){
            for (String s : pT.getCompletedLessons()){
                choices.add(s);
                System.out.println(s);
            }
        }
        System.out.println("\n\nSelect one to replay or type \"q\" to go back.");
        boolean validInput = false;
        String userInput = scanner.nextLine();
        while (!validInput){
            if (userInput.toLowerCase().equals("q")){
                return;
            }
            if (choices.contains(userInput)){
                launchQuiz(userInput);
                return;
            }
            System.out.println("That was not a valid quiz, try again.");
        }
    }

    public static void launchQuiz(String quizId){
        Quiz quiz = getQuiz(quizId);

        if (quiz == null){
            System.out.println("Quiz is null!! Can't continue!");
            return;
        }

        System.out.println("Starting quiz for " + quizId + "\n");
        
        int quiestionNumber = 0;
        int score = 0;
        Question currentQuestion = null;
        while(quiestionNumber < quiz.getQuestionsList().size()){
            System.out.println("Question #" + (quiestionNumber + 1) + "\n");
            currentQuestion = quiz.getQuestion(quiestionNumber);

            System.out.println(currentQuestion.getQuestion() + "\n\n" + "Choices:\n");
            for(String s : currentQuestion.getUserOptions()){
                System.out.println(s);
            }

            if (scanner.nextLine().toLowerCase().equals(currentQuestion.getAnswer().toLowerCase())){
                score++;
                System.out.println("CORRECT!\nNext Question...\n\n");
            } else {
                System.out.println("Incorrect!\nNext Question...\n\n");
            }
            quiestionNumber++;
        }
        
        // Update progress if user passed
        if ((double) score / (double) quiz.getQuestionsList().size() >= 0.7){
            System.out.println("Congratulations!! You have passed this quiz with a score of " + score + "/" + quiz.getQuestionsList().size() + "!\nReturning to lessons...");
            
            // Get Progress Tracker for language
            ProgressTracker userProgressTracker = LanguageLearningApp.getCurrentUser().getProgressTrackerByLanguage(LessonFactory.currentLesson.getLanguage());

            userProgressTracker.completeLesson(LessonFactory.currentLesson);
        } else {
            System.out.println("Oh no! You did not pass the quiz! Your score was: " + score + "/" + quiz.getQuestionsList().size() + "\nReturning to lessons...");
        }
    }

    private static Quiz getQuiz(String quizId){
        for(Quiz q : quizList){
            if (q.getQuizID().equals(quizId)){
                return q;
            }
        }
        return null;
    }
}
