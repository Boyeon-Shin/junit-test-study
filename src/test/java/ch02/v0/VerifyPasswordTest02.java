package ch02.v0;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("verifyPassword() 테스트")
class VerifyPasswordTest02 {

    private Rule fakeRule;

    @BeforeEach
    void setUp() {
        // 공통으로 사용할 실패하는 규칙을 세팅
        fakeRule = input -> new RuleResult(false, "fake reason");
    }

    @Nested
    @DisplayName("실패하는 규칙이 주어졌을 때")
    class WhenFailingRuleIsGiven {
        @Test
        @DisplayName("에러 메시지를 반환해야 한다")
        void shouldReturnErrors() {
            List<Rule> rules = List.of(fakeRule);
            List<String> errors = VerifyPassword.verifyPassword("any value", rules);

            assertEquals(1, errors.size());
            assertTrue(errors.get(0).contains("fake reason"));
        }
    }
}