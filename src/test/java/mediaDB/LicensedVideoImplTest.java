package mediaDB;

import org.junit.jupiter.api.Test;
import uploaderDB.UploaderImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LicensedVideoImplTest {

    @Test
    void getHolder() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.News);
        LicensedVideo licensedVideo = new LicensedVideoImpl(new UploaderImpl("test"),"address",tags,1,1,1,1,"encoding","holder");
        String expected = "holder";
        assertEquals(expected, licensedVideo.getHolder());
    }


}