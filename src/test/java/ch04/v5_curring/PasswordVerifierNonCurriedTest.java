package ch04.v5_curring;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import org.junit.jupiter.api.Test;

class PasswordVerifierNonCurriedTest {

    @Test
    void shouldLogPassedWhenAllRulesPassed() {
        //로그 메시지를 저장할 배열
        final String[] logged = {""};

        //빈 규칙 목록 -> 무조건 통과
        List<Predicate<String>> rules = List.of();

        Consumer<String> mockLogger = msg -> logged[0] = msg;

        Function<String, Boolean> passVerifier = PasswordVerifierNonCurried.makeVerifier(rules, mockLogger);

        boolean result = passVerifier.apply("any input");
        assertTrue(result);
        assertEquals("PASSED", logged[0]);
    }
}