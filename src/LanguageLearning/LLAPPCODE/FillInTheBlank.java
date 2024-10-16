
import java.util.List;
import java.util.Map;

public class FillInTheBlank extends Question {

    private String question;
    private String exerciseID;
    private List<String> userOptions;
    private Map<String, String> wordBank;
    private String correctAnswer;

    public Map<String, String> getWordBank() {
        return wordBank;
    }

    public void setWordBank(Map<String, String> newWordBank) {
        this.wordBank = wordBank;
    }

    public void startExercise(String exerciseID) {
        this.exerciseID = exerciseID;
    }

    public boolean checkAnswer(String userAnswer) {
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
