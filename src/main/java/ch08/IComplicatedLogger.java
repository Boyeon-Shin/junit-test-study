package ch08;

public interface IComplicatedLogger {
    void info(String message);
    void warn(String message);
    void error(String message, Throwable t);
}
