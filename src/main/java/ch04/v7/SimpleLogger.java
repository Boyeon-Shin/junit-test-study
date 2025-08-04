package ch04.v7;

public class SimpleLogger implements ILogger {
    @Override
    public void info(final String text) {
        System.out.println("로그: " + text);
    }
}
