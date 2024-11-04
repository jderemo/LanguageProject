package com.techtroops;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A Quiz system for a language learning application.
 * Contains methods that allow a user to start a quiz, answer questions, and get a grade
 * There is a console implementation in this file along with methods that would allow for a
 * GUI implementation elsewhere.
 */
public class QuizFactory {
    private static ArrayList<Quiz> quizList = DataLoader.loadQuizzes();
    private static LessonList lessonList = LessonList.getInstance();
    private static Scanner scanner = LanguageLearningApp.scanner;
    private static Quiz currentQuiz = null;
    private static Question currentQuestion;
    private static int currentScore = 0;
    private static int currentQuestionNumber = 0;

    private static QuizFactory instance;

    private QuizFactory(){
        quizList = DataLoader.loadQuizzes();
        scanner = LanguageLearningApp.scanner;
    }

    public static QuizFactory getInstance(){
        if (instance == null){
            instance = new QuizFactory();
        }
        return instance;
    }

    /**
     * Gets a quiz by it's id
     * @param quizId Quiz id
     * @return A Quiz from quizList with the specified id
     */
    public Quiz getQuizById(String quizId){
        for (Quiz q : quizList){
            if (q.getQuizID().equals(quizId)){
                return q;
            }
        }
        return null;
    }

    /**
     * This will return the list of replayable quizzes for a user
     * @param user User to scan
     * @param language Language the quizzes should be for
     * @return An ArrayList<Quiz> containing said quizzes
     */
    public ArrayList<Quiz> getAvailableQuizzes(User user, String language){
        ProgressTracker userProgressTracker = user.getProgressTrackerByLanguage(language);

        if (userProgressTracker == null){
            return null;
        }

        Lesson lesson = null;
        ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
        for (String lessonId : userProgressTracker.getCompletedLessons()){
            lesson = lessonList.getLesson(lessonId);
            for (Quiz quiz : quizList){
                if (quiz.getQuizID().equals(lesson.getQuizID())){
                    quizzes.add(quiz);
                }
            }
        }
        return quizzes.size() == 0 ? null : quizzes;
    }

    /**
     * Starts the quiz provided
     * @param quiz Quiz to start
     */
    public void launchQuiz(Quiz quiz){
        if (currentQuiz != null){
            return;
        }
        if (quiz == null){
            return;
        }
        currentQuiz = quiz;
        currentQuestionNumber = 0;
        currentScore = 0;

        currentQuestion = currentQuiz.getQuestion(currentQuestionNumber);
    }

    /**
     * Returns the current question
     * @return The current Question of the quiz
     */
    public Question getCurrentQuestion(){
        return currentQuestion;
    }

    /**
     * Checks if the answer is correct,
     * incrememnts score if correct, and
     * moves to the next question
     * @param answer Answer provided by the user
     * @return was the answer correct?
     */
    public boolean validateAnswer(String answer){
        boolean isCorrect = answer.toLowerCase().equals(currentQuestion.getAnswer().toLowerCase());
        if (isCorrect){
            currentScore++;
        }
        nextQuestion();
        return isCorrect;
    }

    /**
     * Gets the current question number
     * @return current question number
     */
    public int getQuestionNumber(){
        return currentQuestionNumber;
    }

    /**
     * Checks if there are more questions to ask
     * @return are there more questions in the quiz?
     */
    public boolean hasMoreQuestions(){
        return currentQuestionNumber < currentQuiz.getQuestionsList().size();
    }

    /**
     * Gets the player's total score
     * @return score / total questions
     */
    public double getScore(){
        return (double) currentScore / (double) currentQuiz.getQuestionsList().size();
    }

    /**
     * Moves to the next question
     */
    private void nextQuestion(){
        if (hasMoreQuestions()){
            currentQuestionNumber++;
            currentQuestion = currentQuiz.getQuestion(currentQuestionNumber);
        }
        return;
    }

    /**
     * Gets the number of questions in the current quiz.
     * Returns 0 if there is no quiz currently active
     * @return number of questions in current quiz
     */
    public int getNumberOfQuestions(){
        if (currentQuiz == null){
            return 0;
        }
        return currentQuiz.getQuestionsList().size();
    }

    /**
     * Gets the number of questions the user has gotten correct
     * @return number of questions the user has gotten correct so far
     */
    public int getNumberOfCorrectAnswers(){
        return currentScore;
    }

    public Quiz getCurrentQuiz(){
        return currentQuiz;
    }

    /**
     * A console version of replaying quizzes
     */
    public void listAvailableQuizzesConsoleUi(){
        // Get Available Quizzes
        ArrayList<Quiz> choices = getAvailableQuizzes(LanguageLearningApp.getCurrentUser(), "Spanish");
        if (choices == null){
            System.out.println("You have not completed any quizzes yet!");
            return;
        }

        // Print out the avaialable choices
        System.out.println("Here are the past quizzes you have completed: \n");
        for (Quiz q : choices){
            System.out.println(q.getQuizID());
        }
        System.out.println("\n\nSelect one to replay or type \"q\" to go back.");
        boolean validInput = false;
        String userInput = scanner.nextLine();

        // Let the user choose a quiz or quit to menu
        while (!validInput){
            for(Quiz q : quizList){
                if (q.getQuizID().equals(userInput)){
                    launchQuizConsoleUi(q);
                    return;
                }
            }
            if (userInput.toLowerCase().equals("q")){
                return;
            }
            System.out.println("That was not a valid quiz, try again.");
            userInput = scanner.nextLine();
        }
    }

    /**
     * A console version of taking a quiz
     * @param quiz Quiz to take
     */
    public void launchQuizConsoleUi(Quiz quiz){
        launchQuiz(quiz);

        System.out.println("Starting quiz for " + quiz.getQuizID() + "\n");

        // Reset parameters
        currentQuestionNumber = 0;
        currentScore = 0;
        Question currentQuestion = null;

        // While there are remaining questions, ask the next one
        while(hasMoreQuestions()){
            System.out.println("Question #" + (currentQuestionNumber + 1) + "\n");
            currentQuestion = currentQuiz.getQuestion(currentQuestionNumber);

            // Print the question and the choices
            System.out.println(currentQuestion.getQuestion() + "\n\n" + "Choices:\n");
            for(String s : currentQuestion.getUserOptions()){
                System.out.println(s);
            }

            // If the user inputs the correct answer, increment score
            // and let the user know they were correct
            if (validateAnswer(scanner.nextLine())){
                System.out.println("CORRECT!\nNext Question...\n\n");
            } else {
                System.out.println("Incorrect!\nNext Question...\n\n");
            }
        }
        
        // Update progress if user passed
        if (getScore() >= 0.7){
            System.out.println("Congratulations!! You have passed this quiz with a score of " + currentScore + "/" + currentQuiz.getQuestionsList().size() + "!\nReturning to lessons...");
            
            // Get Progress Tracker for language
            ProgressTracker userProgressTracker = LanguageLearningApp.getCurrentUser().getProgressTrackerByLanguage(currentQuiz.getLanguage());

            // Update user's progress tracker
            userProgressTracker.completeLesson(lessonList.getLesson(quiz.getLessonID()));
        } else {
            System.out.println("Oh no! You did not pass the quiz! Your score was: " + currentScore + "/" + currentQuiz.getQuestionsList().size() + "\nReturning to lessons...");
        }
        currentQuiz = null;
        currentQuestionNumber = 0;
        currentScore = 0;
    }
}
