package ch06.timer.v1;

import java.util.function.Consumer;

public class Calculator {
    private final TimeProvider timeProvider;

    public Calculator(final TimeProvider timeProvider) {
        this.timeProvider = timeProvider;
    }

    public void calculator1(int x, int y, Consumer<Integer> callback) {
        timeProvider.setTimeout(() -> callback.accept(x + y), 5000);
    }
}
