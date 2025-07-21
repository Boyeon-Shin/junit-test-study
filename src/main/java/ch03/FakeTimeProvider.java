package ch03;

import java.time.DayOfWeek;

public class FakeTimeProvider implements TimeProvider {

    private final DayOfWeek fakeDay;

    public FakeTimeProvider(DayOfWeek fakeDay) {
        this.fakeDay = fakeDay;
    }

    @Override
    public DayOfWeek getDayOfWeek() {
        return fakeDay;
    }
}