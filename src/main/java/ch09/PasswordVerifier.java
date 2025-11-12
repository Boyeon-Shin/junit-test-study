package ch09;
import java.util.List;

public class PasswordVerifier {

    private final List<Rule> rules;
    private final IComplicatedLogger logger;

    public PasswordVerifier(List<Rule> rules, IComplicatedLogger logger) {
        this.rules = rules;
        this.logger = logger;
    }

    public boolean verify(String input) {
        long failedCount = rules.stream()
                .map(rule -> rule.test(input))
                .filter(result -> !result)
                .count();

        if (failedCount == 0) {
            logger.info("PASSED", "verify");
            return true;
        } else {
            logger.info("FAIL", "verify");
            return false;
        }
    }

}
