package mediaDB;

import uploaderDB.Uploader;

import java.util.Collection;
import java.util.Date;

public interface Content {
    Uploader getUploader();
    String getAddress();
    Collection<Tag> getTags();
    Date getTimestamp();

}
