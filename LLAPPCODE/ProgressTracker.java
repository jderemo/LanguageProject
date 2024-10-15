import java.util.ArrayList;

public class ProgressTracker {

    // Variables
    private String language;
    private double progress;
    private ArrayList<Lesson> completedLessons;
    private ArrayList<Lesson> incompleteLessons;

    // Constructor
    public ProgressTracker(String language) {
        this.language = language;
        this.progress = 0.0;
        this.completedLessons = new ArrayList<>();
        this.incompleteLessons = new ArrayList<>();
    }

    // Method Stubs
    public void updateProgress(int lessonId) {
        completedLessons.add(lessonId);

        int i = 0;
        for(int l : incompleteLessons){
            if (l == lessonId){
                incompleteLessons.remove(lessonId);
            }
            i++;
        }
        this.progress = (double) completedLessons.size() / ((double) completedLessons.size() + (double) incompleteLessons.size())
    }

    // Getters and Setters
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public ArrayList<Lesson> getCompletedLessons() {
        return completedLessons;
    }

    public void setCompletedLessons(ArrayList<Lesson> completedLessons) {
        this.completedLessons = completedLessons;
    }

    public ArrayList<Lesson> getIncompleteLessons() {
        return incompleteLessons;
    }

    public void setIncompleteLessons(ArrayList<Lesson> incompleteLessons) {
        this.incompleteLessons = incompleteLessons;
    }
}
