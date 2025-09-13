package ch06.timer.v2;

import ch06.timer.v1.TimeProvider;
import java.util.ArrayList;
import java.util.List;

public class FakeScheduler implements TimeProvider {
    private final List<Runnable> scheduledTasks = new ArrayList<>();

    @Override
    public void setTimeout(Runnable task, long delayMillis) {
        scheduledTasks.add(task); // 실행은 보류
    }

    // Jest의 advanceTimersToNextTimer() 역할
    public void advanceTime() {
        for (Runnable task : scheduledTasks) {
            task.run(); // 저장된 작업 실행
        }
        scheduledTasks.clear();
    }

    // Jest의 resetAllTimers() 역할
    public void clearAll() {
        scheduledTasks.clear();
    }
}