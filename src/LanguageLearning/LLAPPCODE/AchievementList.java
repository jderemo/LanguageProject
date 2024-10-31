import java.util.ArrayList;

public class AchievementList {
    private static AchievementList instance;

    private ArrayList<Achievement> achievements;

    private AchievementList(){
        this.achievements = DataLoader.GetAllAchievements();
    }

    public AchievementList getInstance(){
        if (instance == null){
            instance = new AchievementList();
        }

        return instance;
    }

    public Achievement getAchievement(int id){
        for(Achievement a : achievements){
            if (a.getAchievement() == id){
                return a;
            }
        }
        return null; // return null if no achievement was found
    }
}
