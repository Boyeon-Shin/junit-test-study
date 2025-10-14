package ch07;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("trigger")
class TriggerTest {
    @Test
    void works() {
        AtomicReference<String> callbackMessage = new AtomicReference<>();

        // when
        int result = Trigger.trigger(1, 2, callbackMessage::set);

        // then
        assertEquals(3, result);
        assertEquals("I'm triggered", callbackMessage.get());
    }

    @Test
    void trigger_a_given_callback() {
        AtomicReference<String> callbackMessage = new AtomicReference<>();

        // when
        int result = Trigger.trigger(1, 2, callbackMessage::set);

        // then
        assertEquals("I'm triggered", callbackMessage.get());
    }

    @Test
    void sums_up_given_values() {
        AtomicReference<String> callbackMessage = new AtomicReference<>();

        int result = Trigger.trigger(1, 2, callbackMessage::set);

        assertThat(result).isEqualTo(3);
    }

}