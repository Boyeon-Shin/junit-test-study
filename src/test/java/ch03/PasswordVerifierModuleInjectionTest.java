package ch03;

import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import java.util.List;
import org.junit.jupiter.api.Test;

class PasswordVerifierModuleInjectionTest {

    class InjectHelper {
        public static Runnable injectDate(PasswordVerifierModuleInjection verifier, DayOfWeek fakeDay) {
            return verifier.inject(() -> fakeDay);
        }
    }

    @Test
    void testOnlyOnWeekend() {
        PasswordVerifierModuleInjection verifier = new PasswordVerifierModuleInjection();
        Runnable reset = InjectHelper.injectDate(verifier, DayOfWeek.SATURDAY);

        RuntimeException e = assertThrows(RuntimeException.class, () ->
                verifier.verifyPassword("abc", List.of())
        );
        assertEquals("It's the weekend!", e.getMessage());

        reset.run();
    }
}