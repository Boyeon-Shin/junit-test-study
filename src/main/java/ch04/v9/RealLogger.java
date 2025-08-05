package ch04.v9;

// 실제 로거 클래스
public class RealLogger {
    public void info(String text) {
        System.out.println("INFO: " + text);
    }

    public void debug(String text) {
        System.out.println("DEBUG: " + text);
    }
}
