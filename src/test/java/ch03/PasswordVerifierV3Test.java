package ch03;

import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("PasswordVerifier3 - dummy function")
class PasswordVerifierV3Test {
    @Test
    void testOnlyOnWeekendInjectedFunction () {
        //함수 주입 - 항상 SUNDAY 만 반환
        DayProvider sunday = () -> DayOfWeek.SUNDAY;
        PasswordVerifierV3 passwordVerifier = new PasswordVerifierV3();

        RuntimeException e = assertThrows(RuntimeException.class, () -> {
            passwordVerifier.verifyPassword("anything", List.of(), sunday);
        });

        assertEquals(e.getMessage(), "It's the weekend!");
    }




}