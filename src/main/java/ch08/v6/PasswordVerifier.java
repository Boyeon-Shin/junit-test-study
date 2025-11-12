package ch08.v6;

import ch08.IResult;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PasswordVerifier {
    private final List<Predicate<String>> rules;
    private String msg =  "";

    public PasswordVerifier(List<Predicate<String>> rules) {
        this.rules = rules;
    }

    public String getMsg() {
        return msg;
    }

    public List<IResult> verify(List<String> input) {
        List<IResult> allResults = input.stream()
                .map(this::checkSingleInput)
                .collect(Collectors.toList());
        setDescription(allResults);
        return allResults;
    }

    private void setDescription(List<IResult> results) {
        long failedCount = results.stream()
                .filter(rs -> !rs.result())
                .count();
        this.msg = "you have " + failedCount + " failed rules.";
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
}
