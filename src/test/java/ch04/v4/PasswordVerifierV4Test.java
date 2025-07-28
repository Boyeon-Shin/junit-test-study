package ch04.v4;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class PasswordVerifierV4Test {
    private final String[] logged = {""};
    private final Logger mockLogger = new Logger() {
        @Override
        public void info(String message) {
            logged[0] = message;
        }
    };

    private final PasswordVerifierV4 verifier = new PasswordVerifierV4(mockLogger);

    @AfterEach
    void resetLogger() {
        // 원래 logger로 되돌리는 동작 (테스트 격리 유지)
        verifier.resetLogger(mockLogger);
    }

    @Test
    void callsLoggerWithPassWhenNoRulesFail() {
        // 가짜 로거 주입
        verifier.injectLogger(mockLogger);

        // 규칙 모두 통과하는 경우
        verifier.verifyPassword("anything", List.of());

        assertTrue(logged[0].contains("PASSED"));
    }
}