package mediaDB;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uploaderDB.UploaderImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContentImplTest {
    private Content content;
    private List<Tag> tags;

    @BeforeEach
    void init() {
        tags = new ArrayList<>();
        tags.add(Tag.News);
        content = new ContentImpl(new UploaderImpl("test"), "address", tags);
    }

    @Test
    void getUploader() {
        String expected = "test";
        assertEquals(expected, content.getUploader().getName());
    }

    @Test
    void getAddress() {
        String expected = "address";
        assertEquals(expected, content.getAddress());
    }

    @Test
    void getTags() {
        Tag expected = Tag.News;
        List<Tag> tags = (List<Tag>) content.getTags();
        assertEquals(expected, tags.get(0));
    }

    @Test
    void getTimestamp() {
        Date expected = new Date(System.currentTimeMillis());
        assertEquals(expected.toString(), content.getTimestamp().toString());
    }


}