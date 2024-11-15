package com.model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class DataLoader {

    private static final String USERS_FILE = "/data/LLAppUsers.json"; // Adjust the path as necessary
    private static final String LESSONS_FILE = "/data/LLAppLessons.json";
    private static final String QUIZZES_FILE = "/data/LLAppQuiz.json";

    public static ArrayList<User> loadUsers() {
        ArrayList<User> users = new ArrayList<>();

        try {
            InputStream inputStream = DataLoader.class.getResourceAsStream(USERS_FILE);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            JSONArray usersJSON = (JSONArray) new JSONParser().parse(reader);

            for (Object obj : usersJSON) {
                JSONObject userJSON = (JSONObject) obj;

                String userID = (String) userJSON.get("userID");
                String email = (String) userJSON.get("email");
                String username = (String) userJSON.get("username");
                String password = (String) userJSON.get("password");
                String preferredLanguage = (String) userJSON.get("preferredLanguage");

                // Load progress trackers
                JSONArray progressTrackerJSON = (JSONArray) userJSON.get("progressTrackers");
                ArrayList<ProgressTracker> progressTrackers = parseProgressTracker(progressTrackerJSON, userID); // Updated

                // Create user and add to the list
                User user = new User(userID, username, email, password, preferredLanguage,
                        progressTrackers);
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users; // Return the list of users
    }

    private static ArrayList<ProgressTracker> parseProgressTracker(JSONArray progressTrackerJSON, String userID) {
        ArrayList<ProgressTracker> progressTrackers = new ArrayList<>();

        if (progressTrackerJSON != null) {
            for (Object obj : progressTrackerJSON) {
                ArrayList<String> completedLessons = new ArrayList<String>();

                JSONObject trackerJSON = (JSONObject) obj;
                JSONArray completeLessonJSON = (JSONArray) trackerJSON.get("completedLessons");
                for (Object s : completeLessonJSON){
                    completedLessons.add((String) s);
                }
                double progress = ((Number) trackerJSON.get("progress")).doubleValue();
                String language = (String) trackerJSON.get("language");
                ProgressTracker tracker = new ProgressTracker(progress, language, completedLessons); // Pass userID here
                progressTrackers.add(tracker);
            }
        }

        return progressTrackers; // Return the list of progress trackers
    }


    /**
     * Loads all lessons found in the lessons JSON file
     * @return an ArrayList of all lessons found in the lessons JSON file
     */
    public static ArrayList<Lesson> loadLessons() {
        ArrayList<Lesson> lessons = new ArrayList<>();

        try {
            InputStream inputStream = DataLoader.class.getResourceAsStream(LESSONS_FILE);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            JSONArray lessonsJSON = (JSONArray) new JSONParser().parse(reader);

            for (Object obj : lessonsJSON) {
                JSONObject lessonJSON = (JSONObject) obj;
                String lessonID = (String) lessonJSON.get("lessonID");
                String language = (String) lessonJSON.get("language");

                // Get the difficulty level and convert to enum
                String difficultyLevelString = (String) lessonJSON.get("difficultyLevel");
                Difficulty difficultyLevel = Difficulty.valueOf(difficultyLevelString.toUpperCase());

                String content = (String) lessonJSON.get("content");
                String duration = (String) lessonJSON.get("duration");
                String quizID = (String) lessonJSON.get("quizID");

                // Create and add the Lesson
                Lesson lesson = new Lesson(lessonID, language, difficultyLevel, content, duration, quizID);
                lessons.add(lesson);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lessons;
    }

    /**
     * Loads all quizzes in the Quizzes JSON file
     * @return An ArrayList of all questions found in the JSON file
     */
    public static ArrayList<Quiz> loadQuizzes() {
        ArrayList<Quiz> quizzes = new ArrayList<>();

        try {
            InputStream inputStream = DataLoader.class.getResourceAsStream(QUIZZES_FILE);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            JSONArray quizzesJSON = (JSONArray) new JSONParser().parse(reader);

            for (Object obj : quizzesJSON) {
                JSONObject quizJSON = (JSONObject) obj;
                String quizID = (String) quizJSON.get("quizID");
                String language = (String) quizJSON.get("language");
                String lessonID = (String) quizJSON.get("lessonID");
                JSONArray questionsListJSON = (JSONArray) quizJSON.get("questionsList");

                List<Question> questionsList = new ArrayList<>();

                for (Object questionObj : questionsListJSON) {
                    JSONObject questionJSON = (JSONObject) questionObj;
                    String questionID = (String) questionJSON.get("questionID");
                    String questionText = (String) questionJSON.get("question");

                    // Parse userOptions
                    JSONArray userOptionsJSON = (JSONArray) questionJSON.get("userOptions");
                    List<String> userOptions = new ArrayList<>();
                    if (userOptionsJSON != null) {
                        for (Object option : userOptionsJSON) {
                            userOptions.add((String) option);
                        }
                    }

                    // Parse correctAnswer
                    String correctAnswer = (String) questionJSON.get("correctAnswer");

                    // Determine question type and instantiate
                    if (userOptions != null && !userOptions.isEmpty()) {
                        // It's a multiple choice question
                        MultipleChoice mcQuestion = new MultipleChoice(questionText, questionID, userOptions, correctAnswer);
                        questionsList.add(mcQuestion);
                    } else if (questionID.contains("FillInTheBlank")) {
                        // It's a fill-in-the-blank question
                        FillInTheBlank fillInTheBlankQuestion = new FillInTheBlank(questionText, questionID, userOptions, correctAnswer);
                        questionsList.add(fillInTheBlankQuestion);
                    } else {
                        // It's a true/false question; no user options expected
                        TrueOrFalse tfQuestion = new TrueOrFalse(questionText, questionID, userOptions, correctAnswer);
                        questionsList.add(tfQuestion);
                    }
                }

                // Create a Quiz object and add it to the list
                Quiz quiz = new Quiz(quizID, language, lessonID, questionsList);
                quizzes.add(quiz);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return quizzes;
    }

}
