package ch06.emitter.applicationeventpublisher;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AdderEventHandler {

    @EventListener
    public void handleAdd(AddedEvent event) {
        System.out.println("이벤트 수신:" + event.getResult());
    }
}
