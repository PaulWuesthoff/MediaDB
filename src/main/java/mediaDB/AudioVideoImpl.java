package mediaDB;

import uploaderDB.Uploader;


import java.util.Collection;

public class AudioVideoImpl extends AudioImpl implements AudioVideo {
    private int width;
    private int height;
    public AudioVideoImpl(Uploader uploader, String address, Collection<Tag> tags,
                          int bitrate, long length, int samplingRate, String encoding, int width, int height) {
        super(uploader, address, tags, bitrate, length, samplingRate, encoding);
        this.width = width;
        this.height = height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
    @Override
    public String toString() {
        return "Audio-Video content: " + super.toString().substring(15)+
                ", width: " + width +
                ", height: " + height ;
    }

}
