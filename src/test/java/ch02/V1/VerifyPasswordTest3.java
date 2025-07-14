package ch02.V1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import ch02.v0.Rule;
import ch02.v0.RuleResult;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("v9 PasswordVerifier")
class VerifyPasswordTest3 {

    // 팩토리 함수들
    private VerifyPassword makeVerifier() {
        return new VerifyPassword();
    }

    private Rule passingRule() {
        return input -> new RuleResult(true, "");
    }

    private VerifyPassword makeVerifierWithPassingRule() {
        VerifyPassword verifier = makeVerifier();
        verifier.addRule(passingRule());
        return verifier;
    }

    private VerifyPassword makeVerifierWithFailedRule(String reason) {
        VerifyPassword verifier = makeVerifier();
        Rule fakeRule = input -> new RuleResult(false, reason);
        verifier.addRule(fakeRule);
        return verifier;
    }

    // 테스트 1
    @Test
    @DisplayName("pass verifier, with failed rule, has an error message based on the rule.reason")
    void failedRuleShouldReturnErrorMessage() {
        VerifyPassword verifier = makeVerifierWithFailedRule("fake reason");
        List<String> errors = verifier.verifyPassword("any input");
        assertTrue(errors.get(0).contains("fake reason"));
    }

    // 테스트 2
    @Test
    @DisplayName("pass verifier, with failed rule, has exactly one error")
    void failedRuleShouldReturnExactlyOneError() {
        VerifyPassword verifier = makeVerifierWithFailedRule("fake reason");
        List<String> errors = verifier.verifyPassword("any input");
        assertEquals(1, errors.size());
    }

    // 테스트 3
    @Test
    @DisplayName("pass verifier, with passing rule, has no errors")
    void passingRuleShouldReturnNoErrors() {
        VerifyPassword verifier = makeVerifierWithPassingRule();
        List<String> errors = verifier.verifyPassword("any input");
        assertEquals(0, errors.size());
    }

    // 테스트 4
    @Test
    @DisplayName("pass verifier, with passing and failing rule, has one error")
    void passingAndFailingRuleShouldReturnOneError() {
        VerifyPassword verifier = makeVerifierWithFailedRule("fake reason");
        verifier.addRule(passingRule());
        List<String> errors = verifier.verifyPassword("any input");
        assertEquals(1, errors.size());
    }

    // 테스트 5
    @Test
    @DisplayName("pass verifier, with passing and failing rule, error text belongs to failed rule")
    void errorMessageShouldMatchFailedRule() {
        VerifyPassword verifier = makeVerifierWithFailedRule("fake reason");
        verifier.addRule(passingRule());
        List<String> errors = verifier.verifyPassword("any input");
        assertTrue(errors.get(0).contains("fake reason"));
    }

    // 테스트 6
    @Test
    @DisplayName("verify, with no rules, throws exception")
    void noRulesShouldThrowException_TryCatch() {
        VerifyPassword verifier = makeVerifier();
        try {
            verifier.verifyPassword("any input");
            fail("Exception was expected but not thrown");
        } catch (RuntimeException e) {
            assertTrue(e.getMessage().contains("no rules configured"));
        }
    }

    // 테스트 7
    @Test
    @DisplayName("verify, with no rules, throws exception (assertThrows)")
    void noRulesShouldThrowException_AssertThrows() {
        VerifyPassword verifier = makeVerifier();
        RuntimeException e = assertThrows(RuntimeException.class,
            () -> verifier.verifyPassword("any input"));
        assertTrue(e.getMessage().contains("no rules configured"));
    }
}