package ch04.v7;

import static org.junit.jupiter.api.Assertions.*;

import ch04.v7.testDouble.FakeLogger;
import java.util.List;
import java.util.function.Predicate;
import org.junit.jupiter.api.Test;

class PasswordVerifierV7Test {

    @Test
    void verifyCallsLoggerWithPassMessage() {
        FakeLogger mockLogger = new FakeLogger();
        List<Predicate<String>> rules = List.of();
        PasswordVerifierV7 verifierV7 = new PasswordVerifierV7(rules, mockLogger);

        verifierV7.verify("anything");
        assertTrue(mockLogger.written.contains("PASSED"));
    }

}