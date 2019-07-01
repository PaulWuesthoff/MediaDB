package management;

import mediaDB.AudioImpl;
import mediaDB.Content;
import mediaDB.Tag;
import org.junit.jupiter.api.Test;
import uploaderDB.UploaderImpl;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MediaManagerTest {
    MediaManager mediaManager;

    @Test
    void registerObserver() {
    }

    @Test
    void addContent() {
    }

    @Test
    void deleteContent() {
    }

    @Test
    void getTimestamp() {
    }

    @Test
    void getAddress() {
    }

    @Test
    void printList() {
    }

    @Test
    void getContentsByInterfaceType() {
    }

    @Test
    void getUsedTags() {
    }

    @Test
    void getUnusedTags() {
    }

    @Test
    void getAmountOfUploadsForOneUploader() {
    }

    @Test
    void deleteContentForOneUploader() {
    }

    @Test
    void getContentList() {
    }

    @Test
    void getMAXSIZE() {
    }

    @Test
    void getUploaderManager() {
    }

    //    @Test
    // could not test that because it depends on the speed of the running system
//    void getOldestContent() {
//        mediaManager = new MediaManager();
//        ArrayList<Tag> tags = new ArrayList<Tag>();
//        tags.add(Tag.Lifestyle);
//        Content content = new AudioImpl(new UploaderImpl("Paul"), "sdfsfd", tags, 223, 546, 564, "dsf");
//        Content content1 = new AudioImpl(new UploaderImpl("Julius"), "sdfsfd", tags, 223, 546, 564, "dsf");
//        mediaManager.addContent(content);
//        mediaManager.addContent(content1);
//        Content oldestContent = mediaManager.getOldestContent();
//        assertEquals(content,oldestContent);
//
//    }
//    @Test
    // could not test that because it depends on the speed of the running system
//    void getOldestContentDifferentOrder() {
//        mediaManager = new MediaManager();
//        ArrayList<Tag> tags = new ArrayList<Tag>();
//        tags.add(Tag.Lifestyle);
//        Content content = new AudioImpl(new UploaderImpl("Paul"), "sdfsfd", tags, 223, 546, 564, "dsf");
//        Content content1 = new AudioImpl(new UploaderImpl("Julius"), "sdfsfd", tags, 223, 546, 564, "dsf");
//        mediaManager.addContent(content1);
//        mediaManager.addContent(content);
//        Content oldestContent = mediaManager.getOldestContent();
//        assertEquals(content,oldestContent);
//    }
    @Test
    void getOldestContentNotNull() {
        mediaManager = new MediaManager();
        ArrayList<Tag> tags = new ArrayList<Tag>();
        tags.add(Tag.Lifestyle);
        Content content = new AudioImpl(new UploaderImpl("Paul"), "sdfsfd", tags, 223, 546, 564, "dsf");
        Content content1 = new AudioImpl(new UploaderImpl("Julius"), "sdfsfd", tags, 223, 546, 564, "dsf");
        mediaManager.addContent(content1);
        mediaManager.addContent(content);
        Content oldestContent = mediaManager.getOldestContent();
        assertNotNull(oldestContent);
    }
}