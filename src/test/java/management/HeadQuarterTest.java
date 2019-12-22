package management;

import eventBasedCli.observers.CapacityObserver;
import eventBasedCli.observers.IObserver;
import mediaDB.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uploaderDB.Uploader;
import uploaderDB.UploaderImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HeadQuarterTest {
    private HeadQuarter headQuarter;
    private Uploader uploader;
    private List<Tag> tags;
    private final String uploaderName = "Test";

    private

    @BeforeEach
    void init() {
        headQuarter = new HeadQuarter();
        tags = new ArrayList<>();
    }

    @Test
    void getMediaListUseCase() {
        uploader = new UploaderImpl(uploaderName);
        tags.add(Tag.News);
        headQuarter.addUploader(uploader);
        headQuarter.addAudioContent(headQuarter.getUploader("Test"), 1, "encoding", 1, 1, "address", tags);
        List<Content> testList = new ArrayList<>();
        Content content = new AudioImpl(uploader, "address", tags, 1, 1, 1, "encoding");
        testList.add(content);
        assertTrue(headQuarter.getMediaList().size() > 0);
        assertEquals(testList.toString(), headQuarter.getMediaList().toString());
    }

    @Test
    void deleteUploaderUseCase() {
        uploader = new UploaderImpl(uploaderName);
        headQuarter.addUploader(uploader);
        assertTrue(headQuarter.deleteUploader(uploader));

    }

    @Test
    void deleteUploaderNull() {
        assertFalse(headQuarter.deleteUploader(null));

    }

    @Test
    void deleteUploaderNotInList() {
        uploader = new UploaderImpl("Test");
        assertFalse(headQuarter.deleteUploader(uploader));

    }

    @Test
    void deleteContent() {
        uploader = new UploaderImpl(uploaderName);
        tags.add(Tag.News);
        headQuarter.addUploader(uploader);
        headQuarter.addAudioContent(headQuarter.getUploader("Test"), 1, "encoding", 1, 1, "address", tags);
        Content content = new AudioImpl(uploader, "address", tags, 1, 1, 1, "encoding");
        assertEquals(content.toString(), headQuarter.getMediaList().get(0).toString());
        assertTrue(headQuarter.deleteContent(headQuarter.getMediaList().get(0)));
        assertTrue(headQuarter.getMediaList().size() == 0);
    }

    @Test
    void deleteContentNull() {
        assertFalse(headQuarter.deleteContent(null));
    }

    @Test
    void deleteContentNotInList() {
        Content content = new AudioImpl(uploader, "address", tags, 1, 1, 1, "encoding");
        assertFalse(headQuarter.deleteContent(content));
    }

    @Test
    void addUploaderUseCase() {
        uploader = new UploaderImpl("Test");
        assertTrue(headQuarter.addUploader(uploader));
    }

    @Test
    void addUploaderNull() {
        assertFalse(headQuarter.addUploader(null));
    }

    @Test
    void addUploaderHasNoName() {
        uploader = new UploaderImpl("");
        assertFalse(headQuarter.addUploader(uploader));
    }

    @Test
    void addUploaderHasSpecialCharactersInName() {
        uploader = new UploaderImpl("!!??");
        assertFalse(headQuarter.addUploader(uploader));
    }

    @Test
    void addMediaContentUseCase() {
        uploader = new UploaderImpl(uploaderName);
        tags.add(Tag.News);
        headQuarter.addUploader(uploader);
        Content content = new MediaContentImpl(headQuarter.getUploader("test"), "address", tags, 1, 1);
        assertTrue(headQuarter.addMediaContent(content));

    }

    @Test
    void addMediaContentListFull() {
        uploader = new UploaderImpl(uploaderName);
        tags.add(Tag.News);
        headQuarter.addUploader(uploader);
        Content content = new MediaContentImpl(uploader, "address", tags, 1, 1);
        for (int i = 0; i < headQuarter.getMaxSizeOfDatabase(); i++) {
            headQuarter.addMediaContent(content);
        }
        assertFalse(headQuarter.addMediaContent(content));

    }

    @Test
    void addMediaContentWithoutUploader() {
        tags.add(Tag.News);
        Content content = new MediaContentImpl(null, "address", tags, 1, 1);
        assertFalse(headQuarter.addMediaContent(content));

    }

    @Test
    void addMediaContentWithoutTags() {
        uploader = new UploaderImpl(uploaderName);
        headQuarter.addUploader(uploader);
        Content content = new MediaContentImpl(headQuarter.getUploader("test"), "address", null, 1, 1);
        assertFalse(headQuarter.addMediaContent(content));

    }

    @Test
    void addMediaContentIsNull() {
        assertFalse(headQuarter.addMediaContent(null));
    }

    @Test
    void addAudioContentUseCase() {
        uploader = new UploaderImpl(uploaderName);
        tags.add(Tag.News);
        headQuarter.addUploader(uploader);
        assertTrue(headQuarter.addAudioContent(headQuarter.getUploader("test"), 1, "encoding", 1, 1, "address", tags));
    }

    @Test
    void addAudioContentUploaderNull() {
        tags.add(Tag.News);
        assertFalse(headQuarter.addAudioContent(null, 1, "encoding", 1, 1, "address", tags));
    }

    @Test
    void addAudioContentTagsNull() {
        uploader = new UploaderImpl(uploaderName);
        headQuarter.addUploader(uploader);
        assertFalse(headQuarter.addAudioContent(headQuarter.getUploader("test"), 1, "encoding", 1, 1, "address", null));
    }

    @Test
    void addAudioContentStorageFull() {
        uploader = new UploaderImpl(uploaderName);
        tags.add(Tag.News);
        headQuarter.addUploader(uploader);
        for (int i = 0; i < headQuarter.getMaxSizeOfDatabase(); i++) {
            headQuarter.addAudioContent(headQuarter.getUploader("test"), 1, "encoding", 1, 1, "address", tags);
        }
        assertFalse(headQuarter.addAudioContent(headQuarter.getUploader("test"), 1, "encoding", 1, 1, "address", tags));
    }

    @Test
    void addVideoContentUseCase() {
        uploader = new UploaderImpl(uploaderName);
        tags.add(Tag.News);
        headQuarter.addUploader(uploader);
        assertTrue(headQuarter.addVideoContent(headQuarter.getUploader("test"), "address", tags, 1, 1, 1, 1, "encoding"));
    }

    @Test
    void addVideoContentUploaderNull() {
        tags.add(Tag.News);
        assertFalse(headQuarter.addVideoContent(null, "address", tags, 1, 1, 1, 1, "encoding"));
    }

    @Test
    void addVideoContentTagsNull() {
        uploader = new UploaderImpl(uploaderName);
        headQuarter.addUploader(uploader);
        assertFalse(headQuarter.addVideoContent(headQuarter.getUploader("test"), "address", null, 1, 1, 1, 1, "encoding"));
    }

    @Test
    void addVideoContenStorageFull() {
        uploader = new UploaderImpl(uploaderName);
        tags.add(Tag.News);
        headQuarter.addUploader(uploader);
        for (int i = 0; i < headQuarter.getMaxSizeOfDatabase(); i++) {
            headQuarter.addVideoContent(headQuarter.getUploader("test"), "address", tags, 1, 1, 1, 1, "encoding");
        }
        assertFalse(headQuarter.addVideoContent(headQuarter.getUploader("test"), "address", tags, 1, 1, 1, 1, "encoding"));
    }

    @Test
    void addAudioVideoContentUseCase() {
        uploader = new UploaderImpl(uploaderName);
        headQuarter.addUploader(uploader);
        tags.add(Tag.News);
        assertTrue(headQuarter.addAudioVideoContent(headQuarter.getUploader("test"), "address",
                tags, 1, 1, 1, "encoding", 1, 1));
    }

    @Test
    void addAudioVideoContentUploaderNull() {
        tags.add(Tag.News);
        assertFalse(headQuarter.addAudioVideoContent(null, "address",
                tags, 1, 1, 1, "encoding", 1, 1));
    }

    @Test
    void addAudioVideoContentTagsNull() {
        uploader = new UploaderImpl(uploaderName);
        headQuarter.addUploader(uploader);
        assertFalse(headQuarter.addAudioVideoContent(headQuarter.getUploader("test"), "address",
                null, 1, 1, 1, "encoding", 1, 1));
    }

    @Test
    void addAudioVideoContentStorageFull() {
        uploader = new UploaderImpl(uploaderName);
        headQuarter.addUploader(uploader);
        tags.add(Tag.News);
        for (int i = 0; i < headQuarter.getMaxSizeOfDatabase(); i++) {
            headQuarter.addAudioVideoContent(headQuarter.getUploader("test"), "address",
                    tags, 1, 1, 1, "encoding", 1, 1);
        }
        assertFalse(headQuarter.addAudioVideoContent(headQuarter.getUploader("test"), "address",
                tags, 1, 1, 1, "encoding", 1, 1));
    }

    @Test
    void addLicensedAudioUseCase() {
        uploader = new UploaderImpl(uploaderName);
        headQuarter.addUploader(uploader);
        tags.add(Tag.News);
        assertTrue(headQuarter.addLicensedAudio(headQuarter.getUploader("test"), "address", tags, 1, 1, 1, "encoding", "holder"));
    }

    @Test
    void addLicensedAudioUploaderNull() {
        tags.add(Tag.News);
        assertFalse(headQuarter.addLicensedAudio(null, "address", tags, 1, 1, 1, "encoding", "holder"));
    }

    @Test
    void addLicensedAudioTagsNull() {
        uploader = new UploaderImpl(uploaderName);
        headQuarter.addUploader(uploader);
        assertFalse(headQuarter.addLicensedAudio(headQuarter.getUploader("test"), "address", null, 1, 1, 1, "encoding", "holder"));
    }

    @Test
    void addLicensedAudioStorageFull() {
        uploader = new UploaderImpl(uploaderName);
        headQuarter.addUploader(uploader);
        tags.add(Tag.News);
        for (int i = 0; i <= headQuarter.getMaxSizeOfDatabase(); i++) {
            headQuarter.addLicensedAudio(headQuarter.getUploader("test"), "address", tags, 1, 1, 1, "encoding", "holder");
        }
        assertFalse(headQuarter.addLicensedAudio(headQuarter.getUploader("test"), "address", tags, 1, 1, 1, "encoding", "holder"));
    }

    @Test
    void addLicensedVideo() {
        uploader = new UploaderImpl(uploaderName);
        headQuarter.addUploader(uploader);
        tags.add(Tag.News);
        assertTrue(headQuarter.addLicensedVideo(headQuarter.getUploader("test"), "address", tags, 1, 1, 1, 1, "encoding", "holder"));
    }

    @Test
    void addLicensedVideoUploaderNull() {
        tags.add(Tag.News);
        assertFalse(headQuarter.addLicensedVideo(null, "address", tags, 1, 1, 1, 1, "encoding", "holder"));
    }

    @Test
    void addLicensedVideoTagsNull() {
        uploader = new UploaderImpl(uploaderName);
        headQuarter.addUploader(uploader);
        assertFalse(headQuarter.addLicensedVideo(headQuarter.getUploader("test"), "address", null, 1, 1, 1, 1, "encoding", "holder"));
    }

    @Test
    void addLicensedVideoStorageFull() {
        uploader = new UploaderImpl(uploaderName);
        headQuarter.addUploader(uploader);
        tags.add(Tag.News);
        for (int i = 0; i <= headQuarter.getMaxSizeOfDatabase(); i++) {
            headQuarter.addLicensedVideo(headQuarter.getUploader("test"), "address", tags, 1, 1, 1, 1, "encoding", "holder");
        }
        assertFalse(headQuarter.addLicensedVideo(headQuarter.getUploader("test"), "address", tags, 1, 1, 1, 1, "encoding", "holder"));
    }

    @Test
    void addLicensedAudioVideoUseCase() {
        uploader = new UploaderImpl(uploaderName);
        headQuarter.addUploader(uploader);
        tags.add(Tag.News);
        assertTrue(headQuarter.addLicensedAudioVideo(headQuarter.getUploader("test"), "address", tags, 1, 1, 1, 1, 1, "encoding", "holder"));
    }

    @Test
    void addLicensedAudioVideoUploaderNull() {
        tags.add(Tag.News);
        assertFalse(headQuarter.addLicensedAudioVideo(null, "address", tags, 1, 1, 1, 1, 1, "encoding", "holder"));
    }

    @Test
    void addLicensedAudioVideoTagsNull() {
        uploader = new UploaderImpl(uploaderName);
        headQuarter.addUploader(uploader);
        assertFalse(headQuarter.addLicensedAudioVideo(headQuarter.getUploader("test"), "address", null, 1, 1, 1, 1, 1, "encoding", "holder"));
    }

    @Test
    void addLicensedAudioVideoStorageFull() {
        uploader = new UploaderImpl(uploaderName);
        headQuarter.addUploader(uploader);
        tags.add(Tag.News);
        for (int i = 0; i <= headQuarter.getMaxSizeOfDatabase(); i++) {
            headQuarter.addLicensedAudioVideo(headQuarter.getUploader("test"), "address", tags, 1, 1, 1, 1, 1, "encoding", "holder");
        }
        assertFalse(headQuarter.addLicensedAudioVideo(headQuarter.getUploader("test"), "address", tags, 1, 1, 1, 1, 1, "encoding", "holder"));
    }

    @Test
    void getUploaderUseCase() {
        uploader = new UploaderImpl(uploaderName);
        headQuarter.addUploader(uploader);
        assertEquals(uploader.getName(), headQuarter.getUploader("Test").getName());
    }

    @Test
    void getUploaderNotInList() {
        uploader = new UploaderImpl(uploaderName);
        headQuarter.addUploader(uploader);
        assertNull(headQuarter.getUploader("ihd"));
    }

    @Test
    void getUploaderNull() {
        assertThrows(NullPointerException.class, () -> headQuarter.getUploader(null));
    }

    @Test
    void getContentByNameUseCase() {
        uploader = new UploaderImpl(uploaderName);
        tags.add(Tag.News);
        headQuarter.addUploader(uploader);
        headQuarter.addAudioContent(headQuarter.getUploader("test"), 1, "encoding", 1, 1, "address", tags);
        List<Content> contents = headQuarter.getContentByName(Audio.class);
        List<Content> expected = new ArrayList<>();
        Content content = new AudioImpl(uploader, "address", tags, 1, 1, 1, "encoding");
        expected.add(content);
        assertTrue(contents.size() > 0);
        assertEquals(expected.toString(), contents.toString());
        assertEquals(Audio.class, contents.get(0).getClass().getInterfaces()[0]);
    }

    @Test
    void getContentByNameNull() {
        assertEquals("[]", headQuarter.getContentByName(null).toString());
    }

    @Test
    void getContentByNameWrongClassName() {
        assertEquals("[]", headQuarter.getContentByName(Class.class).toString());
    }


    @Test
    void getAmountOfUploadsForOneUploader() {
        uploader = new UploaderImpl(uploaderName);
        tags.add(Tag.News);
        headQuarter.addUploader(uploader);
        headQuarter.addAudioContent(headQuarter.getUploader("test"), 1, "encoding", 1, 1, "address", tags);
        int expected = 1;
        assertEquals(expected, headQuarter.getAmountOfUploadsForOneUploader(headQuarter.getUploader("test")));
    }

    @Test
    void getAmountOfUploadsForOneUploaderNull() {
        int expected = 0;
        assertEquals(expected, headQuarter.getAmountOfUploadsForOneUploader(headQuarter.getUploader("test")));
    }

    @Test
    void getAmountOfUploadsForOneUploaderUnkown() {
        int expected = 0;
        assertEquals(expected, headQuarter.getAmountOfUploadsForOneUploader(headQuarter.getUploader("test")));
    }

    @Test
    void printList() {
        uploader = new UploaderImpl(uploaderName);
        tags.add(Tag.News);
        headQuarter.addUploader(uploader);
        headQuarter.addAudioContent(headQuarter.getUploader("test"), 1, "encoding", 1, 1, "address", tags);
        String expected = " 1. Audio content: uploader: Test, address: address, tags: [News], bitrate: 1, length: 1, samplingRate: 1, encoding: encoding";
        assertEquals(expected, headQuarter.printList());
    }

    @Test
    void printListIsEmpty() {
        String expected = "The List is Empty";
        assertEquals(expected, headQuarter.printList());
    }

    @Test
    void getAddress() {
        uploader = new UploaderImpl(uploaderName);
        tags.add(Tag.News);
        headQuarter.addUploader(uploader);
        headQuarter.addAudioContent(headQuarter.getUploader("test"), 1, "encoding", 1, 1, "address", tags);
        String expected = "address";
        assertEquals(expected, headQuarter.getAddress(headQuarter.getMediaList().get(0)));
    }

    @Test
    void getTimestamp() {
        uploader = new UploaderImpl(uploaderName);
        tags.add(Tag.News);
        headQuarter.addUploader(uploader);
        headQuarter.addAudioContent(headQuarter.getUploader("test"), 1, "encoding", 1, 1, "address", tags);
        Date expected = new Date(System.currentTimeMillis());
        assertEquals(expected.toString(), headQuarter.getTimestamp(headQuarter.getMediaList().get(0)));
    }

    @Test
    void getOldestContent() {
        // could not test that because it depends on the speed of the running system
    }

    @Test
    void getOldestContentIsNotNull() {
        uploader = new UploaderImpl(uploaderName);
        tags.add(Tag.News);
        headQuarter.addUploader(uploader);
        headQuarter.addAudioContent(headQuarter.getUploader("test"), 1, "encoding", 1, 1, "address", tags);
        headQuarter.addVideoContent(headQuarter.getUploader("test"), "address", tags, 1, 1, 1, 1, "encoding");
        Content content = headQuarter.getOldestContent();
        assertNotNull(content);
    }

    @Test
    void registerUseCase() {
        assertTrue(headQuarter.register(new CapacityObserver(this.headQuarter)));
        assertEquals(2, headQuarter.getObservers().size());
    }

    @Test
    void registerNull() {
        assertFalse(headQuarter.register(null));

    }

    @Test
    void unregisterUseCase() {
        IObserver observer = new CapacityObserver(this.headQuarter);
        assertTrue(headQuarter.unregister(observer));
        assertEquals(0, headQuarter.getObservers().size());
    }

    @Test
    void unregisterNull() {
        assertFalse(headQuarter.unregister(null));
    }

    @Test
    void notifyObservers() {
        IObserver observer = Mockito.mock(CapacityObserver.class);
        headQuarter.notifyObservers();
        Mockito.doNothing().when(observer).update();

    }

    @Test
    void getUnusedTags() {
        List<Tag> expected = new ArrayList<>();
        expected.add(Tag.Animal);
        expected.add(Tag.Tutorial);
        expected.add(Tag.Lifestyle);
        uploader = new UploaderImpl(uploaderName);
        tags.add(Tag.News);
        headQuarter.addUploader(uploader);
        headQuarter.addAudioContent(headQuarter.getUploader("test"), 1, "encoding", 1, 1, "address", tags);

        assertEquals(expected.toString(), headQuarter.getUnusedTags().toString());

    }

    @Test
    void getUsedTags() {
        List<Tag> expected = new ArrayList<>();
        expected.add(Tag.News);
        uploader = new UploaderImpl(uploaderName);
        tags.add(Tag.News);
        headQuarter.addUploader(uploader);
        headQuarter.addAudioContent(headQuarter.getUploader("test"), 1, "encoding", 1, 1, "address", tags);

        assertEquals(expected.toString(), headQuarter.getUsedTags().toString());
    }

    @Test
    void getMaxSizeOfDatabase() {
        int expected = 10;
        assertEquals(expected, headQuarter.getMaxSizeOfDatabase());
    }

    @Test
    void getUploaderList() {
        uploader = new UploaderImpl(uploaderName);
        headQuarter.addUploader(uploader);
        assertTrue(headQuarter.getUploaderList().size() > 0);
        assertEquals(uploader.getName(), headQuarter.getUploaderList().get(0).getName());
    }

    @Test
    void getUploaderManager() {
        assertNotNull(headQuarter.getUploaderManager());
    }

    @Test
    void getMediaManager() {
        assertNotNull(headQuarter.getMediaManager());
    }
}