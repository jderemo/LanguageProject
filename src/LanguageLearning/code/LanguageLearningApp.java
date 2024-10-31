import java.util.Scanner;

public class LanguageLearningApp {

    // Variables
    private LessonList lessonList;
    private UserList userList;
    private User user;

    // Main method
    public static void main(String[] args){
        new LanguageLearningApp();
        return;
    }

    // Constructor
    private LanguageLearningApp(){
        this.lessonList = LessonList.getInstance();
        this.userList = UserList.getInstance();
        this.user = null; // Initially no user is logged in
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to our Language Learning App!\nWhat would you like to do? (register, login, quit)");
        switch (scanner.nextLine()){
            case "register":{
                break;
            }
            case "login":{
                login(scanner);
                break;
            }
            case "quit":{
                scanner.close();
                return;
            }
        }
        scanner.close();
    }

    // Method to register a user
    private User register(String username, String email, String password) {
        // Registration logic to create and return a new User
        return new User(username, email, password);
    }

    private void login(Scanner scanner){
        User locatedUser = null;
        while (locatedUser == null){
            System.out.println("Enter your username");
            String username = scanner.nextLine();
            locatedUser = userList.locateUserByUsername(username);
        }
        String password = "";
        while (!password.equals(locatedUser.getPassword())){
            System.out.println("Enter your password");
            password = scanner.nextLine();
            if (!password.equals(locatedUser.getPassword())){
                System.out.println("Incorrect! Try again.");
            }
        }
        System.out.println("Logged in!");
    }
}
