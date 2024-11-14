package com.model;

import java.io.FileWriter;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter {

    private static final String USERS_FILE = "/data/LLAppUsers.json"; 
    private static final String LESSONS_FILE = "/data/LLAppLessons.json";

    /**
     * Saves all users in the user list
     */
    @SuppressWarnings("unchecked")
    public static void saveUsers() {
        ArrayList<User> users = UserList.getInstance().getUsers();
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
                JSONArray trackerCompleteLessonJSON = new JSONArray();
                for (String lesson : tracker.getCompletedLessons()){
                    trackerCompleteLessonJSON.add(lesson);
                }
                trackerJSON.put("language", tracker.getLanguage());
                trackerJSON.put("completedLessons", trackerCompleteLessonJSON);
                trackerJSON.put("progress", tracker.getProgress()); 
                progressTrackersJSON.add(trackerJSON);
            }
            userJSON.put("progressTrackers", progressTrackersJSON);

            usersJSON.add(userJSON);
        }

        writeToFile(USERS_FILE, usersJSON);
    }

    /**
     * Writes all lessons found in the lesson list.
     */
    @SuppressWarnings("unchecked")
    public static void saveLessons() {
        ArrayList<Lesson> lessons = LessonList.getInstance().getLessons();
        JSONArray lessonsJSON = new JSONArray();

        for (Lesson lesson : lessons) {
            JSONObject lessonJSON = new JSONObject();
            lessonJSON.put("lessonID", lesson.getLessonID());
            lessonJSON.put("language", lesson.getLanguage());
            lessonJSON.put("difficultyLevel", lesson.getDifficultyLevel().toString());
            lessonJSON.put("content", lesson.getContent());
            lessonJSON.put("duration", lesson.getDuration());

            lessonsJSON.add(lessonJSON);
        }

        writeToFile(LESSONS_FILE, lessonsJSON);
    }

    /**
     * Writes a JSON Array to a specified file
     * @param filePath file path constant
     * @param data the JSON Array data
     */
    private static void writeToFile(String filePath, JSONArray data) {
        try {
            String path = DataWriter.class.getResource(filePath).toURI().getPath();
            FileWriter file = new FileWriter(path);
            file.write(data.toJSONString());
            file.flush();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateUser(User user) {
        UserList.getInstance().updateUserById(user.getUserID(), user);
        saveUsers(); // Save the updated user list back to the JSON file
    }
}
