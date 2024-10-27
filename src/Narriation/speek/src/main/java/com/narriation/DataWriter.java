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
                trackerJSON.put("exerciseID", tracker.getExerciseID()); 
                trackerJSON.put("progress", tracker.getProgress()); 
                progressTrackersJSON.add(trackerJSON);
            }
            userJSON.put("progressTrackers", progressTrackersJSON);

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

            // Save questions list
            JSONArray questionsListJSON = new JSONArray();
            for (Question question : lesson.getQuestions()) {
                JSONObject questionJSON = new JSONObject();
                questionJSON.put("question", question.getQuestion());
                questionJSON.put("exerciseID", question.getExerciseID());
                questionJSON.put("correctAnswer", question.getAnswer());

                // Save user options
                JSONArray userOptionsJSON = new JSONArray();
                for (String option : question.getUserOptions()) {
                    userOptionsJSON.add(option);
                }
                questionJSON.put("userOptions", userOptionsJSON);

                // Add the question to the list
                questionsListJSON.add(questionJSON);
            }
            lessonJSON.put("questionsList", questionsListJSON);

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

    // Method to update progress for a user in the JSON file
    public static void updateProgress(ProgressTracker tracker) {
        ArrayList<User> users = DataLoader.loadUsers(); // Load existing users
        for (User user : users) {
            if (user.getUserID() == tracker.getUserID()) { 
                ProgressTracker existingTracker = user.getProgressTrackerByExerciseID(tracker.getExerciseID());
                if (existingTracker != null) {
                    existingTracker.setProgress(tracker.getProgress()); // Update the existing tracker
                } else {
                    user.addProgressTracker(tracker); // Add as a new tracker
                }
                break;
            }
        }
        saveUsers(users); // Save updated user list back to the JSON
    }

    public static void saveProgress(User user) {
        ArrayList<User> users = DataLoader.loadUsers(); // Load existing users
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserID() == user.getUserID()) { // Assuming userID is how we identify users
                users.set(i, user); // Update the user in the list
                break;
            }
        }
        saveUsers(users); // Save the updated user list back to the JSON file
    }
}
