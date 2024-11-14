package com.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * User class
 */
public class User {
    private String userID;
    private String username;
    private String password;
    private String email;
    private ArrayList<ProgressTracker> progressTrackers;
    private String preferredLanguage;

    // Constructor for loading existing user from JSON
    public User(String userID, String username, String email, String password, String preferredLanguage, ArrayList<ProgressTracker> progressTrackers) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.password = password;
        this.preferredLanguage = preferredLanguage;
        this.progressTrackers = progressTrackers != null ? progressTrackers : new ArrayList<>();
    }

    // Constructor for a new user
    public User(String username, String email, String password){
        this.userID = UUID.randomUUID().toString();
        this.username = username;
        this.email = email;
        this.password = password;
        this.preferredLanguage = "Spanish";
        this.progressTrackers = new ArrayList<ProgressTracker>();
    }

    // toString method
    public String toString(){
        String trackers = "";
        for(ProgressTracker p : progressTrackers){
            trackers+= p;
        }
        return "Username: " + username + "\nPassword: " + password + "\nEmail: " + email + "\nProgress Tracker(s): " + trackers + "\nPreferred Language: " + preferredLanguage;
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

    /**
     * Gets the progress tracker for the specified langugage
     * @param language Language to search for
     * @return The resulting progress tracker. Returns a new one if there wasn't one already.
     */
    public ProgressTracker getProgressTrackerByLanguage(String language){
        for (ProgressTracker pT : progressTrackers){
            if (pT.getLanguage().equals(language)){
                return pT;
            }
        }
        ProgressTracker newPT = new ProgressTracker(0, language, new ArrayList<String>());
        progressTrackers.add(newPT);
        return newPT;
    }

}
