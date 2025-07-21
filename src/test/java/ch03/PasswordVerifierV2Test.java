package ch03;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.DayOfWeek;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("verifierV2-dummy object")
class PasswordVerifierV2Test {

    @Test
    void testOnlyOnWeekend() {
        PasswordVerifierV2 passwordVerifier = new PasswordVerifierV2();
        DayOfWeek currentDay = DayOfWeek.SATURDAY;

        RuntimeException e = assertThrows(RuntimeException.class, () -> {
            passwordVerifier.verifyPassword("anything", List.of(), currentDay);
        });

        assertEquals("It's the weekend!", e.getMessage());
    }
}