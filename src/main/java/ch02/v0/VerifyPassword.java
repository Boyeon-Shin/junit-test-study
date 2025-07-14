package ch02.v0;

import java.util.ArrayList;
import java.util.List;

public class VerifyPassword {
    public static List<String> verifyPassword(String input, List<Rule> rules) {
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
