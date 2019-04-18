package rockets.model;

import java.util.Objects;
import java.util.Set;

import static org.apache.commons.lang3.Validate.*;

import static org.apache.commons.lang3.Validate.notBlank;

public class User extends Entity {
    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Set<Rocket> rocketSet;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        // User's last Name can not be empty
        notEmpty(lastName,"Last name can not be empty");
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        // Creat the email format by the regular expression.
        String emailFormat = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        notBlank(email, "email cannot be null or empty");
        // email can not be empty validation
        notEmpty(email,"email cannot be empty");
        // email format validation
        matchesPattern(email,emailFormat,"Use the valid email address");
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        notNull(password, "password cannot be null or empty");
        // Check the password format
        String passwordFormat = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
        matchesPattern(password,passwordFormat,"password must contain 8 - 16 characters and composed by number and characters");
        this.password = password;
    }

    // match the given password against user's password and return the result
    public boolean isPasswordMatch(String password) {
        return this.password.equals(password.trim());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Set<Rocket> getRocketSet() {
        return rocketSet;
    }

    public void setRocketSet(Set<Rocket> rocketSet) {
        this.rocketSet = rocketSet;
    }
}
