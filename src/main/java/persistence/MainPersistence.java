package persistence;

import management.HeadQuarter;
import mediaDB.Tag;
import uploaderDB.UploaderImpl;

import java.util.ArrayList;
import java.util.List;

public class MainPersistence {
    public static void main(String[] args) {
        HeadQuarter headQuarter = new HeadQuarter();
        headQuarter.addUploader(new UploaderImpl("Test"));
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.News);
        headQuarter.addAudioVideoContent(headQuarter.getUploader("Test"), "address", tags, 1, 1, 1, "encoding", 1, 1);
        headQuarter.addAudioContent(headQuarter.getUploader("Test"), 1, "encoding", 1, 1, "address", tags);
        headQuarter.addVideoContent(headQuarter.getUploader("Test"),"address",tags,1,1,1,1,"encoding");
        headQuarter.addLicensedAudio(headQuarter.getUploader("Test"),"address",tags,1,1,1,"encoding","holder");
        headQuarter.addLicensedVideo(headQuarter.getUploader("Test"),"address",tags,1,1,1,1,"encoding","holder");
        headQuarter.addLicensedAudioVideo(headQuarter.getUploader("Test"),"address",tags,1,1,1,1,1,"encoding","holder");
        SaveDatabaseToFile saveDatabaseToFile = new SaveDatabaseToFile(headQuarter);
        System.out.println(saveDatabaseToFile.safeDataToTextFile("testFile.txt"));
        LoadDatabaseFromFile loadDatabaseFromFile = new LoadDatabaseFromFile();
        HeadQuarter headQuarterLoaded = new HeadQuarter();
        loadDatabaseFromFile.loadDataFromTextFile("testFile.txt");
         headQuarterLoaded.setMediaList(loadDatabaseFromFile.getContentList());
        System.out.println(headQuarterLoaded.printList());


        System.out.println("<----------- JBP -------------->");
        SaveDatabaseWithJBP saveDatabaseWithJBP = new SaveDatabaseWithJBP();
        saveDatabaseWithJBP.saveDatabase(headQuarter,"testJbp.txt");
        HeadQuarter headQuarterJbp = saveDatabaseWithJBP.loadHeadquarter("testJbp.txt");
        System.out.println(headQuarterJbp.printList());
    }

}
