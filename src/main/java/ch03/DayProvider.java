package ch03;

import java.time.DayOfWeek;

@FunctionalInterface
public interface DayProvider {
    DayOfWeek getDay();
}