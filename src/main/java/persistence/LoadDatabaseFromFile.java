package persistence;

import management.HeadQuarter;
import mediaDB.Content;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LoadDatabaseFromFile {
    private List<Content> contentList;

    public boolean loadDataFromTextFile(String fileName) {
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            loadData(objectInputStream);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;

    }

    public void loadData(ObjectInputStream objectInputStream) {
        try {

            contentList = (List<Content>) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Content> getContentList() {
        return contentList;
    }
}
