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
                        System.out.print("📧 Enter user ID to get email domain: ");
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
        System.out.println("\n======= USER MANAGEMENT MENU =======");
        System.out.println("1. Add user");
        System.out.println("2. Find user by ID");
        System.out.println("3. Get user email domain");
        System.out.println("4. Get user city");
        System.out.println("5. Exit");
        System.out.print("👉 Enter your choice: ");
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
