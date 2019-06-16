package eventBasedCli.events;



public class AddUploaderEventImpl {
    private String uploader;

    public AddUploaderEventImpl(String uploader) {
        this.uploader = uploader;
    }

    public String getUploader() {
        return uploader;
    }
}
