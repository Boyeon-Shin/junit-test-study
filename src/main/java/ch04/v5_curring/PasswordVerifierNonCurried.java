package ch04.v5_curring;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class PasswordVerifierNonCurried {

    public static Function<String, Boolean> makeVerifier(
            List<Predicate<String>> rules, Consumer<String> logger
    ) {
        return (input) -> {
            long failCount = rules.stream()
                    .map(rule -> rule.test(input))
                    .filter(result -> !result)
                    .count();

            if (failCount == 0) {
                logger.accept("PASSED");
                return true;
            } else {
                logger.accept("FAILED");
                return false;
            }
        };
    }
}
