package Entity;

import java.util.Optional;

public class User {
    private String id;
    private String username;
    private String email;
    private Optional<String> phoneNumber = Optional.empty(); // Optional field
    private Optional<Address> address = Optional.empty(); // Optional field

    public User(String id, String username, String email,
            String phoneNumber, Address address) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phoneNumber = Optional.ofNullable(phoneNumber); // Tạo một Optional chứa giá trị nếu không null, hoặc
                                                             // Optional.empty() nếu null.​
        this.address = Optional.ofNullable(address); // Wrap in Optional
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Optional<String> getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = Optional.ofNullable(phoneNumber);
    }

    public Optional<Address> getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = Optional.ofNullable(address);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber.orElse("N/A") +
                ", address=" + address.map(Address::toString).orElse("N/A") +
                '}';
    }
}
