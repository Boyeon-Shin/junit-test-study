package ch05.moduleMock;

import java.util.List;
import java.util.function.Predicate;

public class PasswordVerifier {

    private final ConfigurationService config;
    private final ComplicatedLogger logger;

    public PasswordVerifier(final ConfigurationService config, final ComplicatedLogger logger) {
        this.config = config;
        this.logger = logger;
    }

    private void log(String text) {
        String level = config.getLogLevel();
        if("info".equalsIgnoreCase(level)) {
            logger.info(text);
        }
        if("debug".equalsIgnoreCase(level)) {
            logger.debug(text);
        }
    }
    public boolean verifyPassword(String input, List<Predicate<String>> rules) {
        boolean passed = rules.stream().allMatch(rule -> rule.test(input));
        rules.stream().allMatch(rule -> rule.test(input));
        log(passed ? "Passed" : "Failed");
        return passed;
    }
}
