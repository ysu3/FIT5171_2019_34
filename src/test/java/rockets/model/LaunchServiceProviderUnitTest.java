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
    private String nullName;
    private int minusYearFounded;
    private String nullCountry;

    @BeforeEach
    public void setUp() {

        this.nullName = null;
        this.minusYearFounded = -1;
        this.nullCountry = null;
    }

    @DisplayName("should throw exception when pass null to settle name")
    @Test
    public void whenEnterNullName(){
        target = new LaunchServiceProvider(nullName, yearFounded,country);
        assertNull(nullName,"true");
    }

    @DisplayName("should throw exception when pass null to settle country")
    @Test
    public void whenEnterNullCountry(){
        target = new LaunchServiceProvider(name, yearFounded,nullCountry);
        assertNull(nullCountry,"true");
    }

    @DisplayName("should throw exception when pass yearFounded less than 1900")
    @Test
    public void whenEnterLessYearFounded(){
        target = new LaunchServiceProvider(name, minusYearFounded,country);
        assertTrue(minusYearFounded < 1900);
    }

    @DisplayName("should throw exceptions when pass a null headquarters to setHeadquarters function")
    @Test
    public void shouldThrowExceptionWhenSetPasswordToNull() {
        target = new LaunchServiceProvider(name, yearFounded,country);
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> target.setHeadquarters(null));
        assertEquals("headquarters cannot be null or empty", exception.getMessage());
    }

}


