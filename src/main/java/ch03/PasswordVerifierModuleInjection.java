package ch03;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class PasswordVerifierModuleInjection {

    private TimeProvider timeProvider = () -> LocalDate.now().getDayOfWeek();

    private static final DayOfWeek SATURDAY = DayOfWeek.SATURDAY;
    private static final DayOfWeek SUNDAY = DayOfWeek.SUNDAY;

    public PasswordVerifierModuleInjection() {
    }

    public Runnable inject(TimeProvider fake) {
        timeProvider = fake;
        return this::reset;
    }

    public void reset() {
        timeProvider = () -> LocalDate.now().getDayOfWeek(); // 원복
    }

    public TimeProvider getTimeProvider() {
        return timeProvider;
    }

    public List<String> verifyPassword(String input, List<String> rules) {
        DayOfWeek today = timeProvider.getDayOfWeek();

        if (today == SATURDAY || today == SUNDAY) {
            throw new RuntimeException("It's the weekend!");
        }
        return List.of();
    }


}


