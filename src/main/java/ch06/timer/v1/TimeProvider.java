package ch06.timer.v1;

@FunctionalInterface
public interface TimeProvider {
    void setTimeout(Runnable task, long milliseconds);
}
