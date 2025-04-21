package Manager;

import Entity.*;
import java.util.*;

public class UserManager {
    private static final Map<String, User> users = new HashMap<>(); // Map to store users by ID

    public void addUser(User user) {
        users.put(user.getId(), user); // Add user to the map
    }

    public Optional<User> findUserById(String id) {
        return users.values().stream().filter(user -> user.getId().equals(id)).findFirst(); // Find user by ID
    }

    public Optional<User> getUserEmailDomain(User user) {
        return users.values().stream()
                .filter(u -> u.getEmail().endsWith("@example.com")) // Filter users by email domain
                .findFirst(); // Return the first user found with the specified domain
    }

    public Optional<String> getUserCity(User user) {
        return user.getAddress().map(Address::getCity); // Get the city from the user's address
    }
}
