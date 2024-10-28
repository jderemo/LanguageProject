import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Datawriter {

    // Method to save user data to a JSON file
    public static void saveUsers(ArrayList<User> users) {
        try (FileWriter writer = new FileWriter("users.json")) {
            writer.write("[\n");
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                writer.write("  {\n");
                writer.write("    \"userID\": " + user.getUserID() + ",\n");
                writer.write("    \"username\": \"" + user.getUsername() + "\",\n");
                writer.write("    \"email\": \"" + user.getEmail() + "\",\n");
                writer.write("    \"preferredLanguage\": \"" + user.getPreferredLanguage() + "\",\n");
                
                writer.write("    \"progress\": {\n");
                writer.write("      \"trackerID\": " + user.getProgress().getTrackerID() + ",\n");
                writer.write("      \"progress\": " + user.getProgress().getProgress() + ",\n");
                
                writer.write("      \"completedLessons\": [");
                for (int j = 0; j < user.getProgress().getCompletedLessons().size(); j++) {
                    Lesson lesson = user.getProgress().getCompletedLessons().get(j);
                    writer.write(lesson.getLessonID());
                    if (j < user.getProgress().getCompletedLessons().size() - 1) writer.write(", ");
                }
                writer.write("],\n");

                writer.write("      \"incompleteLessons\": [");
                for (int k = 0; k < user.getProgress().getIncompleteLessons().size(); k++) {
                    Lesson lesson = user.getProgress().getIncompleteLessons().get(k);
                    writer.write(lesson.getLessonID());
                    if (k < user.getProgress().getIncompleteLessons().size() - 1) writer.write(", ");
                }
                writer.write("]\n    }\n");

                writer.write("  }");
                if (i < users.size() - 1) writer.write(",\n");
                else writer.write("\n");
            }
            writer.write("]\n");
            System.out.println("User data saved to users.json successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving user data.");
            e.printStackTrace();
        }
    }

    // Method to save lesson data to a JSON file
    public static void saveEveryLesson(ArrayList<Lesson> lessons) {
        try (FileWriter writer = new FileWriter("lessons.json")) {
            writer.write("[\n");
            for (int i = 0; i < lessons.size(); i++) {
                Lesson lesson = lessons.get(i);
                writer.write("  {\n");
                writer.write("    \"lessonID\": " + lesson.getLessonID() + ",\n");
                writer.write("    \"language\": \"" + lesson.getLanguage() + "\",\n");
                writer.write("    \"difficultyLevel\": \"" + lesson.getDifficultyLevel() + "\",\n");
                writer.write("    \"content\": \"" + lesson.getContent() + "\",\n");
                writer.write("    \"duration\": \"" + lesson.getDuration() + "\",\n");
                writer.write("    \"rating\": " + lesson.getRating() + ",\n");

                writer.write("    \"feedbackList\": [");
                for (int j = 0; j < lesson.getFeedbackList().size(); j++) {
                    Feedback feedback = lesson.getFeedbackList().get(j);
                    writer.write("{ \"comment\": \"" + feedback.getComment() + "\", \"rating\": " + feedback.getRating() + " }");
                    if (j < lesson.getFeedbackList().size() - 1) writer.write(", ");
                }
                writer.write("]\n");

                writer.write("  }");
                if (i < lessons.size() - 1) writer.write(",\n");
                else writer.write("\n");
            }
            writer.write("]\n");
            System.out.println("Lesson data saved to lessons.json successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving lesson data.");
            e.printStackTrace();
        }
    }
}


    

    