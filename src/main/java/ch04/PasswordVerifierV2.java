package ch04;

import java.util.List;
import java.util.function.Predicate;

public class PasswordVerifierV2 {

    public boolean verifyPassword(String input, List<Predicate<String>> rules, Logger logger) {
        long failedCount = rules.stream()
                .map(rule -> rule.test(input))
                .filter(result -> result == false)
                .count();

        if (failedCount == 0) {
            logger.info("PASSED");
            return true;
        }

        logger.info("FAIL");
        return false;
    }
}