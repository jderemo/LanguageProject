import java.util.ArrayList;

public class UserList {
    private static UserList instance;

    // Variables
    private ArrayList<User> users;

    // Constructor
    private UserList() {
        this.users = new ArrayList<>();
    }

    public UserList getInstance(){
        if (instance == null){
            instance = new UserList();
        }

        return instance;
    }

    // Method to get a user by username
    public User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null; // Return null if user not found
    }

    // Method to add a user
    public void addUser(User user) {
        users.add(user);
    }

    // Method to remove a user by userID
    public void removeUser(int userID) {
        users.removeIf(user -> user.getUserID() == userID);
    }
}

