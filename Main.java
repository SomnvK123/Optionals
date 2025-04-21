import Entity.*;
import Enum.*;
import Manager.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        UserManager manager = new UserManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            showMenu();
            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
                Action action = Action.getAction(choice);

                switch (action) {
                    case ADD_USER -> {
                        User user = createUserFromInput(sc);
                        manager.addUser(user);
                        System.out.println("User added successfully!");
                    }
                    case FIND_USER -> {
                        System.out.print("Enter user ID to find: ");
                        String id = sc.nextLine();
                        manager.findUserById(id)
                                .ifPresentOrElse(
                                        u -> System.out.println("Found: " + u),
                                        () -> System.out.println("User not found."));
                    }
                    case GET_EMAIL_DOMAIN -> {
                        System.out.print("Enter user ID to get email domain: ");
                        String id = sc.nextLine();
                        manager.findUserById(id)
                                .flatMap(manager::getUserEmailDomain)
                                .ifPresentOrElse(
                                        domain -> System.out.println("Email domain: " + domain),
                                        () -> System.out.println("Could not retrieve email domain."));
                    }
                    case GET_CITY -> {
                        System.out.print("Enter user ID to get city: ");
                        String id = sc.nextLine();
                        manager.findUserById(id)
                                .flatMap(manager::getUserCity)
                                .ifPresentOrElse(
                                        city -> System.out.println("City: " + city),
                                        () -> System.out.println("Could not retrieve city."));
                    }
                    case GET_USER_BY_CITY -> {
                        System.out.print("Enter city to search user: ");
                        String city = sc.nextLine();
                        List<User> find = manager.findUserByCity(city);
                        if (find.isEmpty()) {
                            System.out.println("No user found in city: " + city);
                        } else {
                            System.out.println("Found users in city " + city + ":");
                            find.forEach(user -> System.out.println(" - " + user));
                        }
                    }

                    case GET_USER_BY_EMAIL -> {
                        String email = sc.nextLine();
                        System.out.println("Email domain: " + email);
                        List<User> find = manager.findUsersByEmailDomain(email);
                        if (find.isEmpty()) {
                            System.out.println("No user found in email domain: " + email);
                        } else {
                            System.out.println("Found users in email domain " + email + ":");
                            find.forEach(user -> System.out.println(" - " + user));
                        }
                    }
                    case EXIT -> {
                        System.out.println("Exit");
                        return; // Exit the loop & program
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid choice. " + e.getMessage());
            }
        }
    }

    private static void showMenu() {
        System.out.println("\nUSER MANAGEMENT MENU");
        for (Action action : Action.values()) {
            System.out.println(action.getId() + ": " + action.getName());
        }
        System.out.print("Enter your choice: ");
    }

    private static User createUserFromInput(Scanner sc) {
        System.out.print("ID: ");
        String id = sc.nextLine();

        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Phone number (optional): ");
        String phone = sc.nextLine();

        System.out.print("Street: ");
        String street = sc.nextLine();

        System.out.print("City: ");
        String city = sc.nextLine();

        System.out.print("Zip Code: ");
        String zipCode = sc.nextLine();

        System.out.print("Country: ");
        String country = sc.nextLine();

        Address address = new Address(street, city, country, zipCode);

        return new User(id, username, email,
                phone.isBlank() ? null : phone,
                address);
    }
}
