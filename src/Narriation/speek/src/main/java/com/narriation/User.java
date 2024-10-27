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

    // Method Stubs
    public void register() {
        // Load existing users
        ArrayList<User> users = DataLoader.loadUsers();
    
        // Check if username or email already exists
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
    
        // If unique, save the new user
        users.add(this); // Add this user to the list
        DataWriter.saveUsers(users); // Save the updated list
        System.out.println("Registration successful!");
    }

    public void login(String username, String email, String password) {
        ArrayList<User> users = DataLoader.loadUsers();

        for(User user : users) {
            if (user.getUsername().equals(username) && user.getEmail().equals(email) && user.getPassword().equals(password)) {
                System.out.println("Login successful!");
                return;
        }
    }
    System.out.println("Login failed. Please check your credentials.");
}

    public User getUser(String username) {
        ArrayList<User> users = DataLoader.loadUsers();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public String getProgressReport(String language) {
        // Logic to generate progress report based on language
        return ""; // Stub return
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

    public ArrayList<ProgressTracker> getProgressTrackers() {
        return progressTrackers;
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
}