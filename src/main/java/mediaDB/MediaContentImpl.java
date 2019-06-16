package mediaDB;

import uploaderDB.Uploader;

import java.util.Collection;

public class MediaContentImpl extends ContentImpl implements MediaContent {
    private int bitrate;
    private long length;

    public MediaContentImpl(Uploader uploader, String address, Collection<Tag> tags, int bitrate, long length) {
        super(uploader, address, tags);
        this.bitrate = bitrate;
        this.length = length;
    }


    @Override
    public int getBitrate() {
        return bitrate;
    }

    @Override
    public long getLength() {
        return length;
    }
    @Override
    public String toString() {
        return  super.toString()+
                "bitrate: " + bitrate +
                ", length: " + length ;
    }
}
