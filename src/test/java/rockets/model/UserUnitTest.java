package rockets.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.regex.*;

import static org.junit.jupiter.api.Assertions.*;

public class UserUnitTest {
    private User target;

    @BeforeEach
    public void setUp() {
        target = new User();
    }


    //email address should not be empty
    @DisplayName("should throw exception when pass a empty email address to setEmail function")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void shouldThrowExceptionWhenSetEmailToEmpty(String email) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setEmail(email));
        assertEquals("email cannot be null or empty", exception.getMessage());
    }

    //email address should not be null
    @DisplayName("should throw exception when pass null to setEmail function")
    @Test
    public void shouldThrowExceptionWhenSetEmailToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setEmail(null));
        assertEquals("email cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass null to setFirstname function")
    @Test
    public void shouldThrowExceptionWhensetFirstnameToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setFirstName(null));
        assertEquals("First name cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass null to setLastname function")
    @Test
    public void shouldThrowExceptionWhensetLastnameToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setLastName(null));
        assertEquals("Last name cannot be null or empty", exception.getMessage());
    }

    //password should not be null
    @DisplayName("should throw exceptions when pass a null password to setPassword function")
    @Test
    public void shouldThrowExceptionWhenSetPasswordToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> target.setPassword(null));
        assertEquals("password cannot be null or empty", exception.getMessage());
    }

    //password should have characters and numbers
    @DisplayName("should throw exceptions when pass a wrong format of password")
    public void shouldReturnFalseWhenPasswordFormatIsWrong(){
        String password = "123";
        target.setPassword(password);
        String regEx1 = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
        boolean isRight = false;
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(password);
        if (m.matches()){
            isRight = true;
        }
        else{
            isRight = false;
        }
        assertFalse(isRight);
    }

    @DisplayName("should return true when two users have the same email")
    @Test
    public void shouldReturnTrueWhenUsersHaveSameEmail() {
        String email = "abc@example.com";
        target.setEmail(email);
        User anotherUser = new User();
        anotherUser.setEmail(email);
        assertTrue(target.equals(anotherUser));
    }


    @DisplayName("should return false when two users have different emails")
    @Test
    public void shouldReturnFalseWhenUsersHaveDifferentEmails() {
        target.setEmail("abc@example.com");
        User anotherUser = new User();
        anotherUser.setEmail("def@example.com");
        assertFalse(target.equals(anotherUser));
    }

    //email format should have "@" and "."
//    @DisplayName("should return false when email address is not in right format")
//    @Test
//    public void shouldReturnFalseWhenEmailIsNotInTheRightFormat(){
//        String email = "a";
//        boolean isRight = false;
//        target.setEmail(email);
//        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
//        Pattern p;
//        Matcher m;
//        p = Pattern.compile(regEx1);
//        m = p.matcher(email);
//        if (m.matches()){
//            isRight = true;
//        }
//        else{
//            isRight = false;
//        }
//        assertFalse(isRight);
//    }
}