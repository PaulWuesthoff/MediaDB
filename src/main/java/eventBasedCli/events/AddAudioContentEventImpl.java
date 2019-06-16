package eventBasedCli.events;

import mediaDB.Tag;
import uploaderDB.Uploader;

import java.util.Collection;

public class AddAudioContentEventImpl {
    private String uploader;
    private String address;
    private Collection<Tag> tags;
    private int bitrate;
    private long length;
    private int samplingRate;
    private String encoding;

    public AddAudioContentEventImpl(String uploader, String address, Collection<Tag> tags, int bitrate, long length, int samplingRate, String encoding) {
        this.uploader = uploader;
        this.address = address;
        this.tags = tags;
        this.bitrate = bitrate;
        this.length = length;
        this.samplingRate = samplingRate;
        this.encoding = encoding;
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

    public int getSamplingRate() {
        return samplingRate;
    }

    public String getEncoding() {
        return encoding;
    }
}
