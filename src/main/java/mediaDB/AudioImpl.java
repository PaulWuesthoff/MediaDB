package mediaDB;


import uploaderDB.Uploader;
import java.util.Collection;

public class AudioImpl extends MediaContentImpl implements Audio {
    private int samplingRate;
    private String encoding;


    public AudioImpl(Uploader uploader, String address, Collection<Tag> tags, int bitrate, long length, int samplingRate, String encoding) {
        super(uploader, address, tags, bitrate, length);
        this.samplingRate = samplingRate;
        this.encoding = encoding;
    }


    @Override
    public int getSamplingRate() {
        return samplingRate;
    }

    @Override
    public String getEncoding() {
        return encoding;
    }

    @Override
    public String toString() {
        return "Audio content: " + super.toString() +
                ", samplingRate: " + samplingRate +
                ", encoding: " + encoding;
    }


}
