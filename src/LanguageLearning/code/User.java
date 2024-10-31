import java.util.ArrayList;
import java.util.UUID;

public class User {

    private String userID;
    private String username;
    private String password;
    private String email;
    private ArrayList<ProgressTracker> progressTrackers;
    private String preferredLanguage;

    public User(String userID, String username, String email, String password, String preferredLanguage, ArrayList<ProgressTracker> progressTrackers) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.password = password;
        this.preferredLanguage = preferredLanguage;
        this.progressTrackers = progressTrackers != null ? progressTrackers : new ArrayList<>();
    }
    public User(String username, String email, String password){
        this.userID = UUID.randomUUID().toString();
        this.username = username;
        this.email = email;
        this.password = password;
        this.preferredLanguage = "Spanish";
        this.progressTrackers = new ArrayList<ProgressTracker>();
    }

    // Registers a new user if the username and email are unique
    public void register() {
        ArrayList<User> users = DataLoader.loadUsers();

        for (User user : users) {
            if (user.getUsername().equals(this.username)) {
                System.out.println("Username already exists. Please choose a different username.");
                return;
            }
            if (user.getEmail().equals(this.email)) {
                System.out.println("Email already registered. Please use a different email.");
                return;
            }
        }

        users.add(this); // Add this user to the list
        DataWriter.saveUsers(users); // Save the updated list to JSON
        System.out.println("Registration successful!");
    }

    // Validates login credentials
    public void login(String username, String email, String password) {
        ArrayList<User> users = DataLoader.loadUsers();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getEmail().equals(email) && user.getPassword().equals(password)) {
                System.out.println("Login successful!");
                return;
            }
        }
        System.out.println("Login failed. Please check your credentials.");
    }

    // Retrieves a ProgressTracker by exercise ID
    public ProgressTracker getProgressTrackerByExerciseID(String exerciseID) {
        for (ProgressTracker tracker : progressTrackers) {
            if (tracker.getExerciseID().equals(exerciseID)) {
                return tracker;
            }
        }
        return null;
    }

    // Updates the progress for a specific exercise
    public void updateProgress(ProgressTracker tracker) {
        ProgressTracker existingTracker = getProgressTrackerByExerciseID(tracker.getExerciseID());
        if (existingTracker != null) {
            existingTracker.setProgress(existingTracker.getProgress() + 1.0);
            System.out.println("Progress updated for exercise: " + tracker.getExerciseID());
        } else {
            addProgressTracker(tracker);
            System.out.println("Added and updated progress for new exercise: " + tracker.getExerciseID());
        }
    }

    // Getters and Setters
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProgressTrackers(ArrayList<ProgressTracker> progressTrackers) {
        this.progressTrackers = progressTrackers;
    }

    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    public ArrayList<ProgressTracker> getProgressTrackers() {
        return progressTrackers;
    }

    public void addProgressTracker(ProgressTracker tracker) {
        if (progressTrackers == null) {
            progressTrackers = new ArrayList<>();
        }
        progressTrackers.add(tracker);
    }

}
