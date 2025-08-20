package ch05.logger;

import static org.junit.jupiter.api.Assertions.*;

import ch05.moduleMock.PasswordVerifier;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("복잡한 인터페이스를 수작업으로 테스트하기")
class IComplicatedLoggerTest {

    static class FakeLogger implements IComplicatedLogger {
        String infoText = "";
        String infoMethod = "";

        @Override
        public void info(String text, String method) {
            this.infoText = text;
            this.infoMethod = method;
        }

        @Override
        public void debug(String text, String method) {
        }

        @Override
        public void warn(String text, String method) {
        }

        @Override
        public void error(String text, String method) {
        }
    }


    @Test
    void verify_withManualLoggerAndPassing_callsLoggerWithPASS() {
        // Given
        FakeLogger fakeLogger = new FakeLogger();
//        PasswordVerifier verifier = new PasswordVerifier(fakeLogger, List.of());
//
//        // When
//        verifier.verifyPassword("any-password");

        // Then
        assertTrue(fakeLogger.infoText.contains("PASSED"));
    }


}