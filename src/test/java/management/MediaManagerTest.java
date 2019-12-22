package management;

import eventBasedCli.observers.CapacityObserver;
import eventBasedCli.observers.IObserver;
import mediaDB.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uploaderDB.UploaderImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MediaManagerTest {
    MediaManager mediaManager = new MediaManager();
    List<Tag> tags = new ArrayList<>();
    private Content content;

    @BeforeEach
    void init() {
        tags.add(Tag.News);
        content = new AudioImpl(new UploaderImpl("Test"),
                "address", tags, 12, 12, 12, "encoding");
    }

    @Test
    void registerObserver() {
        IObserver observer = new CapacityObserver(Mockito.mock(HeadQuarter.class));
       assertTrue(mediaManager.registerObserver(observer));
    }

    @Test
    void addContentUseCase() {
        assertTrue(mediaManager.addContent(content));
    }

    @Test
    void addContentNull() {
        assertFalse(mediaManager.addContent(null));
    }

    @Test
    void addContentStorageFull() {
        for (int i = 0; i < mediaManager.getMAXSIZE(); i++) {
            mediaManager.addContent(content);
        }
        assertFalse(mediaManager.addContent(content));
    }


    @Test
    void deleteContentUseCase() {
        mediaManager.addContent(content);
        assertTrue(mediaManager.deleteContent(content));
    }

    @Test
    void deleteContentNull() {
        assertFalse(mediaManager.deleteContent(null));
    }

    @Test
    void deleteContentNotInList() {
        assertFalse(mediaManager.deleteContent(content));
    }

    @Test
    void getTimestamp() {
        mediaManager.addContent(content);
        Date date = new Date(System.currentTimeMillis());
        assertEquals(date.toString(), mediaManager.getTimestamp(content));
    }

    @Test
    void getTimestampContentNull() {
        Date date = new Date(System.currentTimeMillis());
        assertEquals("The content you are looking for dose not exist! ", mediaManager.getTimestamp(null));
    }

    @Test
    void getTimestampContentNotInList() {
        Date date = new Date(System.currentTimeMillis());
        Content contentToTest = new VideoImpl(new UploaderImpl("Test"), "address", tags, 12, 12,
                12, 12, "encoding");
        assertEquals("The content you are looking for dose not exist! ", mediaManager.getTimestamp(contentToTest));
    }

    @Test
    void getAddressUseCase() {
        mediaManager.addContent(content);
        String expected = "address";
        assertEquals(expected, mediaManager.getAddress(content));
    }

    @Test
    void getAddressContentNull() {
        String expected = "The content you are looking for dose not exist! ";
        assertEquals(expected, mediaManager.getAddress(null));
    }

    @Test
    void getAddressContentNotInList() {
        String expected = "The content you are looking for dose not exist! ";
        Content contentToTest = new VideoImpl(new UploaderImpl("Test"), "address", tags, 12, 12,
                12, 12, "encoding");
        assertEquals(expected, mediaManager.getAddress(contentToTest));
    }

    @Test
    void printList() {
        mediaManager.addContent(content);
        assertEquals(" 1. "+content.toString(), mediaManager.printList());
    }
    @Test
    void printListContentlistIsEmpty() {
        assertEquals("The List is Empty", mediaManager.printList());
    }
    @Test
    void printListContentNull() {
        mediaManager.addContent(null);
        assertEquals("The List is Empty", mediaManager.printList());
    }

    @Test
    void getContentsByInterfaceTypeUseCase() {
        mediaManager.addContent(content);
      assertEquals(content.toString(),mediaManager.getContentsByInterfaceType(Audio.class).get(0).toString());
    }
    @Test
    void getContentsByInterfaceTypeNull() {
        assertEquals("[]",mediaManager.getContentsByInterfaceType(null).toString());
    }
    @Test
    void getContentsByInterfaceWrongClassName() {
        mediaManager.addContent(content);
        assertEquals("[]",mediaManager.getContentsByInterfaceType(Video.class).toString());

    }

    @Test
    void getUsedTagsUseCase() {
        mediaManager.addContent(content);
       List<Tag> list = new ArrayList<Tag>(mediaManager.getUsedTags());
       assertTrue(list.size() == 1);
        assertEquals(Tag.News,list.get(0));
    }
    @Test
    void getUsedTagsListIsEmpty() {
        List<Tag> list = new ArrayList<Tag>(mediaManager.getUsedTags());
        assertTrue(list.size() == 0);
    }

    @Test
    void getUnusedTagsUseCase() {
        mediaManager.addContent(content);
        List<Tag> list = new ArrayList<Tag>(mediaManager.getUnusedTags());
        assertTrue(list.size() == 3);
        List<Tag> expectedList = new ArrayList<Tag>();
        expectedList.add(Tag.Animal);
        expectedList.add(Tag.Tutorial);
        expectedList.add(Tag.Lifestyle);

        assertEquals(expectedList,list);
    }
    @Test
    void getUnusedTagsListEmpty() {
        List<Tag> list = new ArrayList<Tag>(mediaManager.getUnusedTags());
        assertTrue(list.size() == 4);
        List<Tag> expectedList = new ArrayList<Tag>();
        expectedList.add(Tag.Animal);
        expectedList.add(Tag.Tutorial);
        expectedList.add(Tag.Lifestyle);
        expectedList.add(Tag.News);

        assertEquals(expectedList,list);
    }

    @Test
    void getAmountOfUploadsForOneUploaderUseCase() {
        UploaderManager uploaderManager = Mockito.mock(UploaderManager.class);
        UploaderImpl uploader = Mockito.mock(UploaderImpl.class);
        Mockito.when(uploader.getName()).thenReturn("Test");
        Mockito.when(uploaderManager.addUploader(uploader)).thenReturn(true);
        tags.add(Tag.News);
        mediaManager.addContent(new AudioImpl(uploader,"address",tags,23,54,12,"test"));
        assertEquals(1,mediaManager.getAmountOfUploadsForOneUploader(uploader));
    }
    @Test
    void getAmountOfUploadsForOneUploaderNull() {
        assertEquals(0,mediaManager.getAmountOfUploadsForOneUploader(null));
    }

    @Test
    void deleteContentForOneUploaderUseCase() {
        UploaderManager uploaderManager = Mockito.mock(UploaderManager.class);
        UploaderImpl uploader = Mockito.mock(UploaderImpl.class);
        Mockito.when(uploader.getName()).thenReturn("Test");
        Mockito.when(uploaderManager.addUploader(uploader)).thenReturn(true);
        tags.add(Tag.News);
        mediaManager.addContent(new AudioImpl(uploader,"address",tags,23,54,12,"test"));
        assertTrue(mediaManager.deleteContentForOneUploader(uploader));
    }
    @Test
    void deleteContentForOneUploaderNull() {
        assertFalse(mediaManager.deleteContentForOneUploader(null));
    }

    @Test
    void getContentList() {
        List<Content> testList = new ArrayList<>();
        testList.add(content);
        mediaManager.addContent(content);
        assertEquals(testList,mediaManager.getContentList());
    }

    @Test
    void getMAXSIZE() {
        int expected = 10;
        assertEquals(expected,mediaManager.getMAXSIZE());
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
        tags.add(Tag.Lifestyle);
        Content content1 = new AudioImpl(new UploaderImpl("Test1"), "address", tags, 223, 546, 564, "encoding");
        mediaManager.addContent(content1);
        mediaManager.addContent(content);
        Content oldestContent = mediaManager.getOldestContent();
        assertNotNull(oldestContent);
    }
}