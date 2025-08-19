package ch05.funcationInterfecMock;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class PasswordVerifierFactory {

    public PasswordVerifierFactory() {
    }

    public static Function<String, Boolean> makeVerifier(
            List<Predicate<String>> rules,
            ComplicatedLogger logger
    ) {
        return (String input) -> {
            boolean passed = rules.stream().allMatch(r -> r.test(input));
            logger.info(passed ? "Passed" : "Failed");
            return passed;
        };
    }
}

