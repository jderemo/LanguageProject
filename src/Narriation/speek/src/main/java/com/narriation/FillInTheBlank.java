package com.narriation;

import java.util.List;
import java.util.Map;

public class FillInTheBlank extends Question {

    private String question;
    private String exerciseID;
    private List<String> userOptions;
    private Map<String, String> wordBank;
    private String correctAnswer;

    public FillInTheBlank(String question, String exerciseID, Map<String, String> wordBank, String correctAnswer) {
        this.question = question;
        this.exerciseID = exerciseID;
        this.wordBank = wordBank;
        this.correctAnswer = correctAnswer;
    }

    public Map<String, String> getWordBank() {
        return wordBank;
    }

    public void setWordBank(Map<String, String> newWordBank) {
        this.wordBank = wordBank;
    }

    public void startExercise(String exerciseID) {
        //System.out.println(question);
        //System.out.println(userOptions);
        //Scanner keyboard = new Scanner(System.in);
        //String userAnswer = keyboard.nextLine();
        //Then it'll give the checkAnswer the userAnswer to be checked (somehow)
        this.exerciseID = exerciseID;
    }

    public boolean checkAnswer(String userAnswer) {
        /*wordBank.put("word relating to correct answer in sentence", "correct answer"); Test word
        String correctAnswer = wordBank.get("word relating to correct answer");
        if (userAnswer == correctAnswer) {
            System.out.println("Correct!");
            return true;
        } else {
            System.out.println("Incorrect answer!");
            return false;
      }  */
        return false;
    }

    public String getQuestion() {
        return this.question;
    }

    public String getAnswer() {
        return this.correctAnswer;
    }

    public String getExerciseID() {
        return this.exerciseID;
    }
}
