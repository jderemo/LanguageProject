import java.util.ArrayList;

public class UserList {

    // Variables
    private static UserList instance;
    private ArrayList<User> users;

    // Constructor
    private UserList() {
        this.users = DataLoader.loadUsers();
    }

    public static UserList getInstance(){
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
    public void removeUser(String userID) {
        users.removeIf(user -> user.getUserID() == userID);
    }

    public User locateUserByUsername(String username){
        for(User u : users){
            if (u.getUsername().equals(username)){
                System.out.println("Found User with name " + username);
                return u;
            }
        }
        System.out.println("Could not find user with name " + username);
        return null;
    }

    User authenticate(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

