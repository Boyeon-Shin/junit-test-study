package ch03;

import java.time.DayOfWeek;

@FunctionalInterface
public interface TimeProvider {
    DayOfWeek getDayOfWeek();
}