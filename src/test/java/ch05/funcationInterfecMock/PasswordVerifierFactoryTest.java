package ch05.funcationInterfecMock;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.function.Predicate;
import org.junit.jupiter.api.Test;

class PasswordVerifierFactoryTest {
    @Test
    void givenLoggerAndPassingScenarioWithoutMockito() {
        AtomicReference<String> logged = new AtomicReference<>("");

        ComplicatedLogger mockLog = text -> logged.set(text);

        Function<String, Boolean> verify =
                PasswordVerifierFactory.makeVerifier(List.<Predicate<String>>of(), mockLog);

        Boolean result = verify.apply("any input");

        assertTrue(result);
        assertTrue(logged.get().matches(".*Passed.*"));
    }
}