import java.util.List;

public class MultipleChoice extends Question {

    private String questionText;
    private String exerciseID;
    private List<String> userOptions;
    private List<String> wordBank;
    private String correctAnswer;

    public MultipleChoice(String questionText, String exerciseID, List<String> userOptions, String correctAnswer) {
        this.questionText = questionText;
        this.exerciseID = exerciseID;
        this.userOptions = userOptions;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public void startExercise(String exerciseID) {
        // Implementation for starting the exercise
        this.exerciseID = exerciseID;
    }

    @Override
    public String getQuestion() {
        return this.questionText;
    }

    @Override
    public String getAnswer() {
        return this.correctAnswer;
    }

    @Override
    public String getExerciseID() {
        return this.exerciseID;
    }

    @Override
    public List<String> getUserOptions() {
        return this.userOptions;
    }


    
}
