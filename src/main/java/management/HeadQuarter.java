package management;

import eventBasedCli.observers.IObservable;
import eventBasedCli.observers.IObserver;
import mediaDB.*;

import uploaderDB.Uploader;
import uploaderDB.UploaderImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class HeadQuarter implements IObservable {
    public MediaManager mediaManager;
    public UploaderManager uploaderManager;

    private List<IObserver> observers;


    public HeadQuarter() {
        this.mediaManager = new MediaManager();
        this.uploaderManager = new UploaderManager();
        this.observers = new ArrayList<>();


    }

    public HeadQuarter(MediaManager mediaManager, UploaderManager uploaderManager) {
        this.mediaManager = mediaManager;
        this.uploaderManager = uploaderManager;

    }

    public List<Content> getMediaList() {
        return mediaManager.getContentList();
    }

    public boolean deleteUploader(Uploader uploader) {
        if(mediaManager.getAmountOfUploadsForOneUploader(uploader) == 0){
            return uploaderManager.deleteUploader(uploader);
        }
      else {
          notifyObservers();
          return uploaderManager.deleteUploader(uploader) && mediaManager.deleteContentForOneUploader(uploader);
        }
    }

    private boolean checkIfUploaderAlreadyExist(Uploader uploader) {
        return uploaderManager.getUploaderList().contains(uploader);
    }


    public boolean deleteContent(Content content) {
        notifyObservers();
        return mediaManager.deleteContent(content);
    }

    public boolean addUploader(Uploader uploader) {
        return uploaderManager.addUploader(uploader);
    }
    public boolean addMediaContent(Content content){
        return mediaManager.addContent(content);
    }

    public boolean addAudioContent(Uploader uploader, int samplingRate, String encoding, int bitrate, long length,
                                   String address, Collection<Tag> tags) {

        if (checkIfUploaderAlreadyExist(uploader)) {
            AudioImpl audioImp = new AudioImpl(uploader, address, tags, bitrate, length, samplingRate, encoding);
            return mediaManager.addContent(audioImp);
        }
        if (!uploaderManager.addUploader(uploader)) {
            return false;
        }

        AudioImpl audioImp = new AudioImpl(uploader, address, tags, bitrate, length, samplingRate, encoding);
        return mediaManager.addContent(audioImp);
    }

    public boolean addVideoContent(Uploader uploader, String address, Collection<Tag> tags,
                                   int bitrate, long length, int width, int height, String encoding) {

        if (checkIfUploaderAlreadyExist(uploader)) {
            VideoImpl videoImp = new VideoImpl(uploader, address, tags, bitrate, length, width, height, encoding);
            notifyObservers();
            return mediaManager.addContent(videoImp);
        }
        if (!uploaderManager.addUploader(uploader)) {
            return false;
        }

        VideoImpl videoImp = new VideoImpl(uploader, address, tags, bitrate, length, width, height, encoding);
        return mediaManager.addContent(videoImp);
    }

    public Uploader getUploader(String name) {
        return uploaderManager.getUploader(name);
    }

    public boolean addAudioVideoContent(Uploader uploader, String address, Collection<Tag> tags, int bitrate, long length,
                                        int samplingRate, String encoding, int width, int height) {

        if (checkIfUploaderAlreadyExist(uploader)) {
            AudioVideoImpl audioVideoImpl = new AudioVideoImpl(uploader, address, tags,
                    bitrate, length, samplingRate, encoding, width, height);
            notifyObservers();
            return mediaManager.addContent(audioVideoImpl);
        }

        if (!uploaderManager.addUploader(uploader)) {
            return false;
        }

        AudioVideoImpl audioVideoImpl = new AudioVideoImpl(uploader, address, tags,
                bitrate, length, samplingRate, encoding, width, height);
        return mediaManager.addContent(audioVideoImpl);
    }

    public boolean addLicensedAudio(Uploader uploader, String address, Collection<Tag> tags, int bitrate, long length,
                                    int samplingRate, String encoding, String holder) {


        if (checkIfUploaderAlreadyExist(uploader)) {
            LicensedAudioImpl licensedAudioImp = new LicensedAudioImpl(uploader, address, tags,
                    bitrate, length, samplingRate, encoding, holder);
            notifyObservers();
            return mediaManager.addContent(licensedAudioImp);
        }

        if (!uploaderManager.addUploader(uploader)) {
            return false;
        }

        LicensedAudioImpl licensedAudioImp = new LicensedAudioImpl(uploader, address, tags,
                bitrate, length, samplingRate, encoding, holder);
        return mediaManager.addContent(licensedAudioImp);
    }

    public boolean addLicensedVideo(Uploader uploader, String address, Collection<Tag> tags, int bitrate, long length,
                                    int width, int height, String encoding, String holder) {

        if (checkIfUploaderAlreadyExist(uploader)) {
            LicensedVideoImpl licensedVideoImp = new LicensedVideoImpl(uploader, address, tags,
                    bitrate, length, width, height, encoding, holder);
            notifyObservers();
            return mediaManager.addContent(licensedVideoImp);
        }

        if (!uploaderManager.addUploader(uploader)) {
            return false;
        }

        LicensedVideoImpl licensedVideoImp = new LicensedVideoImpl(uploader, address, tags,
                bitrate, length, width, height, encoding, holder);
        return mediaManager.addContent(licensedVideoImp);
    }

    public boolean addLicensedAudioVideo(Uploader uploader, String address, Collection<Tag> tags, int bitrate, long length,
                                         int samplingRate, int width, int height, String encoding, String holder) {

        if (checkIfUploaderAlreadyExist(uploader)) {
            LicensedAudioVideoImpl licensedAudioVideoImp = new LicensedAudioVideoImpl(uploader, address, tags,
                    bitrate, length, samplingRate, encoding, width, height, holder);
            notifyObservers();
            return mediaManager.addContent(licensedAudioVideoImp);
        }

        if (!uploaderManager.addUploader(uploader)) {
            return false;
        }

        LicensedAudioVideoImpl licensedAudioVideoImp = new LicensedAudioVideoImpl(uploader, address, tags,
                bitrate, length, samplingRate, encoding, width, height, holder);
        return mediaManager.addContent(licensedAudioVideoImp);
    }

    public List<Content> getContentByName(Class c){
        return mediaManager.getContentsByInterfaceType(c);
    }
    public int getAmountOfUploadsForOneUploader(Uploader uploader){
        return mediaManager.getAmountOfUploadsForOneUploader(uploader);
    }

    public String printList() {
        return mediaManager.printList();
    }

    public String getAddress(Content content){
        return mediaManager.getAddress(content);
    }
    public String getTimestamp (Content content){
        return mediaManager.getTimestamp(content);
    }

    public Content getOldestContent(){
        for(Content content : mediaManager.getContentList()){
            for(int i = 0; i<mediaManager.getContentList().size(); i++){
                if(content.getTimestamp().compareTo(mediaManager.getContentList().get(i).getTimestamp())<0 ){
                    return content;
                }
            }
        }
        return null;
    }




    @Override
    public boolean register(IObserver observer) {
        if (observer != null) {
            return this.observers.add(observer);
        }
        return false;
    }

    @Override
    public boolean unregister(IObserver observer) {
        return this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (IObserver observer : observers) {
            observer.update();
        }
    }

    public Collection<Tag> getUnusedTags() {
        return mediaManager.getUnusedTags();
    }

    public Collection<Tag> getUsedTags() {
        return mediaManager.getUsedTags();
    }

    public int getMaxSizeOfDatabase() {
        return mediaManager.getMAXSIZE();
    }

    public List<Uploader> getUploaderList() {
        return uploaderManager.getUploaderList();
    }
    public UploaderManager getUploaderManager(){
        return uploaderManager;
    }

    public MediaManager getMediaManager() {
        return mediaManager;
    }

    public static void main(String[] args) {
        HeadQuarter headQuarter = new HeadQuarter();
        ArrayList<Tag> tags = new ArrayList<>();
        tags.add(Tag.News);
//        ArrayList<Tag> hd = new ArrayList<>();
//        hd.add(Tag.Animal);
        System.out.println(headQuarter.addUploader(new UploaderImpl("Paul")));
//        System.out.println(headQuarter.deleteUploader(headQuarter.getUploader("Paul")));
          System.out.println(headQuarter.addAudioContent(headQuarter.getUploader("Paul"), 34, "MP8", 34674, 98854, "Database.xx", tags));
        headQuarter.addAudioVideoContent(headQuarter.getUploader("Paul"), "Jojo", tags, 34, 343, 23, "lol", 2, 4);
//        headQuarter.addLicensedAudio(headQuarter.getUploader("Paul"), "Hah", tags, 32, 4232, 534, "Encoding", "Me");
//        headQuarter.addLicensedAudioVideo(headQuarter.getUploader("Paul"), "Ja", hd, 23, 43453, 2, 4, 3, "sdf", "You");
//        headQuarter.addLicensedVideo(headQuarter.getUploader("Paul"), "oxuc", tags, 23, 23, 45, 654, "Ead", "wasd");
//        headQuarter.addVideoContent(headQuarter.getUploader("Paul"), "aefh", tags, 234234, 234324, 23, 23, "Rough");
//          System.out.println(headQuarter.mediaManager.getAmountOfUploadsForOneUploader(headQuarter.getUploader("Paul")));

        System.out.println(headQuarter.printList());
//        System.out.println(headQuarter.deleteUploader(HeadQuarter.uploaderManager.getUploader("Paul")));
//        HeadQuarter.mediaManager.printList();
//        System.out.println(HeadQuarter.mediaManager.getTimestamp(HeadQuarter.mediaManager.getContentList().get(0)));
//        System.out.println(HeadQuarter.mediaManager.getAddress(HeadQuarter.mediaManager.getContentList().get(0)));
//        System.out.println(HeadQuarter.mediaManager.deleteContent(HeadQuarter.mediaManager.getContentList().get(0)));
//        System.out.println(headQuarter.mediaManager.getContentsByInterfaceType(Audio.class));
//        System.out.println(headQuarter.mediaManager.getUsedTags());
//        System.out.println("<--------------------------------->");
//        System.out.println(HeadQuarter.mediaManager.getUnusedTags());
//        System.out.println(HeadQuarter.mediaManager.getAmountOfUploadsForOneUploader(HeadQuarter.uploaderManager.getUploader("Paul")));
//        System.out.println("Saving....");
//         saveDatabaseToFile.saveMediaDatabase();
//        System.out.println("loading...");
//        loadDatabaseFromFile.loadWarehouse();
//        SaveDatabaseWithJBP.saveDatabase(headQuarter,"hq.xml");

    }


}
