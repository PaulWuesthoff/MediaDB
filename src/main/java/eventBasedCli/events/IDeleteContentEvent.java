package eventBasedCli.events;

public interface IDeleteContentEvent {
    void onDeleteContentEvent(DeleteContentEventImpl deleteContentEvent);
}
