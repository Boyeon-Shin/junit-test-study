package ch04.v8;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import org.junit.jupiter.api.Test;

class PasswordVerifierV7Test {

    @Test
    void verifyPassingCallsLoggerWithPASS() {
        FakeComplicatedLogger logger = new FakeComplicatedLogger();

        PasswordVerifierV7 verifier = new PasswordVerifierV7(
                List.of(), // 규칙 없음 -> 무조건 통과
                logger
        );

        verifier.verify("anything");

        assertTrue(logger.infoWritten.contains("PASS"));
    }


    @Test
    void verifyFailingCallsLoggerWithFAIL() {
        FakeComplicatedLogger logger = new FakeComplicatedLogger();

        Predicate<String> alwaysFailRule = input -> false;

        PasswordVerifierV7 verifier = new PasswordVerifierV7(
                List.of(alwaysFailRule),
                logger
        );

        verifier.verify("abc");

        assertTrue(logger.warnWritten.contains("FAILED"));
    }
}
