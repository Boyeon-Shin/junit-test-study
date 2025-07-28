package ch04;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class PasswordVerifierV2Test {
    @Test
    void callsLoggerWithPassedWhenAllRulesPass() {
        // 클로저처럼 값을 기록하기 위한 배열
        final String[] written = {""};

        // 직접 작성한 mock 객체
        Logger mockLogger = new Logger() {
            @Override
            public void info(String text) {
                written[0] = text;
            }
        };

        PasswordVerifierV2 verifier = new PasswordVerifierV2();

        // 규칙이 모두 통과하는 상황
        boolean result = verifier.verifyPassword("anything", List.of(), mockLogger);
        assertTrue(result);

        // 로그 메시지가 PASSED를 포함하는지 확인
        assertTrue(written[0].contains("PASSED"));
    }
}