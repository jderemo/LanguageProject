package com.narriation;

import java.util.ArrayList;

public class User {
    private int userID;
    private String username;
    private String password;
    private String email;
    private ArrayList<ProgressTracker> progressTrackers;
    private String preferredLanguage;
    private ArrayList<Achievement> achievements;
    private ArrayList<Integer> achievementDates;

    public User(int userID, String username, String email, String password, String preferredLanguage, ArrayList<ProgressTracker> progressTrackers, ArrayList<Achievement> achievements) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.password = password;
        this.preferredLanguage = preferredLanguage;
        this.progressTrackers = progressTrackers != null ? progressTrackers : new ArrayList<>();
        this.achievements = achievements != null ? achievements : new ArrayList<>();
        this.achievementDates = achievementDates != null ? achievementDates : new ArrayList<>();
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

    // Retrieves a user by username
    public User getUser(String username) {
        ArrayList<User> users = DataLoader.loadUsers();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
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
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
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

    public ArrayList<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(ArrayList<Achievement> achievements) {
        this.achievements = achievements;
    }

    public ArrayList<Integer> getAchievementDates() {
        return achievementDates;
    }

    public void setAchievementDates(ArrayList<Integer> achievementDates) {
        this.achievementDates = achievementDates;
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
