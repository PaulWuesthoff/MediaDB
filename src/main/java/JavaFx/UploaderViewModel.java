package JavaFx;

import eventBasedCli.observers.IObserver;
import javafx.collections.ObservableList;
import management.UploaderManager;
import uploaderDB.Uploader;
import uploaderDB.UploaderImpl;

import java.util.Collection;
import java.util.List;

public class UploaderViewModel implements IObserver {
    ObservableList<Uploader> uploaderObservableList;
    UploaderManager uploaderManager;

    public UploaderViewModel(ObservableList<Uploader> uploaderObservableList, UploaderManager uploaderManager) {
        this.uploaderManager = uploaderManager;
        uploaderManager.register(this);
        this.uploaderObservableList = uploaderObservableList;
    }

    @Override
    public void update() {
        uploaderObservableList.clear();
        List<Uploader> uploaderList = uploaderManager.getUploaderList();
        Collection<Uploader> uploaderCollection = null;
        uploaderObservableList.addAll( uploaderList);
    }
}
