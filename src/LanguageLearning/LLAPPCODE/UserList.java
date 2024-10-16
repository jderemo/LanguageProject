import java.util.ArrayList;

public class UserList {

    // Variables
    private ArrayList<User> users;

    // Constructor
    public UserList() {
        this.users = new ArrayList<>();
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

    User authenticate(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

