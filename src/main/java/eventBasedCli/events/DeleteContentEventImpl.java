package eventBasedCli.events;

public class DeleteContentEventImpl {
    private int position;

    public DeleteContentEventImpl(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
