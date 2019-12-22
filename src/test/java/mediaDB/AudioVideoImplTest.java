package mediaDB;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uploaderDB.UploaderImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AudioVideoImplTest {
    private AudioVideo audioVideo;
    private List<Tag> tags;

    @BeforeEach
    void init() {
        tags = new ArrayList<>();
        tags.add(Tag.News);
        audioVideo = new AudioVideoImpl(new UploaderImpl("test"),"address",tags,1,1,1,"encoding",1,1);
    }

    @Test
    void getWidth() {
        int expected = 1;
        assertEquals(expected,audioVideo.getWidth());
    }

    @Test
    void getHeight() {
        int expected = 1;
        assertEquals(expected,audioVideo.getHeight());
    }


}