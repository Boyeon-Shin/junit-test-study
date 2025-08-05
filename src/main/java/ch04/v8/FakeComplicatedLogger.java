package ch04.v8;

public class FakeComplicatedLogger implements IComplicatedLogger {
    public String infoWritten = "";
    public String debugWritten = "";
    public String warnWritten = "";
    public String errorWritten = "";

    @Override
    public void info(String text) {
        infoWritten = text;
    }

    @Override
    public void debug(String text, Object obj) {
        debugWritten = text;
    }

    @Override
    public void warn(String text) {
        warnWritten = text;
    }

    @Override
    public void error(String text, String location, String stacktrace) {
        errorWritten = text;
    }
}