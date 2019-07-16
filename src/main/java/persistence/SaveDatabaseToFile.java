package persistence;

import management.HeadQuarter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveDatabaseToFile {
    private final String fileName = "database.ser";
    private HeadQuarter headQuarter;

    public SaveDatabaseToFile(HeadQuarter headQuarter) {
        this.headQuarter = headQuarter;
    }
    //TODO: Rewrite method for testing
    public boolean saveMediaDatabase(){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(headQuarter.getMediaList());
            objectOutputStream.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }
}
