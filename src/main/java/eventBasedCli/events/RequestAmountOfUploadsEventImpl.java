package eventBasedCli.events;

public class RequestAmountOfUploadsEventImpl{
    String name;

    public RequestAmountOfUploadsEventImpl(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
