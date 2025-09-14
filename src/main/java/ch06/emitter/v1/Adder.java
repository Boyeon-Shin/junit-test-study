package ch06.emitter.v1;

import java.util.ArrayList;
import java.util.List;

public class Adder {
    private final List<AdderListener> listeners = new ArrayList<>();

    public void addListener(AdderListener listener) {
        listeners.add(listener);
    }

    public int add(int x, int y) {
        int result = x + y;
        emitAdded(result);
        return result;
    }

    private void emitAdded(int result) {
        for (AdderListener listener : listeners) {
            listener.onAdded(result);
        }
    }
}
