package ch04.v5_curring;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import org.junit.jupiter.api.Test;

class PasswordVerifierCurriedTest {

    @Test
    void returnTrueAndLogsPassedWithWhenAllRulesPass() {
        final String[] logged = {""};

        //Stub logger
        PasswordVerifierCurried.Logger logger = (msg) -> logged[0] += msg;

        //No rules = always pass
        List<Predicate<String>> rules = List.of();

        //커링처럼 rules + logger 를 주입
        PasswordVerifierCurried.PasswordChecker verifier = PasswordVerifierCurried.createVerifier(rules, logger);

        boolean result = verifier.verify("anything");
        assertTrue(result);
        assertEquals("PASSED", logged[0]);
    }


    @Test
    void returnsFalseAndLogsFailWhenAnyRuleFails() {
        final String[] logged = {""};

        PasswordVerifierCurried.Logger logger = (msg) -> logged[0] = msg;

        List<Predicate<String>> rules = List.of(
                pw -> pw.length() >= 8,    // 최소 8자
                pw -> pw.contains("123")  // 123 포함
        );

        PasswordVerifierCurried.PasswordChecker verifier =
                PasswordVerifierCurried.createVerifier(rules, logger);

        boolean result = verifier.verify("short");

        assertFalse(result);
        assertEquals("FAILED", logged[0]);
    }

}