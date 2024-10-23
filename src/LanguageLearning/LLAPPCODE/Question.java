import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Question {

    private static Map<String, String> wordBank = new HashMap<>();

    public Map<String, String> getWordBank() {
        return wordBank;
    }

    public static void expandWordBank(String englishWord, String translatedWord) {
        wordBank.put(englishWord, translatedWord);
    }

    public abstract void startExercise(String exerciseID);

    public abstract boolean checkAnswer(String userAnswer);

    public abstract String getQuestion();

    public abstract String getAnswer();

    public abstract String getExerciseID();
    
}
