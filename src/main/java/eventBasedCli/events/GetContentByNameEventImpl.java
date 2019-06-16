package eventBasedCli.events;

public class GetContentByNameEventImpl  {
   private String contentName;

    public GetContentByNameEventImpl(String contentName) {
        this.contentName = contentName;
    }

    public String getContentName() {
        return contentName;
    }
}
