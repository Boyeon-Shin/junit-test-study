package ch04.v6;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class PasswordVerifierV6 {
    private final List<Predicate<String>> rules;
    private final Consumer<String> logger;

    public PasswordVerifierV6(List<Predicate<String>> rules, Consumer<String> logger) {
        this.rules = rules;
        this.logger = logger;
    }

    public boolean verify(String input){
        long failCount = rules.stream()
                .map(rule -> rule.test(input))
                .filter(result -> !result)
                .count();

        if (failCount == 0){
            logger.accept("PASSED");
            return true;
        }  else {
            logger.accept("FAILED");
            return false;
        }
    }
}
