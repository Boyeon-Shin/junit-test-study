package ch03;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class RealTimeProvider implements TimeProvider {
    @Override
    public DayOfWeek getDayOfWeek() {
        return LocalDate.now().getDayOfWeek();
    }
}