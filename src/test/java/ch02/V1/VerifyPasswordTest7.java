package ch02.V1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ch02.v0.Rule;
import ch02.v0.RuleResult;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("v7 PasswordVerifier")
class VerifyPasswordTest7 {

    private VerifyPassword verifier;

    @BeforeEach
    void init() {
        verifier = new VerifyPassword();
    }

    Rule makeFailingRule(String reason) {
        return input -> new RuleResult(false, reason);
    }

    Rule makePassingRule() {
        return input -> new RuleResult(true, "");
    }

    @Nested
    @DisplayName("with a failing rule")
    class FailingRuleTest {
        private List<String> errors;

        @BeforeEach
        void setUp() {
            verifier.addRule(makeFailingRule("fake reason"));
            errors = verifier.verifyPassword("any value");
        }

        @Test
        @DisplayName("has an error message based on the rule.reason")
        void shouldContainErrorMessage() {
            assertTrue(errors.get(0).contains("fake reason"));
        }

        @Test
        @DisplayName("has exactly one error")
        void shouldHaveOneError() {
            assertEquals(1, errors.size());
        }
    }

    @Nested
    @DisplayName("with a passing rule")
    class PassingRuleTest {
        private List<String> errors;

        @BeforeEach
        void setUp() {
            verifier.addRule(makePassingRule());
            errors = verifier.verifyPassword("any value");
        }

        @Test
        @DisplayName("has no errors")
        void shouldHaveNoErrors() {
            assertEquals(0, errors.size());
        }
    }

    @Nested
    @DisplayName("with a failing and a passing rule")
    class MixedRulesTest {
        private List<String> errors;

        @BeforeEach
        void setUp() {
            verifier.addRule(makePassingRule());
            verifier.addRule(makeFailingRule("fake reason"));
            errors = verifier.verifyPassword("any value");
        }

        @Test
        @DisplayName("has one error")
        void shouldHaveOneError() {
            assertEquals(1, errors.size());
        }

        @Test
        @DisplayName("error text belongs to failed rule")
        void shouldContainFailingReason() {
            assertTrue(errors.get(0).contains("fake reason"));
        }
    }
}