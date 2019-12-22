package mediaDB;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uploaderDB.UploaderImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AudioImplTest  {
private Audio audio;
private List <Tag> tags;
    @BeforeEach
    void init(){
        tags = new ArrayList<>();
        tags.add(Tag.News);
        audio = new AudioImpl(new UploaderImpl("Test"),"address",tags,1,1,1,"encoding");
    }
    @Test
    void getSamplingRate() {
    int expected = 1;
    assertEquals(expected,audio.getSamplingRate());
    }

    @Test
    void getEncoding() {
        String expected = "encoding";
        assertEquals(expected,audio.getEncoding());
    }


}