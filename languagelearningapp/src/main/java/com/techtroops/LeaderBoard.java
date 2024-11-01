package com.techtroops;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LeaderBoard {

    // Instance variables
    private int userRank;
    private int totalScore;
    private List<String> ranks; // Assuming ranks is a list of usernames or rank identifiers
    private HashMap<String, Integer> scoresByUsername; // Stores scores by username

    // Constructor
    public LeaderBoard() {
        this.scoresByUsername = new HashMap<>();
        this.ranks = new ArrayList<>();
    }

    // Method to get the instance of Leaderboard
    public static LeaderBoard getInstance() {
        return new LeaderBoard();
    }

    // Method to add a score by username
    public void addScore(String username, int score) {
        // Add the score for the user
        scoresByUsername.put(username, score);
        
        // Optionally, you might want to update ranks here
        updateRanks();
    }

    // Method to update ranks based on scores
    private void updateRanks() {
        // Clear current ranks
        ranks.clear();
        
        // Sort the usernames based on scores and update ranks
        scoresByUsername.entrySet()
            .stream()
            .sorted((a, b) -> b.getValue().compareTo(a.getValue())) // Sort descending by score
            .forEach(entry -> ranks.add(entry.getKey())); // Add usernames to ranks list
    }

    // Method to display ranking
    public void displayRanking() {
        System.out.println("Leaderboard Rankings:");
        for (int i = 0; i < ranks.size(); i++) {
            String username = ranks.get(i);
            int score = scoresByUsername.get(username);
            System.out.println((i + 1) + ". " + username + " - Score: " + score);
        }
    }

    // Getters and Setters for userRank and totalScore
    public int getUserRank() {
        return userRank;
    }

    public void setUserRank(int userRank) {
        this.userRank = userRank;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public List<String> getRanks() {
        return ranks;
    }

    public HashMap<String, Integer> getScoresByUsername() {
        return scoresByUsername;
    }
}
