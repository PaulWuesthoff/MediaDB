package mediaDB;

import uploaderDB.Uploader;

import java.util.Collection;

public class LicensedAudioVideoImpl extends AudioVideoImpl implements LicensedAudioVideo {
    private String holder;
    public LicensedAudioVideoImpl(Uploader uploader, String address, Collection<Tag> tags,
                                  int bitrate, long length, int samplingRate, String encoding,
                                  int width, int height, String holder) {
        super(uploader, address, tags, bitrate, length, samplingRate, encoding, width, height);
        this.holder = holder;
    }

    @Override
    public String getHolder() {
        return holder;
    }
    @Override
    public String toString() {
        return  "Licensed Audio-Video content :"  + super.toString().substring(21)+
                ", holder: " + holder;
    }
}
