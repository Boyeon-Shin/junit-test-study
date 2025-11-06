package ch08.v5;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PasswordVerifier {
    private final List<Predicate<String>> rules;

    public PasswordVerifier(List<Predicate<String>> rules) {
        this.rules = rules;
    }

    public List<IResult> verify(List<String> inputs) {
        return inputs.stream()
                .map(this::checkSingleInput)
                .collect(Collectors.toList());
    }

    public boolean findResultFor(List<IResult> results, String input) {
        return results.stream()
                .filter(res -> res.input().equals(input))
                .findFirst()
                .map(IResult::result)
                .orElse(false);
    }

    private IResult checkSingleInput(String input) {
        List<Boolean> failed = findFailedRules(input);
        boolean isPassed = failed.isEmpty();
        return new IResult(isPassed, input);
    }

    protected List<Boolean> findFailedRules(String input) {
        return rules.stream()
                .map(rule -> rule.test(input))
                .filter(result -> !result)
                .collect(Collectors.toList());
    }

    // Java 16 이상에서 record를 사용 가능
    public record IResult(boolean result, String input) {}

}
