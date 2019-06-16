package JavaFx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import management.HeadQuarter;
import mediaDB.Content;
import mediaDB.Tag;
import sun.rmi.rmic.iiop.InterfaceType;
import uploaderDB.Uploader;
import uploaderDB.UploaderImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class MainController {

    @FXML
    private TableColumn addressColumn;

    @FXML
    private TableColumn tagsColumn;

    @FXML
    private TableView<Content> mediaDBView;

    @FXML
    private TableColumn widthColumn;

    @FXML
    private TableColumn heightColumn;

    @FXML
    private TableColumn samplingRateColumn;

    @FXML
    private TableColumn bitrateColumn;

    @FXML
    private TableColumn contentColumn;

    @FXML
    private TableColumn encodingColumn;

    @FXML
    private TableColumn lengthColumn;

    @FXML
    private TableColumn holderColumn;

    @FXML
    private TableView<Uploader> uploaderTableView;

    @FXML
    private TableColumn uploaderColumn;

    @FXML
    private Button AddUploaderViewButton;

    @FXML
    private Button deleteUploaderButton;

    private HeadQuarter headQuarter;
    private Main main;

    private ObservableList<Content> observableContentList;
    private ObservableList<Uploader> observableUploaderList;

    @FXML
    private void initialize() {
        addressColumn.setCellValueFactory(new PropertyValueFactory<Content, String>("address"));
        tagsColumn.setCellValueFactory(new PropertyValueFactory<Content, Enum>("tag"));
        widthColumn.setCellValueFactory(new PropertyValueFactory<Content, Integer>("width"));
        heightColumn.setCellValueFactory(new PropertyValueFactory<Content, Integer>("height"));
        samplingRateColumn.setCellValueFactory(new PropertyValueFactory<Content, Integer>("samplingRate"));
        bitrateColumn.setCellValueFactory(new PropertyValueFactory<Content, Integer>("bitrate"));
        encodingColumn.setCellValueFactory(new PropertyValueFactory<Content, String>("encoding"));
        lengthColumn.setCellValueFactory(new PropertyValueFactory<Content, Long>("length"));
        holderColumn.setCellValueFactory(new PropertyValueFactory<Content, String>("holder"));


        observableContentList = FXCollections.observableArrayList();
        mediaDBView.setItems(observableContentList);
        uploaderColumn.setCellValueFactory(new PropertyValueFactory<Uploader, String>("name"));
        observableUploaderList = FXCollections.observableArrayList();
        uploaderTableView.setItems(observableUploaderList);

    }

    public void lateInit(Main main, HeadQuarter headQuarter) {
        this.main = main;
        this.headQuarter = headQuarter;
        new UploaderViewModel(observableUploaderList, headQuarter.getUploaderManager());
        headQuarter.addUploader(new UploaderImpl("Paul"));
        headQuarter.addUploader(new UploaderImpl("Julius"));
        ArrayList<Tag> tags = new ArrayList<>();
        new ContentViewModel(observableContentList, headQuarter.getMediaManager());
        headQuarter.addAudioContent(headQuarter.getUploader("Julius"), 3445, "ddfg", 345, 345, "sdfdfs", tags);

    }

    @FXML
    private void onAddUploader() {
        main.showAddUploaderWindow();
    }

    @FXML
    private void onDeleteUploaderButton() {
        String uploader = uploaderTableView.getSelectionModel().getSelectedItem().toString();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(null);
        alert.setHeaderText("Are you want to delete the uploader" + uploader + " ?");
        alert.setContentText(null);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            alert.close();
            main.getEventHandler().sendDeleteUploaderEvent(uploader);
        } else {
            alert.close();
        }
    }

    @FXML
    private void onAddContentViewButton(){
        main.showAddContentView();
    }

}
