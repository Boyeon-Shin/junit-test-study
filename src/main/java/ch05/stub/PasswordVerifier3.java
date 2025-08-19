package ch05.stub;

import java.util.List;
import java.util.function.Predicate;

public class PasswordVerifier3 {

    private final List<Predicate<String>> rules;
    private final IComplicatedLogger logger;
    private final MaintenanceWindow maintenanceWindow;

    public PasswordVerifier3(
            List<Predicate<String>> rules,
            IComplicatedLogger logger,
            MaintenanceWindow maintenanceWindow
    ) {
        this.rules = rules;
        this.logger = logger;
        this.maintenanceWindow = maintenanceWindow;
    }

    public boolean verify(String input) {
        if (maintenanceWindow.isUnderMaintenance()) {
            logger.info("Under Maintenance", "verify");
            return false;
        }

        boolean passed = rules.stream().allMatch(r -> r.test(input));

        if (passed) {
            logger.info("PASSED", "verify");
            return true;
        }
        logger.info("FAIL", "verify");
        return false;
    }
}