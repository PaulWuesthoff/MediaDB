package management;

import eventBasedCli.observers.IObserver;
import mediaDB.Content;
import mediaDB.Tag;
import uploaderDB.Uploader;


import java.io.Serializable;
import java.util.*;


public class MediaManager implements Serializable {
    private List<Content> contentList;
    private int storageCounter;
    private final int MAXSIZE = 10;
    private UploaderManager uploaderManager;
    private Collection<IObserver> observers;


    public MediaManager() {
       uploaderManager = new UploaderManager();
        contentList = new ArrayList<>();
        observers = new LinkedList<>();
    }

    public MediaManager(List<Content> contentList, UploaderManager uploaderManager) {
        this.uploaderManager = uploaderManager;
        this.contentList = contentList;
    }
    public void registerObserver(IObserver observer){
        observers.add(observer);
    }
    private void notifyObservers(){
        for(IObserver observer : observers){
            observer.update();
        }
    }

    public boolean addContent(Content content) {
        if (checkContent(content)) {
            addContentToArray(content);
            notifyObservers();
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteContent(Content content) {
        boolean executed = contentList.remove(content);
        notifyObservers();
       return executed;
    }

    public String getTimestamp(Content content) {
        String answer = "";
        if (content == null) {
            answer += "The content you are looking for dose not exist! ";
            return answer;
        }
        answer = content.getTimestamp().toString();
        return answer;
    }

    public String getAddress(Content content) {
        String answer = "";
        if (content == null) {
            answer += "The content you are looking for dose not exist! ";
            return answer;
        }
        answer = content.getAddress();
        return answer;
    }

    private boolean addContentToArray(Content contentToAdd) {
        storageCounter++;
        return contentList.add(contentToAdd);

    }

    private boolean checkContent(Content content) {
        if (storageCounter == MAXSIZE) {
            return false;
        }
        if (content == null) {
            return false;
        }
        return true;
    }


    public String printList() {
        String contentString = "";
        int position = 0;
        for (Content content : contentList) {
            if (contentList.isEmpty()) {
                contentString += "The List is Empty";
            }
            position++;
            contentString += " " + position + ". " + content.toString();
        }
        return contentString;
    }

    public List<Content> getContentsByInterfaceType(Class c) {
        List<Content> contents = new ArrayList<>();
        for (Content content : contentList) {
            if (content.getClass().getInterfaces()[0].equals(c)) {
                contents.add(content);
            }
        }

        return contents;
    }

    public Collection<Tag> getUsedTags() {
        Collection<Tag> all = EnumSet.allOf(Tag.class);
        all.removeAll(getUnusedTags());
        return all;
    }


    public Collection<Tag> getUnusedTags() {
        Collection<Tag> all = EnumSet.allOf(Tag.class);
        for (Content content : contentList) {
            all.removeAll(content.getTags());
        }
        return all;
    }

    public int getAmountOfUploadsForOneUploader(Uploader uploader) {
        int amount = 0;
        for (Content content : contentList) {
            if (content.getUploader().equals(uploader)) {
                amount++;
            }
        }

        return amount;
    }

    public boolean deleteContentForOneUploader(Uploader uploader) {
        ArrayList<Content> contentsToDelete = new ArrayList<>();
        for (Content content : contentList) {
            if (content.getUploader().getName().equals(uploader.getName())) {
                contentsToDelete.add(content);
            }

        }
        return contentList.removeAll(contentsToDelete);

    }


    public List<Content> getContentList() {
        return contentList;
    }

    public int getMAXSIZE() {
        return MAXSIZE;
    }

}
