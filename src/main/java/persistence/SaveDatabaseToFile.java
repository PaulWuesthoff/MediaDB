package persistence;

import management.HeadQuarter;

import java.io.*;

public class SaveDatabaseToFile {
    private HeadQuarter headQuarter;

    public SaveDatabaseToFile(HeadQuarter headQuarter) {
        this.headQuarter = headQuarter;
    }

    public boolean safeDataToTextFile(String fileName) {
     FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(fileName);
            safeData(outputStream);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void safeData(FileOutputStream fileOutputStream) {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(headQuarter.getMediaList());
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
