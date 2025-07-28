package ch04.v3;

import java.util.List;
import java.util.function.Predicate;

public class PasswordVerifierV3 {
    private final ConfigurationService config;
    private final Logger logger;

    public PasswordVerifierV3(ConfigurationService config, Logger logger) {
        this.config = config;
        this.logger = logger;
    }

    private void log(String message) {
        String level = config.getLogLevel();
        if ("info".equals(level)) {
            logger.info(message);
        } else if ("debug".equals(level)) {
            logger.debug(message);
        }
    }

    public boolean verifyPassword(String input, List<Predicate<String>> rules) {
        long failed = rules.stream()
                .filter(rule -> !rule.test(input))
                .count();

        if (failed == 0) {
            log("PASSED");
            return true;
        }

        log("FAIL");
        return false;
    }
}
