package rockets.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.naming.Name;

import static org.junit.jupiter.api.Assertions.*;

class RocketTest {

    private Rocket target;
    private LaunchServiceProvider lsp;

    @BeforeEach
    public void setUp() {
        lsp = new LaunchServiceProvider("ULA", 1990, "USA");
        target = new Rocket("1", "2",lsp);
    }

    @AfterEach
    public void tearDown() {
    }

    @DisplayName("should create rocket successfully when given right parameters to constructor")
    @Test
    public void shouldConstructRocketObject() {
        String name = "BFR";
        String country = "USA";
        LaunchServiceProvider manufacturer = new LaunchServiceProvider("SpaceX", 2002, "USA");
        Rocket bfr = new Rocket(name, country, manufacturer);
        assertNotNull(bfr);
    }

    @DisplayName("should throw exception when given null manufacturer to constructor")
    @Test
    public void shouldThrowExceptionWhenNoManufacturerGiven() {
        String name = "BFR";
        String country = "USA";
        assertThrows(NullPointerException.class, () -> new Rocket(name, country, null));
    }

    @DisplayName("should set rocket massToLEO value")
    @ValueSource(strings = {"10000", "15000"})
    public void shouldSetMassToLEOWhenGivenCorrectValue(String massToLEO) {
        String name = "BFR";
        String country = "USA";
        LaunchServiceProvider manufacturer = new LaunchServiceProvider("SpaceX", 2002, "USA");

        Rocket bfr = new Rocket(name, country, manufacturer);

        bfr.setMassToLEO(massToLEO);
        assertEquals(massToLEO, bfr.getMassToLEO());
    }


    //MassToGTO should not be null
    @DisplayName("should throw exception when pass null to setMassToGTO function")
    @Test
    public void shouldThrowExceptionWhenSetMassToGTOToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setMassToGTO(null));
        assertEquals("Mass cannot be null", exception.getMessage());
    }

    //MassToOther should not be null
    @DisplayName("should throw exception when pass null to setMassToOther function")
    @Test
    public void shouldThrowExceptionWhenSetMassToOtherToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setMassToOther(null));
        assertEquals("Mass cannot be null", exception.getMessage());
    }

    //Family should not be null
    @DisplayName("should throw exception when pass null to setFamily function")
    @Test
    public void shouldThrowExceptionWhenSetFamilyToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setFamily(null));
        assertEquals("Family cannot be null", exception.getMessage());
    }

    //Series should not be null
    @DisplayName("should throw exception when pass null to setSeries function")
    @Test
    public void shouldThrowExceptionWhenSetSeriesToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setSeries(null));
        assertEquals("Series cannot be null", exception.getMessage());
    }

    //RocketCode should not be null
    @DisplayName("should throw exception when pass null to setRocketCode function")
    @Test
    public void shouldThrowExceptionWhenSetRocketCodeToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setRocketCode(null));
        assertEquals("RocketCode cannot be null", exception.getMessage());
    }
}