package ch06.emitter.applicationeventpublisher;

public class AddedEvent {
    private final int result;

    public AddedEvent(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }
}
