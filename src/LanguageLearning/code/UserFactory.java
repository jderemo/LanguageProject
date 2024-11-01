import java.util.Scanner;

// This does all of the "dirty work" 
// for the logging in, logging out, and registering of accounts.
// Will probably need to be reworked for when a GUI is created
public class UserFactory {

    public static void register(Scanner scanner) {
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
        User newUser = new User(username, email, password);
        UserList.getInstance().addUser(newUser);
        UserList.getInstance().saveUsers();
        System.out.println("Account created successfully!\nYou may now log in using the credentials you specified!");
        return;
    }

    public static User login(Scanner scanner) {
        User locatedUser = null;
        while (locatedUser == null) {
            System.out.println("Enter your username");
            String username = scanner.nextLine();
            locatedUser = UserList.getInstance().locateUserByUsername(username);
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

    public static void logout(User user){
        UserList.getInstance().updateUserById(user.getUserID(), user);
        System.out.println("Goodbye for now " + user.getUsername() + "!\nHope to see you again soon!");
        UserList.getInstance().saveUsers();
        user = null;
        return;
    }
}
