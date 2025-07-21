package ch03;

import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PasswordVerifierConstructorTest {

    @Test
    void testThrowsExceptionOnWeekend() {
        TimeProvider sunday = () -> DayOfWeek.SUNDAY;
        PasswordVerifierConstructor verifier = new PasswordVerifierConstructor(List.of(), sunday);

        RuntimeException e = assertThrows(RuntimeException.class, () ->
                verifier.verifyPassword("anything")
        );
        assertEquals("It's the weekend!", e.getMessage());
    }

    @Nested
    @DisplayName("helper factory add")
    class helperFactoryAdd {
        //헬퍼 팩토리 함수
        PasswordVerifierConstructor makeVerifier(List<String> rules, TimeProvider timeProvider) {
            return new PasswordVerifierConstructor(rules, timeProvider);
        }

        @Test
        void WeekendThrowsException() {
            TimeProvider sunday = () -> DayOfWeek.SUNDAY;

            PasswordVerifierConstructor verifier = makeVerifier(List.of(), sunday);

            RuntimeException ex = assertThrows(RuntimeException.class, () -> {
                verifier.verifyPassword("anything");
            });

            assertEquals("It's the weekend!", ex.getMessage());
        }

        @Test
        void weekdayNoRulesReturnsEmptyErrorList() {
            TimeProvider alwaysMonday = () -> DayOfWeek.MONDAY;

            PasswordVerifierConstructor verifier = makeVerifier(List.of(), alwaysMonday);
            List<String> result = verifier.verifyPassword("anything");

            assertEquals(0, result.size());
        }


        @Nested
        @DisplayName("생성자 매개변수에 객체 주입")
        class helperFactoryModify {
            //PasswordVerifierConstructor 에 RealTimeProvider 를 의존성으로 주입하는 역할
            PasswordVerifierConstructor makeVerifier(List<String> rules) {
                return new PasswordVerifierConstructor(rules, new RealTimeProvider());
            }
        }

        @Nested
        @DisplayName("주말 테스트")
        class WeekendBehavior {

            @Test
            @DisplayName("주말이면 예외 발생")
            void shouldThrowExceptionOnWeekend() {
                PasswordVerifierConstructor verifier = new PasswordVerifierConstructor(
                        List.of(),
                        new FakeTimeProvider(DayOfWeek.SUNDAY)  // 또는 SATURDAY
                );

                RuntimeException e = assertThrows(RuntimeException.class, () -> {
                    verifier.verifyPassword("anything");
                });

                assertEquals("It's the weekend!", e.getMessage());
            }
        }
    }
}
