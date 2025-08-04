package ch04.v7.testDouble;

import ch04.v7.ILogger;

public class FakeLogger implements ILogger {
    public String written = "";

    @Override
    public void info(String text) {
        this.written = text;
    }
}