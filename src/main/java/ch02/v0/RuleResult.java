package ch02.v0;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RuleResult {
    private boolean passed;
    private String reason;

    public RuleResult(boolean passed, String reason) {
        this.passed = passed;
        this.reason = reason;
    }
}
