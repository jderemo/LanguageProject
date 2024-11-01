package com.techtroops;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class DataLoader {

    private static final String USERS_FILE = "/data/LLAppUsers.json"; // Adjust the path as necessary
    private static final String LESSONS_FILE = "/data/LLAppUsers.json";
    private static final String QUIZZES_FILE = "/data/LLAppUsers.json";

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

                // Check if questionsList is present and not null
                JSONArray questionsListJSON = (JSONArray) lessonJSON.get("questionsList");
                List<Question> questions = new ArrayList<>();

                if (questionsListJSON != null) {  // Null check
                    for (Object questionObj : questionsListJSON) {
                        JSONObject questionJSON = (JSONObject) questionObj;
                        String questionText = (String) questionJSON.get("question");
                        String exerciseID = (String) questionJSON.get("exerciseID");
                        String correctAnswer = (String) questionJSON.get("correctAnswer");
                        JSONArray userOptionsJSON = (JSONArray) questionJSON.get("userOptions");

                        List<String> userOptions = new ArrayList<>();
                        for (Object option : userOptionsJSON) {
                            userOptions.add((String) option);
                        }

                        // Check type to instantiate the correct Question subclass
                        if (exerciseID.contains("MultipleChoice")) {
                            questions.add(new MultipleChoice(questionText, exerciseID, userOptions, correctAnswer));
                        } else if (exerciseID.contains("FillInTheBlank")) {
                            questions.add(new FillInTheBlank(questionText, exerciseID, userOptions, correctAnswer));
                        }
                    }
                }

                // Create and add the Lesson
                Lesson lesson = new Lesson(lessonID, language, difficultyLevel, content, duration, questions);
                lessons.add(lesson);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lessons;
    }

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
                JSONArray questionsListJSON = (JSONArray) quizJSON.get("questionsList");

                List<Question> questionsList = new ArrayList<>();

                for (Object questionObj : questionsListJSON) {
                    JSONObject questionJSON = (JSONObject) questionObj;
                    String exerciseID = (String) questionJSON.get("exerciseID");
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
                        MultipleChoice mcQuestion = new MultipleChoice(questionText, exerciseID, userOptions, correctAnswer);
                        questionsList.add(mcQuestion);
                    } else if (exerciseID.contains("FillInTheBlank")) {
                        // It's a fill-in-the-blank question
                        FillInTheBlank fillInTheBlankQuestion = new FillInTheBlank(questionText, exerciseID, userOptions, correctAnswer);
                        questionsList.add(fillInTheBlankQuestion);
                    } else {
                        // It's a true/false question; no user options expected
                        TrueOrFalse tfQuestion = new TrueOrFalse(questionText, exerciseID, userOptions, correctAnswer);
                        questionsList.add(tfQuestion);
                    }
                }

                // Create a Quiz object and add it to the list
                Quiz quiz = new Quiz(quizID, questionsList);
                quizzes.add(quiz);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return quizzes;
    }

}
