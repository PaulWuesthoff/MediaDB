package eventBasedCli.events;

import uploaderDB.Uploader;

import java.util.List;

public class SendUploaderListEventImpl {
    List<Uploader> uploaderList;

    public SendUploaderListEventImpl(List<Uploader> uploaderList) {
        this.uploaderList = uploaderList;
    }

    public List<Uploader> getUploaderList() {
        return uploaderList;
    }
}
