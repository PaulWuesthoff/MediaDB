package mediaDB;

import uploaderDB.Uploader;


import java.util.Collection;

public class VideoImpl extends MediaContentImpl implements Video {
    private int width;
    private int height;
    private  String encoding;

    public VideoImpl(Uploader uploader, String address, Collection<Tag> tags, int bitrate, long length, int width, int height, String encoding) {
        super(uploader, address, tags, bitrate, length);
        this.width = width;
        this.height = height;
        this.encoding = encoding;
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
    public String getEncoding() {
        return encoding;
    }
    @Override
    public String toString() {
        return "Video content: "  + super.toString()+
                ", width: " + width +
                ", height: "+height +
                ", encoding: "+encoding;
    }
}
