package ch03.supplier;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class PasswordVerifier {

    private static final Supplier<DayOfWeek> originalDayOfWeekSupplier = () -> LocalDate.now().getDayOfWeek();
    private static Supplier<DayOfWeek> dayOfWeekSupplier = originalDayOfWeekSupplier;


    public static Runnable inject(Supplier<DayOfWeek> timeProvider) {
        dayOfWeekSupplier = timeProvider;
        return () -> dayOfWeekSupplier = originalDayOfWeekSupplier;
    }


    public static List<String> verifyPassword(String input, List<Rule> rules) {
        DayOfWeek today = dayOfWeekSupplier.get();
        if (today == DayOfWeek.SATURDAY || today == DayOfWeek.SUNDAY) {
            throw new RuntimeException("It's the weekend!");
        }

        List<String> errors = new ArrayList<>();
        for (Rule rule : rules) {
            VerifyResult result = rule.apply(input);
            if (!result.passed()) {
                errors.add(result.reason());
            }
        }

        return errors;
    }
}
