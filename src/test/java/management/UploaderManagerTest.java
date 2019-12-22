package management;

import eventBasedCli.observers.CapacityObserver;
import eventBasedCli.observers.IObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uploaderDB.Uploader;
import uploaderDB.UploaderImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UploaderManagerTest {
    private UploaderManager uploaderManager;
    private final String uploaderName = "Test";
    private Uploader uploader;

    @BeforeEach
    void init() {
        uploaderManager = new UploaderManager();
        uploader = new UploaderImpl(uploaderName);
    }

    @Test
    void register() {
        IObserver observer = new CapacityObserver(Mockito.mock(HeadQuarter.class));
        assertTrue(uploaderManager.register(observer));
    }

    @Test
    void addUploaderUseCase() {
        assertTrue(uploaderManager.addUploader(uploader));
    }
    @Test
    void addUploaderNull() {
        assertFalse(uploaderManager.addUploader(null));
    }
    @Test
    void addUploaderNameIsEmpty() {
        uploader = new UploaderImpl("");
        assertFalse(uploaderManager.addUploader(uploader));
    }
    @Test
    void addUploaderNameIsNull() {
        uploader = new UploaderImpl(null);
        assertFalse(uploaderManager.addUploader(uploader));
    }
    @Test
    void addUploaderNameWrongCharacters() {
        uploader = new UploaderImpl("323!!");
        assertFalse(uploaderManager.addUploader(uploader));
    }
    @Test
    void addUploaderNameIsAlreadyAssigned() {
        Uploader uploaderToTest = new UploaderImpl("Test");
        uploaderManager.addUploader(uploaderToTest);
        assertFalse(uploaderManager.addUploader(uploader));
    }

    @Test
    void deleteUploaderUseCase() {
        uploaderManager.addUploader(uploader);
        assertTrue(uploaderManager.deleteUploader(uploader));
    }
    @Test
    void deleteUploaderIsNull() {
        assertFalse(uploaderManager.deleteUploader(null));
    }
    @Test
    void deleteUploaderIsNotInList() {
        Uploader uploaderToCheck = new UploaderImpl("NotInTheList");
        assertFalse(uploaderManager.deleteUploader(uploaderToCheck));
    }

    @Test
    void getUploaderList() {
        Uploader testUploader = new UploaderImpl("Test");
        List<Uploader> testList = new ArrayList<>();
        testList.add(testUploader);
        uploaderManager.addUploader(uploader);
        assertTrue(uploaderManager.getUploaderList().size() > 0);
        assertEquals(testList.toString(),uploaderManager.getUploaderList().toString());
    }


    @Test
    void getUploader() {
        uploaderManager.addUploader(uploader);
        assertNotNull(uploaderManager.getUploader("Test"));
        assertEquals(uploader.getName(),uploaderManager.getUploader("Test").getName());
    }
    @Test
    void getUploaderNotList() {
     assertNull(uploaderManager.getUploader("test"));
    }
    @Test
    void getUploaderIsNull() {
       assertThrows(NullPointerException.class,()-> uploaderManager.getUploader(null));
    }
}