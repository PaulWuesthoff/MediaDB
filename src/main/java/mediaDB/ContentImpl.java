package mediaDB;

import uploaderDB.Uploader;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class ContentImpl implements Content, Serializable {

    private Uploader uploader;
    private String address;
    private Collection<Tag> tags;
    private Date timestamp;

    public ContentImpl(Uploader uploader, String address, Collection<Tag> tags) {
        this.uploader = uploader;
        this.address = address;
        this.tags = tags;
        this.timestamp = new Date();
    }

    @Override
    public Uploader getUploader() {
        return this.uploader;
    }

    @Override
    public String getAddress() {
        return this.address;
    }

    @Override
    public Collection<Tag> getTags() {
        return this.tags;
    }


    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString(){
        String content = "uploader: " +this.uploader.getName() +", address: "+this.address+", tags: "+this.tags +", ";
        return content;
    }
}
