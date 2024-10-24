package com.narriation;

import java.io.FileWriter;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter {

    private static final String USERS_FILE = "C:\\Users\\solom\\OneDrive\\Desktop\\CSCE247GIT\\src\\Narriation\\speek\\src\\LLAPPJSON\\json\\LLAppUsers.json"; 
    private static final String LESSONS_FILE = "C:\\Users\\solom\\OneDrive\\Desktop\\CSCE247GIT\\src\\Narriation\\speek\\src\\LLAPPJSON\\json\\LLAppLessons.json";

    // Write users to file
    public static void saveUsers(ArrayList<User> users) {
        JSONArray usersJSON = new JSONArray();

        for (User user : users) {
            JSONObject userJSON = new JSONObject();
            userJSON.put("userID", user.getUserID());
            userJSON.put("email", user.getEmail());
            userJSON.put("username", user.getUsername());
            userJSON.put("password", user.getPassword());
            userJSON.put("preferredLanguage", user.getPreferredLanguage());

            // Save progress trackers
            JSONArray progressTrackersJSON = new JSONArray();
            for (ProgressTracker tracker : user.getProgressTrackers()) {
                JSONObject trackerJSON = new JSONObject();
                // Add properties of tracker to trackerJSON
                progressTrackersJSON.add(trackerJSON);
            }
            userJSON.put("progressTrackers", progressTrackersJSON);

            // Save achievements
            JSONArray achievementsJSON = new JSONArray();
            for (Achievement achievement : user.getAchievements()) {
                JSONObject achievementJSON = new JSONObject();
                achievementJSON.put("achievementID", achievement.getAchievementID());
                achievementJSON.put("title", achievement.getTitle());
                achievementJSON.put("description", achievement.getDescription());
                achievementJSON.put("dateAchieved", achievement.getDateAchieved());
                achievementJSON.put("score", achievement.getScore());
                achievementsJSON.add(achievementJSON);
            }
            userJSON.put("achievements", achievementsJSON);

            usersJSON.add(userJSON);
        }

        writeToFile(USERS_FILE, usersJSON);
    }

    // Write lessons to file
    public static void saveLessons(ArrayList<Lesson> lessons) {
        JSONArray lessonsJSON = new JSONArray();

        for (Lesson lesson : lessons) {
            JSONObject lessonJSON = new JSONObject();
            lessonJSON.put("lessonID", lesson.getLessonID());
            lessonJSON.put("language", lesson.getLanguage());
            lessonJSON.put("difficultyLevel", lesson.getDifficultyLevel().toString());
            lessonJSON.put("content", lesson.getContent());
            lessonJSON.put("duration", lesson.getDuration());

            // Save feedback list
            JSONArray feedbackListJSON = new JSONArray();
            for (String feedback : lesson.getFeedbackList()) {
                feedbackListJSON.add(feedback);
            }
            lessonJSON.put("feedbackList", feedbackListJSON);

            lessonsJSON.add(lessonJSON);
        }

        writeToFile(LESSONS_FILE, lessonsJSON);
    }

    // Utility method to write JSON data to a file
    private static void writeToFile(String filePath, JSONArray data) {
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(data.toJSONString());
            file.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
