package ch02.v0;

public interface Rule {
    RuleResult check(String input);
}

// Rule fakeRule = input -> new RuleResult(false, "fake reason");
// Rule fakeRule = new Rule() {
//    @Override
//    public RuleResult check(String input) {
//        return new RuleResult(false, "fake reason");
//    }
//}