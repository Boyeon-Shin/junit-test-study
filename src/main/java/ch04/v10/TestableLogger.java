package ch04.v10;

import ch04.v9.RealLogger;

public class TestableLogger extends RealLogger {
    public String logged = "";

    @Override
    public void info(String text) {
        this.logged = text; // 호출 기록 저장
    }
}
