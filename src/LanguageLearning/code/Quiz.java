import java.util.List;
import java.util.Scanner;

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

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < questionsList.size(); i++) {
            Question question = questionsList.get(i);
            System.out.println("Question " + (i + 1) + ": " + question.getQuestion());
            Narriator.playSound(question.getQuestion());
            if (question instanceof MultipleChoice) {
                System.out.println("Options: " + ((MultipleChoice) question).getUserOptions());
            }

            System.out.print("Your answer: ");
            String userAnswer = scanner.nextLine();

            if (checkAnswer(userAnswer, i)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer was: " + question.getAnswer());
            }
        }
        scanner.close();
        gradeQuiz();
    }

    public void gradeQuiz() {
        result = "Score: " + score + "/" + questionsList.size();
        System.out.println(result);
    }

    public void chooseQuiz(String userChoice, List<Quiz> availableQuizzes) {
        for (Quiz quiz : availableQuizzes) {
            if (quiz.getQuizID().equals(userChoice)) {
                quiz.startQuiz();
                return;
            }
        }
        System.out.println("Quiz not found.");
    }

    public String getQuizID() {
        return quizID;
    }

    public String getQuestion(int index) {
        if (index >= 0 && index < questionsList.size()) {
            return questionsList.get(index).getQuestion();
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
}
