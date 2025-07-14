package ch02.V1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ch02.v0.Rule;
import ch02.v0.RuleResult;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("v8 PasswordVerifier")
class VerifyPasswordTest8 {

    private Rule passingRule() {
        return input -> new RuleResult(true, "");
    }

    private VerifyPassword makeVerifier() {
        return new VerifyPassword();
    }

    private VerifyPassword makeVerifierWithPassingRule() {
        VerifyPassword verifier = makeVerifier();
        verifier.addRule(passingRule());
        return verifier;
    }

    private VerifyPassword makeVerifierWithFailedRule(String reason) {
        VerifyPassword verifier = makeVerifier();
        Rule failingRule = input -> new RuleResult(false, reason);
        verifier.addRule(failingRule);
        return verifier;
    }

    @Nested
    @DisplayName("with a failing rule")
    class FailingRuleTest {

        @Test
        @DisplayName("has an error message based on the rule.reason")
        void shouldContainErrorMessage() {
            VerifyPassword verifier = makeVerifierWithFailedRule("fake reason");
            List<String> errors = verifier.verifyPassword("any input");

            assertTrue(errors.get(0).contains("fake reason"));
        }

        @Test
        @DisplayName("has exactly one error")
        void shouldHaveOneError() {
            VerifyPassword verifier = makeVerifierWithFailedRule("fake reason");
            List<String> errors = verifier.verifyPassword("any input");

            assertEquals(1, errors.size());
        }
    }

    @Nested
    @DisplayName("with a passing rule")
    class PassingRuleTest {

        @Test
        @DisplayName("has no errors")
        void shouldHaveNoErrors() {
            VerifyPassword verifier = makeVerifierWithPassingRule();
            List<String> errors = verifier.verifyPassword("any input");

            assertEquals(0, errors.size());
        }
    }

    @Nested
    @DisplayName("with a failing and a passing rule")
    class MixedRuleTest {

        @Test
        @DisplayName("has one error")
        void shouldHaveOneError() {
            VerifyPassword verifier = makeVerifierWithFailedRule("fake reason");
            verifier.addRule(passingRule());
            List<String> errors = verifier.verifyPassword("any input");

            assertEquals(1, errors.size());
        }

        @Test
        @DisplayName("error text belongs to failed rule")
        void shouldContainFailedReason() {
            VerifyPassword verifier = makeVerifierWithFailedRule("fake reason");
            verifier.addRule(passingRule());
            List<String> errors = verifier.verifyPassword("any input");

            assertTrue(errors.get(0).contains("fake reason"));
        }
    }
}