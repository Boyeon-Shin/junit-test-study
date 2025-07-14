package ch02.V1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VerifyPasswordErrorTest {
    @Test
    @DisplayName("verify, with no rules, throws exception")
    void test1() {
        VerifyPasswordError verifier = new VerifyPasswordError();

        try {
            verifier.verify("any input");
            fail("Exception was expected but not thrown");
        } catch (IllegalStateException e) {
            assertTrue(e.getMessage().contains("no rules configured"));
        }
    }

    @Test
    @DisplayName("verify, with no rules, throws exception")
    void test2() {
        VerifyPasswordError verifier = new VerifyPasswordError();

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            verifier.verify("any input");
        });

        assertTrue(exception.getMessage().contains("no rules configured"));
    }
}