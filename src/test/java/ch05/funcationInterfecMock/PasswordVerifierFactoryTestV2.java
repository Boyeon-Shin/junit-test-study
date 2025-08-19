package ch05.funcationInterfecMock;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.function.Function;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class PasswordVerifierFactoryTestV2 {

    @Mock
    ComplicatedLogger logger;

    @Test
    void giveLoggerAndPassingScenario() {
        Function<String, Boolean> verify =
                PasswordVerifierFactory.makeVerifier(List.of(), logger);

        Boolean result = verify.apply("any input");

        // then
        assertTrue(result);
        verify(logger).info(contains("Passed"));
    }
}