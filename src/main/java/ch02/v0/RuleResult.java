package ch02.v0;

public class RuleResult {
    private boolean passed;
    private String reason;

    public RuleResult(boolean passed, String reason) {
        this.passed = passed;
        this.reason = reason;
    }


    public boolean isPassed() {
        return passed;
    }

    public void setPassed(final boolean passed) {
        this.passed = passed;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(final String reason) {
        this.reason = reason;
    }
}
