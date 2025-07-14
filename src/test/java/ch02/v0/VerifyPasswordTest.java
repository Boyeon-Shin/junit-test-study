package ch02.v0;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class VerifyPasswordTest {

    @Test
    void testVerifyPassword() {
        Rule fakeRule = input -> new RuleResult(false, "fake reason");
        List<Rule> rules = List.of(fakeRule);

        List<String> errors = VerifyPassword.verifyPassword("any value", rules);

        assertEquals(1, errors.size());
        assertEquals("fake reason", errors.get(0));
    }
}