package ch08.v1;

import ch08.ILogger;
import java.util.List;
import java.util.function.Function;

public class PasswordVerifier {

    private final List<Function<String, Boolean>> rules;
    private final ILogger logger;

    public PasswordVerifier(List<Function<String, Boolean>> rules, ILogger logger) {
        this.rules = rules;
        this.logger = logger;
    }

    public boolean verify(String input) {
        long failed = rules.stream()
                .map(rules -> rules.apply(input))
                .filter(result -> !result)
                .count();

        if(failed == 0) {
            logger.info("PASSED");
            return true;
        } else {
            logger.info("FAIL");
            return false;
        }
    }
}
