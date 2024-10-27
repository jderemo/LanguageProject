package com.narriation;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

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
                ArrayList<ProgressTracker> progressTrackers = parseProgressTracker(progressTrackerJSON, userID); // Updated
    
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
    

    private static ArrayList<ProgressTracker> parseProgressTracker(JSONArray progressTrackerJSON, int userID) {
        ArrayList<ProgressTracker> progressTrackers = new ArrayList<>();
        
        if (progressTrackerJSON != null) {
            for (Object obj : progressTrackerJSON) {
                JSONObject trackerJSON = (JSONObject) obj;
                String exerciseID = (String) trackerJSON.get("exerciseID");
                double progress = ((Number) trackerJSON.get("progress")).doubleValue();
                String language = (String) trackerJSON.get("language");
                ProgressTracker tracker = new ProgressTracker(exerciseID, progress, userID, language); // Pass userID here
                progressTrackers.add(tracker);
            }
        }
        
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
            FileReader reader = new FileReader(QUIZZES_FILE);
            JSONParser parser = new JSONParser();
            JSONArray quizzesJSON = (JSONArray) parser.parse(reader);
    
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
