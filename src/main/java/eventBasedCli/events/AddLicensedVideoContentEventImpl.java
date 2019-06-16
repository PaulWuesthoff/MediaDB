package eventBasedCli.events;

import mediaDB.Tag;
import uploaderDB.Uploader;

import java.util.Collection;

public class AddLicensedVideoContentEventImpl {
    private String uploader;
    private String address;
    private Collection<Tag> tags;
    private int bitrate;
    private long length;
    private int width;
    private int height;
    private String encoding;
    private String holder;

    public AddLicensedVideoContentEventImpl(String uploader, String address, Collection<Tag> tags,
                                            int bitrate, long length, int width, int height, String encoding, String holder) {
        this.uploader = uploader;
        this.address = address;
        this.tags = tags;
        this.bitrate = bitrate;
        this.length = length;
        this.width = width;
        this.height = height;
        this.encoding = encoding;
        this.holder = holder;
    }

    public String getUploader() {
        return uploader;
    }

    public String getAddress() {
        return address;
    }

    public Collection<Tag> getTags() {
        return tags;
    }

    public int getBitrate() {
        return bitrate;
    }

    public long getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getEncoding() {
        return encoding;
    }

    public String getHolder() {
        return holder;
    }
}
