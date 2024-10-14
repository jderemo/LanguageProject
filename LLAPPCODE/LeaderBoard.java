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

    // Method to add ranking
    public void addRanking(LeaderBoard ranks) {
        // Implementation to add rankings from another Leaderboard instance
    }

    // Method to display ranking
    public void displayRanking() {
        // Implementation to display the current rankings
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
