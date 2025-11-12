package ch09;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import ch09.v1.PasswordVerifier;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

class PasswordVerifierTest {
    @Nested
    @DisplayName("password verifier")
    class PasswordVerifierTests {

        @Test
        @DisplayName("password verifier")
        void onWeekends_throwsException() {
            PasswordVerifier verifier = new PasswordVerifier();

            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> verifier.verifyPassword("jhGGu78!", List.of(), 0)
            );

            assertEquals("It's the weekend!", exception.getMessage());
        }
    }
    @Nested
    @DisplayName("password verifier2 - dummy object")
    class PasswordVerifierTests2 {

        private static final int SUNDAY = 0;
        private static final List<String> NO_RULES = Collections.emptyList();

        @Test
        @DisplayName("password verifier2")
        void onWeekends_throwsException() {
            PasswordVerifier verifier = new PasswordVerifier();

            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> verifier.verifyPassword("anything",NO_RULES, SUNDAY)
            );


            assertEquals("It's the weekend!", exception.getMessage());
        }
    }


    @Nested
    @DisplayName("password verifier - mock setup")
    class PasswordVerifierTests3 {

        private IComplicatedLogger mockLogger;

        @BeforeEach
        void setUp() {
            mockLogger = mock(IComplicatedLogger.class);
        }


        @Test
        @DisplayName("verify, with logger & passing, calls logger with PASS")
        void verify_withLoggerAndPassing_callsLoggerWithPASS() {
            ch09.PasswordVerifier verifier = new ch09.PasswordVerifier(Collections.emptyList(), mockLogger);

            verifier.verify("anything");

            verify(mockLogger).info(contains("PASSED"), ArgumentMatchers.eq("verify"));
        }
    }

    @Nested
    @DisplayName("password verifier")
    class PasswordVerifierTests4 {

        @Test
        @DisplayName("verify, with logger & passing, calls logger with PASS")
        void verify_withLoggerAndPassing_callsLoggerWithPASS() {
            // 테스트 내부에서 mock 초기화 (beforeEach 사용 안 함)
            IComplicatedLogger mockLog = mock(IComplicatedLogger.class);

            // Arrange
            ch09.PasswordVerifier verifier = new ch09.PasswordVerifier(Collections.emptyList(), mockLog);

            // Act
            verifier.verify("anything");

            // Assert
            verify(mockLog).info(contains("PASSED"), eq("verify"));
        }
    }

    @Nested
    @DisplayName("password verifier")
    class PasswordVerifierTests5 {

        @Test
        @DisplayName("verify, with logger & passing, calls logger with PASS")
        void verify_withLoggerAndPassing_callsLoggerWithPASS() {
            // 팩토리 함수로 mock 생성
            IComplicatedLogger mockLog = makeMockLogger();

            // Arrange
            ch09.PasswordVerifier verifier = new ch09.PasswordVerifier(Collections.emptyList(), mockLog);

            // Act
            verifier.verify("anything");

            // Assert
            verify(mockLog).info(contains("PASSED"), eq("verify"));
        }
    }

    private IComplicatedLogger makeMockLogger() {
        return mock(IComplicatedLogger.class);
    }
}