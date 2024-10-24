package com.narriation;

public class Achievement {
        // Variables
        private int achievementID;
        private String title;
        private String description;
        private int dateAchieved;
        private int score;
    
        // Constructor
        public Achievement(int achievementID, String title, String description, int dateAchieved, int score) {
            this.achievementID = achievementID;
            this.title = title;
            this.description = description;
            this.dateAchieved = dateAchieved;
            this.score = score;
        }
    
        // Method Stubs
        public void updateLeaderboard() {
            // Logic to update the leaderboard
        }
    
        public void displayRanking() {
            // Logic to display ranking
        }
    
        public void unlockReward() {
            // Logic to unlock rewards
        }
    
        public void displayAchievements() {
            // Logic to display achievements
        }
    
        public void setScore(int score) {
            this.score = score;
        }
    
        public int getScore() {
            return score;
        }
    
        // Getters and Setters for other variables
        public int getAchievementID() {
            return achievementID;
        }
    
        public void setAchievement(int achievement) {
            this.achievementID = achievementID;
        }
    
        public String getTitle() {
            return title;
        }
    
        public void setTitle(String title) {
            this.title = title;
        }
    
        public String getDescription() {
            return description;
        }
    
        public void setDescription(String description) {
            this.description = description;
        }
    
        public int getDateAchieved() {
            return dateAchieved;
        }
    
        public void setDateAchieved(int dateAchieved) {
            this.dateAchieved = dateAchieved;
        }
}
