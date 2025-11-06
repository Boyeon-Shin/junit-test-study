package ch08.v2;

import ch08.IComplicatedLogger;
import java.util.List;
import java.util.function.Function;

public class PasswordVerifier {

    private final List<Function<String, Boolean>> rules;
    private final IComplicatedLogger logger;

    public PasswordVerifier(List<Function<String, Boolean>> rules, IComplicatedLogger logger) {
        this.rules = rules;
        this.logger = logger;
    }

    public boolean verify(String input) {
        long failed = rules.stream()
                .map(rule -> rule.apply(input))
                .filter(result -> result == false)
                .count();

        if(failed == 0) {
            logger.info("PASSWORD");
            return true;
        } else {
            logger.info("FAILED");
            return false;
        }
    }
}
