package rockets.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LaunchUnitTest {

    private Launch target;

    @BeforeEach
    public void setUp(){
        target = new Launch();
    }

    //payload should be satellites or spacecrafts
    @DisplayName("what is included in payload list should be equaled with satellites or spacecrafts")
    @Test
    public void shouldThrowExceptionWhenSetPayloadsAreNotSatellitesOrSpacecrafts(){
        String payload1 = "a";
        String payload2 = "satellites";
        String payload3 = "spacecrafts";
        Set<String> payload = new HashSet<String>();
        payload.add(payload1);
        payload.add(payload2);
        payload.add(payload3);
        target.setPayload(payload);
        boolean isTrue = true;
        Iterator iter = payload.iterator();
        while(iter.hasNext()){
            if(!iter.next().equals("satellites") && !iter.next().equals("spacecrafts")){
                isTrue = false;
                break;
            }
        }
        assertFalse(isTrue, "false");
    }

    @DisplayName("should throw exception when pass blank to launchDate")
    @Test
    public void whenEnterNullLaunchDate(){
        target = new Launch();
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> target.setLaunchDate(null));
        assertEquals("LaunchDate cannot be null or empty", exception.getMessage());

    }

    @DisplayName("should throw exception when pass blank to LaunchVehicle")
    @Test
    public void whenEnterNullLaunchVehicle(){
        target = new Launch();
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> target.setLaunchVehicle(null));
        assertEquals("LaunchVehicle cannot be null or empty", exception.getMessage());

    }

    @DisplayName("should throw exception when pass blank to LaunchVehicle")
    @Test
    public void whenEnterNullLaunchServiceProvider(){
        target = new Launch();
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> target.setLaunchServiceProvider(null));
        assertEquals("LaunchServiceProvider cannot be null or empty", exception.getMessage());

    }

    @DisplayName("should throw exception when pass blank to Payload")
    @Test
    public void whenEnterNullPayload(){
        target = new Launch();
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> target.setPayload(null));
        assertEquals("Payload cannot be null or empty", exception.getMessage());

    }

    @DisplayName("should throw exception when pass blank to LaunchSite")
    @Test
    public void whenEnterNullLaunchSited(){
        target = new Launch();
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> target.setLaunchSite(null));
        assertEquals("LaunchSite cannot be null or empty", exception.getMessage());

    }

    @DisplayName("should throw exception when pass blank to Orbit")
    @Test
    public void whenEnterNullOrbit(){
        target = new Launch();
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> target.setOrbit(null));
        assertEquals("Orbit cannot be null or empty", exception.getMessage());

    }

    @DisplayName("should throw exception when pass blank to Function")
    @Test
    public void whenEnterNullFunction(){
        target = new Launch();
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> target.setFunction(null));
        assertEquals("Function cannot be null or empty", exception.getMessage());

    }

    @DisplayName("should throw exception when pass blank to Price")
    @Test
    public void whenEnterNullPrice(){
        target = new Launch();
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> target.setPrice(null));
        assertEquals("Price cannot be null or empty", exception.getMessage());

    }

    @DisplayName("should throw exception when pass blank to LaunchOutcome")
    @Test
    public void whenEnterNullLaunchOutcome(){
        target = new Launch();
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> target.setLaunchOutcome(null));
        assertEquals("LaunchOutcome cannot be null or empty", exception.getMessage());

    }

}

