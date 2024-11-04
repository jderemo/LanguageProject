package com.techtroops;

import java.util.ArrayList;

/**
 * Singleton list of all users of the LanguageLearningApp
 * Contains helper methods to interact with the list
 */
public class UserList {
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

    /**
     * Returns the ArrayList of Users
     * @return the entire ArrayList of Users
     */
    public ArrayList<User> getUsers(){
        return users;
    }

    /**
     * Adds a user to the User List
     * @param user User to add
     */
    public void addUser(User user) {
        users.add(user);
    }

    /**
     * Locates a user by their Username
     * @param username Username to search for
     * @return a user with the given username
     */
    public User locateUserByUsername(String username){
        for(User user : users){
            if (user.getUsername().equals(username)){
                System.out.println("Found User with name " + username);
                return user;
            }
        }
        return null;
    }

    /**
     * Replaces a user with the given User
     * @param userId User ID to search for
     * @param user The updated user
     */
    public void updateUserById(String userId, User user){
        for(User u : users){
            if (u.getUserID().equals(userId)){
                u = user;
                return;
            }
        }
        System.out.println("Couldn't find user with ID: " + userId);
    }

    /**
     * Invokes data writer to save users.
     */
    public void saveUsers(){
        DataWriter.saveUsers();
        return;
    }
}

