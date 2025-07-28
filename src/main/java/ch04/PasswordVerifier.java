package ch04;

import java.util.List;
import java.util.function.Predicate;

public class PasswordVerifier {

    private static final ComplicatedLogger logger = new ComplicatedLogger();

    public boolean verifyPassword(String input, List<Predicate<String>> rules) {
        long failCount = rules.stream()
                .filter(rule -> !rule.test(input))  // 오타 수정: rules.test → rule.test
                .count();

        if (failCount == 0) {
            logger.info("PASSED"); // 종료점 1
            return true;
        }

        logger.info("FAIL"); // 종료점 2
        return false;
    }
}