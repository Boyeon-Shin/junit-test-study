package ch04.v9;

import static org.junit.jupiter.api.Assertions.*;

import ch04.v10.TestableLogger;
import java.util.List;
import org.junit.jupiter.api.Test;

class PasswordVerifierTest {

    @Test
    void verifyWithLoggerCallsLogger() {
        // 부분 모의 객체
        final String[] logged = {""};

        RealLogger testableLog = new RealLogger() {
            @Override
            public void info(String text) {
                logged[0] = text;
            }
        };

        PasswordVerifier verifier = new PasswordVerifier(List.of(), testableLog);
        verifier.verify("any input");

        assertEquals("PASSED", logged[0]);
    }


    @Test
    void verifyWithLoggerCallsLogger2() {
        // 1. 부분 모의 객체 생성
        TestableLogger mockLog = new TestableLogger();

        // 2. 테스트 대상 객체 생성
        PasswordVerifier verifier = new PasswordVerifier(List.of(), mockLog);

        // 3. 검증
        verifier.verify("any input");

        // 4. info가 호출되었는지 확인
        assertTrue(mockLog.logged.contains("PASSED"));
    }

}