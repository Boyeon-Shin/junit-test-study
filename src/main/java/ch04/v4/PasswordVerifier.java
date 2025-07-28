package ch04.v4;

import java.util.List;
import java.util.function.Predicate;

class PasswordVerifierV4 {

    private Logger logger;

    // 기본 생성자 (실제 구현체 주입 가능)
    public PasswordVerifierV4(Logger logger) {
        this.logger = logger;
    }

    // 의존성 교체 메서드 (inject)
    public void injectLogger(Logger fakeLogger) {
        this.logger = fakeLogger;
    }

    // 의존성 리셋 메서드 (reset)
    public void resetLogger(Logger originalLogger) {
        this.logger = originalLogger;
    }

    // 핵심 로직
    public boolean verifyPassword(String input, List<Predicate<String>> rules) {
        long failedCount = rules.stream()
                .filter(rule -> !rule.test(input))
                .count();

        if (failedCount == 0) {
            logger.info("PASSED");
            return true;
        }

        logger.info("FAIL");
        return false;
    }
}