package ch04.v8;

import java.util.List;
import java.util.function.Predicate;

public class PasswordVerifierV7 {
    private final List<Predicate<String>> rules;
    private final IComplicatedLogger logger;

    public PasswordVerifierV7(List<Predicate<String>> rules, IComplicatedLogger logger) {
        this.rules = rules;
        this.logger = logger;
    }

    public boolean verify(String input) {
        long failed = rules.stream()
                .map(rule -> rule.test(input))
                .filter(result -> !result)
                .count();

        if (failed == 0) {
            logger.info("PASSED");
            return true;
        } else {
            logger.warn("FAILED");
            return false;
        }
    }
}