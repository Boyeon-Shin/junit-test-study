package ch07;

import java.util.function.Consumer;

public class Trigger {

    public static int trigger(int x, int y, Consumer<String> callback) {
        callback.accept("I'm triggered");

        return x + y;
    }
}