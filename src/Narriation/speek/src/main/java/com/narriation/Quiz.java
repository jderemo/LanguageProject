package com.narriation;

import java.util.ArrayList;

public class Quiz {

    // Variables
    private String quizID;
    private ArrayList<Question> questionsList;
    private String result;

    // Constructor
    public Quiz(String quizID) {
        this.quizID = quizID;
        this.questionsList = new ArrayList<>();
        this.result = "";
    }

    // Method to start the quiz
    public void startQuiz() {
        // Logic to start the quiz
    }

    // Method to grade the quiz
    public void gradeQuiz() {
        // Logic to grade the quiz and set the result
    }

    // Method to choose a quiz based on user choice
    public void chooseQuiz(String userChoice) {
        // Logic to choose a quiz based on user input
    }

    // Method to get the current question
    public String getQuestion(int index) {
        if (index >= 0 && index < questionsList.size()) {
            return questionsList.get(index).getQuestion();
        }
        return null; // Return null if index is out of bounds
    }

    // Method to check the user's answer
    public boolean checkAnswer(String userAnswer, int index) {
        if (index >= 0 && index < questionsList.size()) {
            return questionsList.get(index).getCorrectAnswer().equalsIgnoreCase(userAnswer);
        }
        return false; // Return false if index is out of bounds
    }

    // Method to get the quiz grade
    public String getQuizGrade() {
        return result; // Return the result of the quiz
    }

    // Method to add questions to the quiz
    public void addQuestion(Question question) {
        questionsList.add(question);
    }
}
