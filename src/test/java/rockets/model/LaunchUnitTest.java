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

}

