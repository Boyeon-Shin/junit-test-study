package ch03;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PasswordVerifierTest {

    @Nested
    class verifier {
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        PasswordVerifier passwordVerifier;

        @BeforeEach
        void setUp() {
            passwordVerifier = new PasswordVerifier();
        }

        // 이 테스트는 항상 실행되지만, 아무것도 수행하지 않을 수 있음
        @Test
        void onWeekendException() {
            if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
                RuntimeException e = assertThrows(RuntimeException.class, () -> {
                    passwordVerifier.verifyPassword("anything", List.of());
                });
                assertTrue(e.getMessage().contains("It's the weekend"));
            }
        }

        // 이 테스트는 주말에만
        @Test
        void testOnlyOnWeekend() {
            if (!(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY)) {
                return;
            }

            RuntimeException e = assertThrows(RuntimeException.class, () -> {
                passwordVerifier.verifyPassword("anything", List.of());
            });
            assertEquals("It's the weekend!", e.getMessage());
        }

        @Test
        void testOnlyOnWeekendAssertTrue() {
            assumeTrue(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY);

            RuntimeException e = assertThrows(RuntimeException.class, () -> {
                passwordVerifier.verifyPassword("anything", List.of());
            });
            assertEquals("It's the weekend!", e.getMessage());
        }
    }


}