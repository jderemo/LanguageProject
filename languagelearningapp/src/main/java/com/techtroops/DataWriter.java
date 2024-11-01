package com.techtroops;

import java.io.FileWriter;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter {

    private static final String USERS_FILE = "/data/LLAppUsers.json"; 
    private static final String LESSONS_FILE = "/data/LLAppLessons.json";

    // Write users to file
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

    // Write lessons to file
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

    // Method to write JSON data to a file
    private static void writeToFile(String filePath, JSONArray data) {
        try {
            String path = DataWriter.class.getResource(filePath).toURI().getPath();
            System.out.println(path);
            FileWriter file = new FileWriter(path);
            file.write(data.toJSONString());
            file.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateUser(User user) {
        UserList.getInstance().updateUserById(user.getUserID(), user);
        saveUsers(); // Save the updated user list back to the JSON file
    }
}
