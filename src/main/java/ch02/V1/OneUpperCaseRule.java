package ch02.V1;

import ch02.v0.Rule;
import ch02.v0.RuleResult;

public class OneUpperCaseRule implements Rule {

    @Override
    public RuleResult check(String input) {
        boolean passed = !input.equals(input.toLowerCase());
        //        boolean passed = input.matches(".*[A-Z].*");
        String reason = "at least one upper case needed";
        return new RuleResult(passed, reason);
    }
}