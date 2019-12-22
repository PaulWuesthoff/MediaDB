package mediaDB;

import org.junit.jupiter.api.Test;
import uploaderDB.UploaderImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LicensedImplTest {

    @Test
    void getHolder() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.News);
        Licensed licensed = new LicensedImpl(new UploaderImpl("test"),"address",tags,"holder");
        String expected = "holder";
        assertEquals(expected,licensed.getHolder());
    }
}