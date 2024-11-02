package com.techtroops;

import java.util.List;

public class Quiz {
    private String quizID;
    private List<Question> questionsList;
    private String result;
    private int score;

    public Quiz(String quizID, List<Question> questionsList) {
        this.quizID = quizID;
        this.questionsList = questionsList;
        this.result = "";
        this.score = 0;
    }

    public void gradeQuiz() {
        result = "Score: " + score + "/" + questionsList.size();
        System.out.println(result);
    }

    public String getQuizID() {
        return quizID;
    }

    public Question getQuestion(int index) {
        if (index >= 0 && index < questionsList.size()) {
            return questionsList.get(index);
        }
        return null;
    }

    public boolean checkAnswer(String userAnswer, int index) {
        if (index >= 0 && index < questionsList.size()) {
            return questionsList.get(index).getAnswer().equalsIgnoreCase(userAnswer);
        }
        return false;
    }

    public String getQuizGrade() {
        return result;
    }

    public void addQuestion(Question question) {
        questionsList.add(question);
    }

    public List<Question> getQuestionsList(){
        return this.questionsList;
    }
}
