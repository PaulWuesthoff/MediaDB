package mediaDB;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uploaderDB.UploaderImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MediaContentImplTest {
    private MediaContent mediaContent;
    private List<Tag> tags;
    @BeforeEach
    void init(){
        tags = new ArrayList<>();
        tags.add(Tag.News);
        mediaContent = new MediaContentImpl(new UploaderImpl("test"),"address",tags,1,1);
    }

    @Test
    void getBitrate() {
        int expected = 1;
        assertEquals(expected,mediaContent.getBitrate());
    }

    @Test
    void getLength() {
        int expected = 1;
        assertEquals(expected,mediaContent.getLength());
    }

}