package ch03;

import java.time.DayOfWeek;
import java.util.List;

public class PasswordVerifierConstructor {

    private final List<String> rules;
    private final TimeProvider timeProvider;

    public PasswordVerifierConstructor(List<String> rules, TimeProvider timeProvider) {
        this.rules = rules;
        this.timeProvider = timeProvider;
    }

    public List<String> verifyPassword(String input) {
        DayOfWeek today = timeProvider.getDayOfWeek();

        if (today == DayOfWeek.SATURDAY || today == DayOfWeek.SUNDAY) {
            throw new RuntimeException("It's the weekend!");
        }
        return List.of();
    }
}
