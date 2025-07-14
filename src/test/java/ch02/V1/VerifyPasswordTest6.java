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

@DisplayName("v6 PasswordVerifier")
class VerifyPasswordTest6 {

    private VerifyPassword verifier;

    @BeforeEach
    void setUp() {
        verifier = new VerifyPassword();
    }

    @Nested
    @DisplayName("with a failing rule")
    class FailingRuleTest {
        private Rule fakeRule;
        private List<String> errors;

        @BeforeEach
        void setUp() {
            fakeRule = input -> new RuleResult(false, "fake reason");
            verifier.addRule(fakeRule);
            errors = verifier.verifyPassword("any value");
        }

        @Test
        @DisplayName("has an error message based on the rule.reason")
        void shouldContainErrorMessage() {
            assertTrue(errors.get(0).contains("fake reason"));
        }

        @Test
        @DisplayName("has exactly one error")
        void shouldHaveExactlyOneError() {
            assertEquals(1, errors.size());
        }
    }

    @Nested
    @DisplayName("with a passing rule")
    class PassingRuleTest {
        private Rule fakeRule;
        private List<String> errors;

        @BeforeEach
        void setUp() {
            fakeRule = input -> new RuleResult(true, "");
            verifier.addRule(fakeRule);
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
        private Rule fakeRulePass, fakeRuleFail;
        private List<String> errors;

        @BeforeEach
        void setUp() {
            fakeRulePass = input -> new RuleResult(true, "fake success");
            fakeRuleFail = input -> new RuleResult(false, "fake reason");
            verifier.addRule(fakeRulePass);
            verifier.addRule(fakeRuleFail);
            errors = verifier.verifyPassword("any value");
        }

        @Test
        @DisplayName("has one error")
        void shouldHaveOneError() {
            assertEquals(1, errors.size());
        }

        @Test
        @DisplayName("error text belongs to failed rule")
        void shouldContainFailedReason() {
            assertTrue(errors.get(0).contains("fake reason"));
        }
    }
}