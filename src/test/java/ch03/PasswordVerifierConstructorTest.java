package ch03;

import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import java.util.List;
import org.junit.jupiter.api.Test;

class PasswordVerifierConstructorTest {

    @Test
    void testThrowsExceptionOnWeekend() {
        // Arrange: 항상 일요일을 반환하는 TimeProvider 주입
        TimeProvider sunday = () -> DayOfWeek.SUNDAY;
        PasswordVerifierConstructor verifier = new PasswordVerifierConstructor(List.of(), sunday);

        RuntimeException e = assertThrows(RuntimeException.class, () ->
                verifier.verifyPassword("anything")
        );
        assertEquals("It's the weekend!", e.getMessage());
    }
}