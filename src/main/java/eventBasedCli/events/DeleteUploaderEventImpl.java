package eventBasedCli.events;

import uploaderDB.Uploader;

public class DeleteUploaderEventImpl {
    private String uploader;

    public DeleteUploaderEventImpl(String  uploader) {
        this.uploader = uploader;
    }

    public String getUploader() {
        return uploader;
    }
}
