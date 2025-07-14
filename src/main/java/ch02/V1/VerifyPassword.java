package ch02.V1;

import ch02.v0.Rule;
import ch02.v0.RuleResult;
import java.util.ArrayList;
import java.util.List;

public class VerifyPassword {

    private final List<Rule> rules = new ArrayList<>();

    public void addRule(Rule rule) {
        rules.add(rule);
    }

    public List<String> verifyPassword(String input) {
        List<String> errors = new ArrayList<>();

        for(Rule rule : rules){
            RuleResult result = rule.check(input);
            if(!result.isPassed()){
                errors.add(result.getReason());
            }
        }
        return errors;
    }
}
