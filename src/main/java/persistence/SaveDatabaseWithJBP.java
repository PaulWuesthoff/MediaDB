package persistence;

import management.HeadQuarter;
import management.MediaManager;
import management.UploaderManager;
import mediaDB.*;
import uploaderDB.UploaderImpl;

import java.beans.DefaultPersistenceDelegate;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;

public class SaveDatabaseWithJBP {

    // make sure all getters are there !

    public static void saveDatabase(HeadQuarter headQuarter, String filepath) {
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filepath)))) {
            encoder.setPersistenceDelegate(HeadQuarter.class, new DefaultPersistenceDelegate(
                    new String[]{"mediaManager", "uploaderManager"}
                    ));
            encoder.setPersistenceDelegate(MediaManager.class, new DefaultPersistenceDelegate(
                    new String[]{"uploaderManager","contentList"}
            ));
            encoder.setPersistenceDelegate(UploaderManager.class, new DefaultPersistenceDelegate(
                    new String[]{"uploaderList"}
            ));
            encoder.setPersistenceDelegate(UploaderImpl.class, new DefaultPersistenceDelegate(
                    new String[]{"name"}
            ));
            encoder.setPersistenceDelegate(AudioImpl.class, new DefaultPersistenceDelegate(
                    new String[]{"uploader", "address", "tags", "bitrate", "length", "samplingRate", "encoding"}
            ));
            encoder.setPersistenceDelegate(VideoImpl.class, new DefaultPersistenceDelegate(
                    new String[]{"uploader", "address", "tags", "bitrate", "length", "width", "height", "encoding"}
            ));
            encoder.setPersistenceDelegate(AudioVideoImpl.class, new DefaultPersistenceDelegate(
                    new String[]{"uploader", "address", "tags", "bitrate", "length", "samplingRate", "encoding", "width", "height"}
            ));
            encoder.setPersistenceDelegate(LicensedAudioImpl.class, new DefaultPersistenceDelegate(
                    new String[]{"uploader", "address", "tags", "bitrate", "length", "samplingRate", "encoding", "holder"}
            ));
            encoder.setPersistenceDelegate(LicensedVideoImpl.class, new DefaultPersistenceDelegate(
                    new String[]{"uploader", "address", "tags", "bitrate", "length", "width", "height", "encoding", "holder"}
            ));
            encoder.setPersistenceDelegate(LicensedAudioVideoImpl.class, new DefaultPersistenceDelegate(
                    new String[]{"uploader", "address", "tags", "bitrate", "length", "samplingRate", "encoding", "width", "height", "holder"}
            ));
            encoder.writeObject(headQuarter);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static HeadQuarter loadHeadquarter(String filePath) {
        HeadQuarter headQuarter = null;
        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(filePath)))) {
            headQuarter = (HeadQuarter) decoder.readObject();
        } catch (FileNotFoundException e) {
        }
        return headQuarter;
    }

}
