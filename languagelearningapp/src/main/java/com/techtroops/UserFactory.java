package com.techtroops;

import java.util.Scanner;

/**
 * This does all of the "dirty work" 
 * for the logging in, logging out, and registering of accounts.
 * There is a console implementation of the above.
 * There are methods in this file that would allow for a GUI implementation
 */
public class UserFactory {
    private static UserList userList;
    private static Scanner scanner;

    private static UserFactory instance;

    private UserFactory(){
        userList = UserList.getInstance();
        scanner = LanguageLearningApp.scanner;
    }

    public static UserFactory getInstance(){
        if (instance == null){
            instance = new UserFactory();
        }
        return instance;
    }

    /**
     * Method that registers a user and adds them to the user list
     * Checks if the username already exists before creating
     * @param email User email
     * @param username User username
     * @param password User password
     */
    public boolean register(String email, String username, String password){
        if (userList.locateUserByUsername(username) != null){
            return false;
        }
        User newUser = new User(username, email, password);
        userList.addUser(newUser);
        userList.saveUsers();
        return true;
    }

    /**
     * 
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password){
        User locatedUser = userList.locateUserByUsername(username);

        if (locatedUser.getPassword().equals(password)){
            return locatedUser;
        }
        return null;
    }

    public void logout(User user){
        userList.updateUserById(user.getUserID(), user);
        System.out.println("Goodbye for now " + user.getUsername() + "!\nHope to see you again soon!");
        userList.saveUsers();
        return;
    }
    
    public User loginConsoleUi() {
        User locatedUser = null;
        while (locatedUser == null) {
            System.out.println("Enter your username");
            String username = scanner.nextLine();
            locatedUser = userList.locateUserByUsername(username);
        }
        String password = "";
        while (!password.equals(locatedUser.getPassword())) {
            System.out.println("Enter your password");
            password = scanner.nextLine();
            if (!password.equals(locatedUser.getPassword())) {
                System.out.println("Incorrect! Try again.");
            }
        }
        System.out.println("Logged in!");
        return locatedUser;
    }

    
    public void registerConsoleUi() {
        // Gather information
        // Email
        String email = "";
        System.out.println("Enter your email");
        email = scanner.nextLine();
        System.out.println("Does \"" + email + "\" look correct? (y, n)");
        if (scanner.nextLine().toLowerCase().equals("n")){
            return;
        }

        // Username
        String username = "";
        System.out.println("Enter your username");
        username = scanner.nextLine();
        System.out.println("Does \"" + username + "\" look correct? (y, n)");
        if (scanner.nextLine().toLowerCase().equals("n")){
            return;
        }

        // Password
        System.out.println("Enter your password");
        String password = "";
        password = scanner.nextLine();
        System.out.println("Type your password again to be sure it is correct.");
        if (!scanner.nextLine().equals(password)){
            System.out.println("Sorry! That was incorrect. Try registering again!");
            return;
        }

        // Create user
        if (register(email, username, password)){
            System.out.println("Account created successfully!\nYou may now log in using the credentials you specified!");
        } else {
            System.out.println("User already exists, try again");
        }
        return;
    }

}
