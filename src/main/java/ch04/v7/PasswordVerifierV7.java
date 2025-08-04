package ch04.v7;

import java.util.List;
import java.util.function.Predicate;

public class PasswordVerifierV7 {
    private final List<Predicate<String>> rules;
    private final ILogger logger;

    public PasswordVerifierV7(List<Predicate<String>> rules, ILogger logger) {
        this.rules = rules;
        this.logger = logger;
    }

    public boolean verify(String input) {
        long failCount = rules.stream()
                .map(rule -> rule.test(input))
                .filter(result -> !result)
                .count();

        if (failCount == 0) {
            logger.info("PASSED");
            return true;
        } else {
            logger.info("FAILED");
            return false;
        }
    }
}
