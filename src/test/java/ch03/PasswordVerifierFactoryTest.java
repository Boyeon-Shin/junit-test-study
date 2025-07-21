package ch03;

import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.function.Function;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("PasswordVerifier - 고차 함수")
class PasswordVerifierFactoryTest {
        @Test
        void testOnlyOnWeekend() {
            DayProvider sunday = () -> DayOfWeek.SUNDAY;
            Function<String, List<String>> verifier = PasswordVerifierFactory.makeVerifier(List.of(), sunday);

            RuntimeException e = assertThrows(RuntimeException.class, () -> {
                verifier.apply("anything");
            });

            assertEquals("It's the weekend!", e.getMessage());
        }

}