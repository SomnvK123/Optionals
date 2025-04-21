package Manager;

import Entity.*;
import java.util.*;
import java.util.stream.Collectors;

public class UserManager {
    private static final Map<String, User> users = new HashMap<>();

    public void addUser(User user) {
        users.put(user.getId(), user); // Add user to the map
    }

    public Optional<User> findUserById(String id) {
        return users.values().stream() // get all user duoi dang Stream
                .filter(user -> user.getId().equals(id)).findFirst(); // Find user by ID
    }

    public Optional<User> getUserEmailDomain(User user) {
        return users.values().stream() // get all user duoi dang Stream
                .filter(u -> u.getEmail().endsWith("@gmail.com")) // Filter users by email domain
                .findFirst(); // Return the first user
    }

    public Optional<String> getUserCity(User user) {
        return user.getAddress().map(Address::getCity); // Get the city from the user's address
    }

    public List<User> findUserByCity(String city) {
        return users.values().stream().filter( // get all user duoi dang Stream
                user -> getUserCity(user) // kiem tra user co city ko
                        .map(c -> c.equalsIgnoreCase(city)). // neu co thi kiem tra dau vao
                        orElse(false) // neu ko
        ).collect(Collectors.toList());
    }

    public List<User> findUsersByEmailDomain(String domain) {
        return users.values().stream()
                .filter(user -> getUserEmailDomain(user)
                        .map(d -> d.equals(domain))
                        .orElse(false))
                .collect(Collectors.toList());
    }
}
