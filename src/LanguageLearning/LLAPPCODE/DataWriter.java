import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataWriter {

    // Method to write data to a JSON-like file (manually constructing JSON format)
    public static void writeToJson(String filePath, User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("{\n");
            writer.write("\"name\": \"" + user.getName() + "\",\n");
            writer.write("\"lessons\": [\n");
            for (Lesson lesson : user.getLessons()) {
                writer.write("{\n");
                writer.write("\"title\": \"" + lesson.getTitle() + "\",\n");
                writer.write("\"quizQuestions\": [\n");
                for (QuizQuestion quizQuestion : lesson.getQuizQuestions()) {
                    writer.write("{\n");
                    writer.write("\"question\": \"" + quizQuestion.getQuestion() + "\",\n");
                    writer.write("\"answer\": \"" + quizQuestion.getAnswer() + "\"\n");
                    writer.write("},\n");
                }
                writer.write("]\n");
                writer.write("},\n");
            }
            writer.write("]\n");
            writer.write("}\n");
            System.out.println("Data successfully written to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }