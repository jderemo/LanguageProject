import java.util.ArrayList;
import java.util.Scanner;
public class LanguageLearningApp {

    // Variables
    private LessonList lessonList;
    private User user;
    private UserList userList;
    private Scanner kb;
    // Constructor
    public LanguageLearningApp() {
        this.lessonList = new LessonList();
        this.userList = new UserList();
        this.user = null; // Initially no user is logged in
    }

    // Method to register a user
    public User register(String username, String email, String password) {
        // Registration logic to create and return a new User
        return new User(username, email, password);
    }

    // Method to log in a user
    public User login(String username, String password) {
        System.out.println("Insert Username: ");
        if(!userList.getUser(username).equals(username))
            System.out.println("Wrong Username");
        else
            userList.getUser(username).equals(username);
        System.out.println("Insert Password: ");
        if(!userList.getUser(password).equals(password))
            System.out.println("Wrong password: ");
        else
            userList.getUser(password).equals(password);
            
        return user;
    }

    // Method to get a progress report
    public String getProgressReport() {
        // Logic to generate and return a progress report
        return "Progress Report"; // Placeholder
    }

    // Method to update progress
    public void updateProgress(ProgressTracker progress) {
        // Logic to update user progress
    }

    // Method to add a lesson
    public void addLesson(int lessonID, String language, Difficulty difficultyLevel, String content) {
        // Logic to add a lesson to the lesson list
        Lesson newLesson = new Lesson(lessonID, language, difficultyLevel, content);
        lessonList.addLesson(newLesson);
    }

    // Method to remove a lesson
    public void removeLesson(int lessonID) {
        // Logic to remove a lesson from the lesson list
        lessonList.removeLesson(lessonID);
    }

    // Method to get a lesson by ID
    public Lesson getLesson(int lessonID) {
        // Logic to retrieve and return a specific lesson
        return lessonList.getLesson(lessonID);
    }

    // Method to get all lessons
    public ArrayList<Lesson> getEveryLesson() {
        // Logic to return a list of all lessons
        return lessonList.getAllLessons();
    }

    // Method to start a lesson
    public void startLesson(String userChoice) {
        // Logic to start a lesson based on user choice
    }
}
