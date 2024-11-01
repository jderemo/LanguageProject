package com.techtroops;

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

    public ArrayList<User> getUsers(){
        return users;
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

    public void updateUserById(String userId, User user){
        for(User u : users){
            if (u.getUserID().equals(userId)){
                u = user;
                return;
            }
        }
        System.out.println("Couldn't find user with ID: " + userId);
    }

    public void saveUsers(){
        DataWriter.saveUsers();
        return;
    }
}

