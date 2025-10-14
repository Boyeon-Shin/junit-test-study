package ch07;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("makeGreeting")
class TrustTest {

    @Test
    public void return_correct_greeting_for_name_v1 () {
        String name = "boyeon";
        String result = Trust.makeGreeting(name);

        assertEquals("Hello " + name, result);
    }

    @Test
    public void return_correct_greeting_for_name_v2 () {
        String name = "boyeon";
        String result = Trust.makeGreeting(name);

        assertEquals("Hello boyeon", result);
    }




}