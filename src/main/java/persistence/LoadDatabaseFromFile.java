package persistence;

import management.HeadQuarter;
import mediaDB.Content;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class LoadDatabaseFromFile {
    private final String fileName = "database.ser";
    private List<Content> contentList;
//TODO: Rewrite method for testing
    public List<Content> loadWarehouse() {
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            contentList = (List<Content>) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return contentList;
    }
}
