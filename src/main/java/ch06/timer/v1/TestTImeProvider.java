package ch06.timer.v1;

public class TestTImeProvider  implements TimeProvider {
    @Override
    public void setTimeout(final Runnable task, final long milliseconds) {
        task.run();  //지연 없이 즉시 실행
    }
}
