package eventBasedCli.events;

public class GetAddressAndDateEventImpl {
    private int position;

    public GetAddressAndDateEventImpl(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
