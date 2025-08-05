package ch04.v9;

import java.util.List;
import java.util.function.Predicate;

class PasswordVerifier {
    private final List<Predicate<String>> rules;
    private final RealLogger logger;

    public PasswordVerifier(List<Predicate<String>> rules, RealLogger logger) {
        this.rules = rules;
        this.logger = logger;
    }

    public boolean verify(String input) {
        boolean passed = rules.stream().allMatch(rule -> rule.test(input));
        if (passed) {
            logger.info("PASSED");
            return true;
        }else {
            logger.info("FAILED");
            return false;
        }
    }
}