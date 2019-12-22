package mediaDB;

import org.junit.jupiter.api.Test;
import uploaderDB.UploaderImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LicensedAudioVideoImplTest {

    @Test
    void getHolder() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.News);
        LicensedAudioVideo licensedAudioVideo = new LicensedAudioVideoImpl(new UploaderImpl("test"), "address", tags, 1, 1, 1, "encoding", 1, 1, "holder");
        String expected = "holder";
        assertEquals(expected, licensedAudioVideo.getHolder());
    }

}