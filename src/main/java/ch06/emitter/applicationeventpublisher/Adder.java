package ch06.emitter.applicationeventpublisher;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class Adder {
    private final ApplicationEventPublisher publisher;

    public Adder(ApplicationEventPublisher eventPublisher) {
        this.publisher = eventPublisher;
    }

    public int add(int x, int y) {
        int result = x + y;
        publisher.publishEvent(new AddedEvent(result));  //이벤트 발행
        return result;
    }
}
