package ch08.v4;

import ch08.IComplicatedLogger;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PasswordVerifier {

    private final List<Function<String, Boolean>> rules;
    private final IComplicatedLogger logger;

    public PasswordVerifier(List<Function<String, Boolean>> rules, IComplicatedLogger logger) {
        this.rules = rules;
        this.logger = logger;
    }

    public boolean verify(String input) {
        List<Boolean> failed = findFailedRules(input);

        if (failed.isEmpty()) {
            logger.info("PASSED");
            return true;
        }

        logger.info("FAIL");
        return false;
    }

    protected List<Boolean> findFailedRules(String input) {
        return rules.stream()
                .map(rule -> rule.apply(input))
                .filter(result -> !result)
                .collect(Collectors.toList());
    }
}