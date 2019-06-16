package mediaDB;

import uploaderDB.Uploader;


import java.util.Collection;

public class LicensedAudioImpl extends AudioImpl implements LicensedAudio  {
    private String holder;
    public LicensedAudioImpl(Uploader uploader, String address, Collection<Tag> tags,
                             int bitrate, long length, int samplingRate, String encoding, String holder) {
        super(uploader, address, tags, bitrate, length, samplingRate, encoding);
        this.holder = holder;
    }

    @Override
    public String getHolder() {
        return holder;
    }
    @Override
    public String toString() {
        return "Licensed Audio-Video content: "  + super.toString().substring(15)+
                ", holder: " + holder;
    }
}
