package com.techtroops;

import java.util.ArrayList;
import java.util.Scanner;

public class LessonFactory {
    private static LessonList lessonList = LessonList.getInstance();
    private static Scanner scanner = LanguageLearningApp.scanner;
    public static Lesson currentLesson;

    public static void findLesson(){
        // Get language input from user
        System.out.println("What language would you like to study? Available options: \"Spanish\"");
        String language = scanner.nextLine();
        while (!language.equals("Spanish")){
            System.out.println("Sorry! That was an invalid language. Try again");
            language = scanner.nextLine();
        }

        // Get the available lessons
        ArrayList<Lesson> lessons = GetAvailableLessonsForUser(language);

        if (lessons.size() == 0){
            System.out.println("Wow! You have completed all of the available lessons for this language!\nReturning to menu...");
            return;
        }

        System.out.println("You currently have " + lessons.size() + " lessons to go. They are as follows");
        for (Lesson l : lessons){
            System.out.println(l.getLessonID());
        }
        System.out.println("Which would you like to take?");

        String lessonToTake = scanner.nextLine();
        boolean lessonValid = false;
        while (!lessonValid){
            for (Lesson l : lessons){
                if (l.getLessonID().equals(lessonToTake)){
                    lessonValid = true;
                }
            }
            if (!lessonValid){
                System.out.println("That lesson doesn't exist. Try again.");
                lessonToTake = scanner.nextLine();
            }
        }
        // Begin Lesson
        beginLesson(lessonToTake);
    }

    private static void beginLesson(String lessonId){
        currentLesson = lessonList.getLesson(lessonId);

        System.out.println("Starting Lesson: " + lessonId + "\nFollow along as I read the contents to you...\n" + currentLesson.getContent());
        Narriator.playSound(lesson.getContent());
        System.out.println("Ready to take the quiz? (y, n)");
        boolean userAnswered = false;
        while (!userAnswered){
            switch(scanner.nextLine().toLowerCase()){
                case "y":{
                    QuizFactory.launchQuiz(lessonId);
                    userAnswered = true;
                    break;
                }
                case "n":{
                    userAnswered = true;
                    return;
                }
                default: {
                    System.out.println("Invalid choice, try again");
                    break;
                }
            }
        }
    }
    private static ArrayList<Lesson> GetAvailableLessonsForUser(String language) {
        ArrayList<Lesson> lessons = lessonList.getLessonsByLanguage(language);

        ProgressTracker progressTrackerForLanguage = LanguageLearningApp.getCurrentUser().getProgressTrackerByLanguage(language);

        for (String s : progressTrackerForLanguage.getCompletedLessons()) {
            lessons.removeIf(lesson -> lesson.getLessonID().equals(s));
        }

        return lessons;
    }

}
