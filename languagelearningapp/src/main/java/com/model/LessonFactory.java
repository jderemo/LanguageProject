package com.model;

import java.util.ArrayList;
import java.util.Scanner;
import com.narraration.Narriator;

/**
 * Lesson Factory Singleton class that interacts with 
 * the lesson list, the UI, and the Narrator. Contains a console implementation
 * of the lesson list and contains methods for a GUI implementation.
 */
public class LessonFactory {
    private static LessonList lessonList;
    private static Scanner scanner;
    public static Lesson currentLesson;

    private static LessonFactory instance;

    private LessonFactory(){
        lessonList = LessonList.getInstance();
        scanner = LanguageLearningApp.scanner;
    }

    public static LessonFactory getInstance(){
        if (instance == null){
            instance = new LessonFactory();
        }
        return instance;
    }

    /**
     * Gets a lesson from the lesson list.
     * @param language Language of the lesson 
     * @param lessonId ID of the lesson
     * @return The resulting lesson after searching for it. If no lesson was found, returns null.
     */
    public Lesson getLesson(String language, String lessonId){
        ArrayList<Lesson> lessons = GetLessonsOfLanguage(language);

        if (lessons.size() == 0){
            return null;
        }

        for(Lesson l : lessons){
            if (l.getLessonID().equals(lessonId)){
                return l;
            }
        }
        return null;
    }

    /**
     * A method that reads the language's contents aloud
     * @param lesson Lesson to read
     */
    public void readLesson(Lesson lesson){
        if (lesson == null){
            return;
        }
        Narriator.playSound(lesson.getContent());
    }

    /**
     * Console implementation of finding a lesson in the lesson list.
     * After a lesson is found, it will call a method to begin the lesson 
     */
    public void findLessonConsoleUi(){
        // Get language input from user
        System.out.println("What language would you like to study? Available options: \"Spanish\"");
        String language = scanner.nextLine();
        while (!language.equals("Spanish")){
            System.out.println("Sorry! That was an invalid language. Try again");
            language = scanner.nextLine();
        }

        // Get the available lessons
        ArrayList<Lesson> lessons = GetLessonsOfLanguage(language);

        if (lessons.size() == 0){
            System.out.println("Wow! You have completed all of the available lessons for this language!\nReturning to menu...");
            return;
        }

        System.out.println("You currently have " + lessons.size() + " lessons to go. They are as follows");
        for (Lesson l : lessons){
            System.out.println(l.getLessonID());
        }
        System.out.println("Which would you like to take?");

        Lesson lessonToTake = getLesson(language, scanner.nextLine());
        while (lessonToTake == null){
            System.out.println("That lesson doesn't exist. Try again.");
            lessonToTake = getLesson(language, scanner.nextLine());
        }
        // Begin Lesson
        beginLessonConsoleUi(lessonToTake);
    }
    
    /**
     * Console implementation of beginning a lesson. After the lesson is read
     * to the user, the user will be prompted to start the assiciated quiz.
     * @param lesson Lesson ID to start
     */
    private void beginLessonConsoleUi(Lesson lesson){
        if (lesson == null){
            return;
        }
        if (currentLesson != null){
            return;
        }
        currentLesson = lesson;

        System.out.println("Starting Lesson: " + lesson.getLessonID() + "\nFollow along as I read the contents to you...\n" + currentLesson.getContent());
        readLesson(currentLesson);
        System.out.println("Ready to take the quiz? (y, n)");
        while (true){
            switch(scanner.nextLine().toLowerCase()){
                case "y":{
                    QuizFactory.getInstance().launchQuizConsoleUi(QuizFactory.getInstance().getQuizById(currentLesson.getQuizID()));
                    return;
                }
                case "n":{
                    return;
                }
                default: {
                    System.out.println("Invalid choice, try again");
                    break;
                }
            }
        }
    }

    /**
     * Gets a list of lessons for the specified language
     * @param language Language of lessons to search for
     * @return an ArrayList of lessons that are for the given lanugage
     */
    public ArrayList<Lesson> GetLessonsOfLanguage(String language) {
        ArrayList<Lesson> lessons = lessonList.getLessonsByLanguage(language);

        ProgressTracker progressTrackerForLanguage = LanguageLearningApp.getCurrentUser().getProgressTrackerByLanguage(language);

        for (String s : progressTrackerForLanguage.getCompletedLessons()) {
            lessons.removeIf(lesson -> lesson.getLessonID().equals(s));
        }

        return lessons;
    }

}
