package com.narriation;
import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader {

    private static final String USERS_FILE = "C:\\Users\\solom\\OneDrive\\Desktop\\CSCE247GIT\\src\\Narriation\\speek\\src\\LLAPPJSON\\json\\LLAppUsers.json"; // Adjust the path as necessary
    private static final String LESSONS_FILE = "C:\\Users\\solom\\OneDrive\\Desktop\\CSCE247GIT\\src\\Narriation\\speek\\src\\LLAPPJSON\\json\\LLAppLessons.json";
    private static final String QUIZZES_FILE = "C:\\Users\\solom\\OneDrive\\Desktop\\CSCE247GIT\\src\\Narriation\\speek\\src\\LLAPPJSON\\json\\LLAppQuiz.json";
    
    public static ArrayList<User> loadUsers() {
        ArrayList<User> users = new ArrayList<>();

        try {
            FileReader reader = new FileReader(USERS_FILE);
            JSONParser parser = new JSONParser();
            JSONArray usersJSON = (JSONArray) parser.parse(reader);

            for (Object obj : usersJSON) {
                JSONObject userJSON = (JSONObject) obj;
                
                int userID = ((Long) userJSON.get("userID")).intValue();
                String email = (String) userJSON.get("email");
                String username = (String) userJSON.get("username");
                String password = (String) userJSON.get("password");
                String preferredLanguage = (String) userJSON.get("preferredLanguage");

                // Load progress trackers
                JSONArray progressTrackerJSON = (JSONArray) userJSON.get("progressTrackers");
                ArrayList<ProgressTracker> progressTrackers = parseProgressTracker(progressTrackerJSON);

                // Load achievements
                JSONArray achievementsJSON = (JSONArray) userJSON.get("achievements");
                ArrayList<Achievement> achievements = parseAchievements(achievementsJSON);

                // Create user and add to the list
                User user = new User(userID, username, email, password, preferredLanguage, 
                                     progressTrackers, achievements);
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users; // Return the list of users
    }

    private static ArrayList<ProgressTracker> parseProgressTracker(JSONArray progressTrackerJSON) {
        ArrayList<ProgressTracker> progressTrackers = new ArrayList<>();
        
        // Supposed to implement parsing logic similar to the users load process
        // Needs logic to convert progressTrackerJSON to ProgressTracker objects

        return progressTrackers; // Return the list of progress trackers
    }

    private static ArrayList<Achievement> parseAchievements(JSONArray achievementsJSON) {
        ArrayList<Achievement> achievements = new ArrayList<>();
        
        // Supposed to implement parsing logic similar to the users load process
        // Needs logic to convert achievementsJSON to Achievement objects

        return achievements; // Return the list of achievements
    }

    public static ArrayList<Lesson> loadLessons() {
        ArrayList<Lesson> lessons = new ArrayList<>();

        try {
            FileReader reader = new FileReader(LESSONS_FILE);
            JSONParser parser = new JSONParser();
            JSONArray lessonsJSON = (JSONArray) parser.parse(reader);

            for (Object obj : lessonsJSON) {
                JSONObject lessonJSON = (JSONObject) obj;
                int lessonID = ((Long) lessonJSON.get("lessonID")).intValue();
                String language = (String) lessonJSON.get("language");

                // Get the difficulty level and convert to enum
                String difficultyLevelString = (String) lessonJSON.get("difficultyLevel");
                Difficulty difficultyLevel = Difficulty.valueOf(difficultyLevelString.toUpperCase()); // Ensure it matches the enum case

                String content = (String) lessonJSON.get("content");
                String duration = (String) lessonJSON.get("duration");
                JSONArray feedbackListJSON = (JSONArray) lessonJSON.get("feedbackList");

                // Convert JSONArray to ArrayList<String>
                ArrayList<String> feedbackList = new ArrayList<>();
                for (Object feedback : feedbackListJSON) {
                    feedbackList.add((String) feedback);
                }

                // Create a Lesson object and add it to the list
                Lesson lesson = new Lesson(lessonID, language, difficultyLevel, content, duration, feedbackList);
                lessons.add(lesson);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lessons;
    }

    public static ArrayList<Lesson> loadQuizzes() {
        ArrayList<Lesson> lessons = new ArrayList<>();

        try {
            FileReader reader = new FileReader(QUIZZES_FILE);
            JSONParser parser = new JSONParser();
            JSONArray quizzesJSON = (JSONArray) parser.parse(reader);

            for (Object obj : quizzesJSON) {
                JSONObject quizJSON = (JSONObject) obj;
                String quizID = (String) quizJSON.get("quizID");
                ArrayList<Quiz> quizzes = (String) quizJSON.get("language");

                // Get the difficulty level and convert to enum
                String difficultyLevelString = (String) lessonJSON.get("difficultyLevel");
                Difficulty difficultyLevel = Difficulty.valueOf(difficultyLevelString.toUpperCase()); // Ensure it matches the enum case

                String content = (String) lessonJSON.get("content");
                String duration = (String) lessonJSON.get("duration");
                JSONArray feedbackListJSON = (JSONArray) lessonJSON.get("feedbackList");

                // Convert JSONArray to ArrayList<String>
                ArrayList<String> feedbackList = new ArrayList<>();
                for (Object feedback : feedbackListJSON) {
                    feedbackList.add((String) feedback);
                }

                // Create a Lesson object and add it to the list
                Lesson lesson = new Lesson(lessonID, language, difficultyLevel, content, duration, feedbackList);
                lessons.add(lesson);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lessons;
    }
}
