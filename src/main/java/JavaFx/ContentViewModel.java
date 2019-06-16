package JavaFx;

import eventBasedCli.observers.IObserver;
import javafx.collections.ObservableList;
import management.MediaManager;
import mediaDB.Content;

public class ContentViewModel implements IObserver {
    private ObservableList<Content> contentList;
    private MediaManager mediaManager;

    public ContentViewModel(ObservableList<Content> contentList, MediaManager mediaManager) {
        this.mediaManager = mediaManager;
        mediaManager.registerObserver(this);
        this.contentList = contentList;
    }

    @Override
    public void update() {
        contentList.clear();
        for (int i = 0; i < mediaManager.getContentList().size(); i++) {
            if(mediaManager.getContentList().get(i) != null){
                contentList.add(mediaManager.getContentList().get(i));
            }
        }
    }
}
