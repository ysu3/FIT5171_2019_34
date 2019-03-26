package rockets.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.naming.Name;

import static org.junit.jupiter.api.Assertions.*;

class RocketTest {

    private Rocket target;

    @BeforeEach
    public void setUp() {
        target = new Rocket("1", "2","3");
    }


    @DisplayName("should throw exception when pass null to setMassToLEO function")
    @Test
    public void shouldThrowExceptionWhenSetMassToLEOToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setMassToLEO(null));
        assertEquals("Mass cannot be null", exception.getMessage());
    }

    @DisplayName("should throw exception when pass null to setMassToLEO function")
    @Test
    public void shouldThrowExceptionWhenSetMassToGTOToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setMassToGTO(null));
        assertEquals("Mass cannot be null", exception.getMessage());
    }

    @DisplayName("should throw exception when pass null to setMassToLEO function")
    @Test
    public void shouldThrowExceptionWhenSetMassToOtherToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setMassToOther(null));
        assertEquals("Mass cannot be null", exception.getMessage());
    }

    @DisplayName("should throw exception when pass null to setFamily function")
    @Test
    public void shouldThrowExceptionWhenSetFamilyToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setFamily(null));
        assertEquals("Family cannot be null", exception.getMessage());
    }

    @DisplayName("should throw exception when pass null to setSeries function")
    @Test
    public void shouldThrowExceptionWhenSetSeriesToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setSeries(null));
        assertEquals("Series cannot be null", exception.getMessage());
    }

    @DisplayName("should throw exception when pass null to setRocketCode function")
    @Test
    public void shouldThrowExceptionWhenSetRocketCodeToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setRocketCode(null));
        assertEquals("RocketCode cannot be null", exception.getMessage());
    }
}