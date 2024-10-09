
import java.util.List;
import java.util.Map;

public class MultipleChoice extends Question {

    private String question; //"What is the correct conjugation for 'comer' (to eat) in first-person present tense?";
    private String exerciseID;
    private List<String> userOptions; // = Arrays.asList("comer", "present tense", "first-person", "conjugation");
    private static Map<String, String> wordBank; // = new HashMap<>();
    private String correctAnswer;

    public Map<String, String> getWordBank() {
        return wordBank;
    }

    public void setWordBank(Map<String, String> newWordBank) {
        this.wordBank = newWordBank;
    }

    public void startExercise(String exerciseID) {
        //System.out.println(question);
        //System.out.println(userOptions);
        //Scanner keyboard = new Scanner(System.in);
        //String userAnswer = keyboard.nextLine();
               //Then it'll give the checkAnswer the userAnswer to be checked (somehow)
        this.exerciseID = exerciseID;
    }

    /*
    For getting the keyword from a question (ex. 'comer' from the question)
    public String getKeyWord(String question) {

        /*int start = question.indexOf('\'');  // Find the start of the word (after the first single quote)
        int end = question.lastIndexOf('\'');  // Find the end of the word (before the second single quote)

        if (start != -1 && end != -1 && start < end) {
            return question.substring(start + 1, end);  // Extract and return the word between the single quotes
        }
    return null;   
    }   
     */
    public boolean checkAnswer(String userAnswer) {

        /*wordBank.put("comer", "como");
        String correctAnswer = wordBank.get("comer");
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
        //return wordBank.get(getKeyWord(question));
        return this.correctAnswer;
    }

   
    public String getExerciseiD() {
        return this.exerciseID;
    }

}

/**
 * wordBank.put("comer", "como"); if (wordBank.containsKey("comer")) { String
 * correctAnswer = wordBank.get("comer"); }
 *
 */
