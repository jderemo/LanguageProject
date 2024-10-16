
import java.util.ArrayList;

public class Lesson {

    // Variables
    private int lessonID;
    private String language;
    private Difficulty difficultyLevel; // Assuming Difficulty is an enum or class
    private String content;
    private String duration;
    private ArrayList<String> feedbackList;

    // Constructor
    public Lesson(int lessonID, String language, Difficulty difficultyLevel, String content, String duration, ArrayList<String> feedbackList) {
        this.lessonID = lessonID;
        this.language = language;
        this.difficultyLevel = difficultyLevel;
        this.content = content;
        this.duration = duration;
        this.feedbackList = feedbackList != null ? feedbackList : new ArrayList<>();
    }

    // Method Stubs
    public void startLesson() {
        // Logic to start the lesson
    }

    public void completeLesson() {
        // Logic to mark the lesson as complete
    }

    public void addFeedback(String comment) {
        feedbackList.add(comment);
    }

    public ArrayList<String> getFeedback() {
        return feedbackList;
    }

    public Lesson getLesson() {
        return this; // Returns the current lesson instance
    }

    // Getters and Setters
    public int getLessonID() {
        return lessonID;
    }

    public void setLessonID(int lessonID) {
        this.lessonID = lessonID;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Difficulty getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(Difficulty difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public ArrayList<String> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(ArrayList<String> feedbackList) {
        this.feedbackList = feedbackList;
    }
}
