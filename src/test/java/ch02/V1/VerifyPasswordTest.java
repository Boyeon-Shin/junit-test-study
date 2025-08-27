//package ch02.V1;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import ch02.v0.Rule;
//import ch02.v0.RuleResult;
//import java.util.List;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//
//class VerifyPasswordTest {
//
//    @Nested
//    @DisplayName("with a failing rule")
//    class FailingRuleTest {
//        private VerifyPassword verifier;
//
//        @BeforeEach
//        void setUp() {
//            verifier = new VerifyPassword();
//        }
//
//        @Test
//        @DisplayName("has an error message based on the rule.reason")
//        void shouldReturnErrorMessageBasedOnRuleReason() {
//            Rule fakeRule = input -> new RuleResult(false, "fake reason");
//            verifier.addRule(fakeRule);
//
//            List<String> errors = verifier.verifyPassword("any value");
//
//            assertEquals(1, errors.size());
//            assertTrue(errors.get(0).contains("fake reason"));
//        }
//    }
//
//    @Nested
//    @DisplayName("with a failing rule2")
//    class FailingRuleTest2 {
//        private VerifyPassword verifier;
//
//        @BeforeEach
//        void setUp() {
//            verifier = new VerifyPassword();
//            Rule fakeRule = input -> new RuleResult(false, "fake reason");
//            verifier.addRule(fakeRule);
//        }
//
//        @Test
//        void hasExactlyOneError() {
//            List<String> errors = verifier.verifyPassword("any value");
//            assertEquals(1, errors.size());
//        }
//
//        @Test
//        void hasCorrectErrorMessage() {
//            List<String> errors = verifier.verifyPassword("any value");
//            assertTrue(errors.get(0).contains("fake reason"));
//        }
//    }
//
//
//}