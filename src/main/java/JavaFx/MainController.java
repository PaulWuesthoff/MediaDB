package JavaFx;


import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import management.HeadQuarter;
import mediaDB.Content;
import mediaDB.Tag;
import uploaderDB.Uploader;
import uploaderDB.UploaderImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.function.Predicate;


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
    private TableColumn dateOfUploadColumn;
    @FXML
    private TableView<Uploader> uploaderTableView;

    @FXML
    private TableColumn uploaderColumn;

    @FXML
    private Button AddUploaderViewButton;

    @FXML
    private Button deleteUploaderButton;

    @FXML
    private Button deleteContentButton;
    @FXML
    private Button addContentButton;

    @FXML
    private MenuItem menuItemLoad;

    @FXML
    private MenuItem menuItemSave;

    @FXML
    private MenuItem menuItemClose;

    @FXML
    private MenuItem menuItemShowAll;


    private HeadQuarter headQuarter;
    private Main main;

    private ObservableList<Content> observableContentList;
    private ObservableList<Uploader> observableUploaderList;
    private FilteredList<Content> filteredList;

    @FXML
    private void initialize() {

        contentColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Content,String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Content,String> param) {
                return new ObservableValueBase() {
                    @Override
                    public Object getValue() {
                        String className = param.getValue().getClass().getInterfaces()[0].toString();
                        return className.substring(className.lastIndexOf(".")+1);
                    }
                };
            }
        });
        addressColumn.setCellValueFactory(new PropertyValueFactory<Content, String>("address"));
        tagsColumn.setCellValueFactory(new PropertyValueFactory<Content, Tag>("tags"));
        widthColumn.setCellValueFactory(new PropertyValueFactory<Content, Integer>("width"));
        heightColumn.setCellValueFactory(new PropertyValueFactory<Content, Integer>("height"));
        samplingRateColumn.setCellValueFactory(new PropertyValueFactory<Content, Integer>("samplingRate"));
        bitrateColumn.setCellValueFactory(new PropertyValueFactory<Content, Integer>("bitrate"));
        encodingColumn.setCellValueFactory(new PropertyValueFactory<Content, String>("encoding"));
        lengthColumn.setCellValueFactory(new PropertyValueFactory<Content, Long>("length"));
        holderColumn.setCellValueFactory(new PropertyValueFactory<Content, String>("holder"));
        dateOfUploadColumn.setCellValueFactory(new PropertyValueFactory<Content, Date>("timestamp"));

        observableContentList = FXCollections.observableArrayList();
        uploaderColumn.setCellValueFactory(new PropertyValueFactory<Uploader, String>("name"));
        observableUploaderList = FXCollections.observableArrayList();
        uploaderTableView.setItems(observableUploaderList);

        filteredList = new FilteredList<>(observableContentList);

        mediaDBView.setItems(observableContentList);
    }

    public void lateInit(Main main, HeadQuarter headQuarter) {
        this.main = main;
        this.headQuarter = headQuarter;
        new UploaderViewModel(observableUploaderList, headQuarter.getUploaderManager());
        // sample input
        headQuarter.addUploader(new UploaderImpl("Paul"));
        headQuarter.addUploader(new UploaderImpl("Julius"));
        ArrayList<Tag> tags = new ArrayList<>();
        tags.add(Tag.News);
        new ContentViewModel(observableContentList, headQuarter.getMediaManager());
        headQuarter.addAudioContent(headQuarter.getUploader("Paul"), 3445, "ddfg", 345, 345, "sdfdfs", tags);

        uploaderTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                mediaDBView.setItems(observableContentList);
            } else {
                Predicate<Content> equalsUploader = content -> content.getUploader().equals(uploaderTableView.getSelectionModel().getSelectedItem());
                filteredList.setPredicate(equalsUploader);
                mediaDBView.setItems(filteredList);
            }
        });

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
        alert.setHeaderText("Are you want to delete the uploader " + uploader + " ?");
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
    private void onDeleteContentButton() {
        Content content = mediaDBView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(null);
        alert.setHeaderText("Are you want to delete this content for the uploader" + content.getUploader().getName() + " ?");
        alert.setContentText(null);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            alert.close();
            main.getEventHandler().sendDeleteContentEvent(content);
        } else {
            alert.close();
        }
    }

    @FXML
    private void onAddContentViewButton() {
        main.showAddContentView();
    }

    @FXML
    private void onMenuItemClose(){
        System.exit(0);
    }
    @FXML
    private void onMenuItemShowAll(){
        mediaDBView.setItems(observableContentList);
    }

}
