package ch04.v6;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.junit.jupiter.api.Test;

class PasswordVerifierV6Test {

    @Test
    void shouldCallLoggerPassedWhenAllRulesPassed() {
        final String[] logged = {""};

        List<Predicate<String>> rules = List.of();

        Consumer<String> mockLogger = msg -> logged[0] = msg;

        PasswordVerifierV6 passwordVerifier = new PasswordVerifierV6(rules, mockLogger);

        boolean result = passwordVerifier.verify("any input");

        assertTrue(result);
        assertEquals("PASSED", logged[0]);
    }
}