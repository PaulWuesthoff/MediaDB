package mediaDB;

import uploaderDB.Uploader;

import java.util.Collection;

public class LicensedVideoImpl extends VideoImpl implements LicensedVideo {
    String holder;
    public LicensedVideoImpl(Uploader uploader, String address, Collection<Tag> tags,
                             int bitrate, long length, int width, int height, String encoding, String holder) {
        super(uploader, address, tags, bitrate, length, width, height, encoding);
        this.holder = holder;
    }

    @Override
    public String getHolder() {
        return holder;
    }
    @Override
    public String toString() {
        return "Licensed Video content: "  + super.toString().substring(15)+
                ", holder: " + holder;
    }
}
