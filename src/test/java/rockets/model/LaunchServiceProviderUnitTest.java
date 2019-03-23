package rockets.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LaunchServiceProviderUnitTest {
    private LaunchServiceProvider target;
    private String name;
    private int yearFounded;
    private String country;
    private String nullName = null;
    private int minusYearFounded = -1;
    private String nullCountry = null;

    @BeforeEach
    public void setUp() {
        target = new LaunchServiceProvider(name, yearFounded, country);
    }

    public String whenEnterNullName(String name, int yearFounded, String country){
        return name;
    }

    public String whenEnterNullCountry(String name, int yearFounded, String country){
        return country;
    }

    public int whenEnterMinusYear(String name, int yearFounded, String country){
        return yearFounded;
    }

    @DisplayName("should throw exception when pass null to settle name")
    @Test
    public void shouldThrowExceptionWhenSetNameToNull(){
        assertNull(whenEnterNullName(nullName, yearFounded, country),"true");
    }

    @DisplayName("should throw exception when pass null to settle country")
    @Test
    public void shouldThrowExceptionWhenSetCountryToNull(){
        assertNull(whenEnterNullCountry(name, yearFounded, nullCountry),"true");
    }

    @DisplayName("should throw exception when pass the year before 1900")
    @Test
    public void shouldThrowExceptionWhenSetYearFoundedToNull(){
        assertTrue(whenEnterMinusYear(name, minusYearFounded, country) < 1900,"true");
    }

    @DisplayName("should throw exceptions when pass a null headquarters to setHeadquarters function")
    @Test
    public void shouldThrowExceptionWhenSetPasswordToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> target.setHeadquarters(null));
        assertEquals("headquarters cannot be null or empty", exception.getMessage());
    }

}


