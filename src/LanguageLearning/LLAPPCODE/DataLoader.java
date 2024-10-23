

import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class DataLoader {
    
    private static final String USERS_FILE = "users.json";


    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            JSONParser parser = new JSONParser();
            JSONArray usersJSON = (JSONArray) parser.parse(new FileReader(USERS_FILE));
            
            for (Object obj : usersJSON) {
                JSONObject userJSON = (JSONObject) obj;
                int userID = ((Long) userJSON.get("userID")).intValue();
                String email = (String) userJSON.get("email");
                String username = (String) userJSON.get("username");
                String password = (String) userJSON.get("password");
                String preferredLanguage = (String) userJSON.get("preferredLanguage");

                JSONArray progressTrackerJSON = (JSONArray) userJSON.get("progressTracker");
                ProgressTracker progressTracker = parseProgressTracker(progressTrackerJSON);

                JSONArray achievementsJSON = (JSONArray) userJSON.get("achievements");
                ArrayList<Achievement> achievements = parseAchievements(achievementsJSON);

                JSONObject leaderboardJSON = (JSONObject) userJSON.get("leaderboard");
                Leaderboard leaderboard = parseLeaderboard(leaderboardJSON);

                User user = new User(userID, email, username, password, preferredLanguage, progressTracker, achievements, leaderboard);
                users.add(user);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static ArrayList<Lesson> getEveryLesson() {
        ArrayList<Lesson> lessons = new ArrayList<>();  
        return lessons;
    }

    public static ArrayList<Achievement> getAchievements() {
        ArrayList<Achievement> achievements = new ArrayList<>(); 
        return achievements;
    }

    public static HashMap<String, Integer> getLeaderboardScores() {
        HashMap<String, Integer> leaderboardScores = new HashMap<>();
        try {
            JSONParser parser = new JSONParser();
            JSONArray usersJSON = (JSONArray) parser.parse(new FileReader(USERS_FILE));

            for (Object obj : usersJSON) {
                JSONObject userJSON = (JSONObject) obj;
                String username = (String) userJSON.get("username");
                JSONObject leaderboardJSON = (JSONObject) userJSON.get("leaderboard");
                int totalScore = ((Long) leaderboardJSON.get("totalScore")).intValue();
                leaderboardScores.put(username, totalScore);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return leaderboardScores;
    }

    // Helper method
    private static ProgressTracker parseProgressTracker(JSONArray progressTrackerJSON) {
        ProgressTracker progressTracker = new ProgressTracker();
        for (Object obj : progressTrackerJSON) {
            JSONObject progressJSON = (JSONObject) obj;
            double progress = Double.parseDouble((String) progressJSON.get("progress"));
            progressTracker.addProgress(progress);
        }
        return progressTracker;
    }


    private static ArrayList<Achievement> parseAchievements(JSONArray achievementsJSON) {
        ArrayList<Achievement> achievements = new ArrayList<>();
        for (Object obj : achievementsJSON) {
            JSONObject achievementJSON = (JSONObject) obj;
            int achievementID = ((Long) achievementJSON.get("achievement")).intValue();
            String title = (String) achievementJSON.get("title");
            String description = (String) achievementJSON.get("description");
            int dateAchieved = ((Long) achievementJSON.get("dateAchieved")).intValue();
            int score = ((Long) achievementJSON.get("score")).intValue();
            Achievement achievement = new Achievement(achievementID, title, description, dateAchieved, score);
            achievements.add(achievement);
        }
        return achievements;
    }

    private static Leaderboard parseLeaderboard(JSONObject leaderboardJSON) {
        int userRank = ((Long) leaderboardJSON.get("userRank")).intValue();
        int totalScore = ((Long) leaderboardJSON.get("totalScore")).intValue();
        ArrayList<Achievement> achievements = parseAchievements((JSONArray) leaderboardJSON.get("achievements"));
        Leaderboard leaderboard = new Leaderboard(userRank, totalScore, achievements);
        return leaderboard;
    }

}
