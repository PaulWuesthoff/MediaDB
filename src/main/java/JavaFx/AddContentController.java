package JavaFx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import management.HeadQuarter;
import javafx.fxml.FXML;
import uploaderDB.Uploader;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class AddContentController {
    @FXML
    private TextField widthTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private ChoiceBox<String> contentTypeChoiceBox;

    @FXML
    private TextField lengthTextField;

    @FXML
    private CheckBox lifestyleTagCheckBox;

    @FXML
    private TextField samplingrateTextField;

    @FXML
    private CheckBox tutorialTagCheckBox;

    @FXML
    private Button cancelAddContentButton;

    @FXML
    private CheckBox animalTagCheckBox;

    @FXML
    private TextField bitrateTextField;

    @FXML
    private ChoiceBox<Uploader> uploaderChoiceBox;

    @FXML
    private CheckBox newsTagCheckBox;

    @FXML
    private TextField heightTextField;

    @FXML
    private TextField encodingTextField;

    @FXML
    private TextField holderTextField;

    @FXML
    private Button addContentButton;

    private Main main;
    private ObservableList<Uploader> uploaderList = FXCollections.observableArrayList();

    public void lateInit(Main main) {
        this.main = main;
        contentTypeChoiceBox.setItems(FXCollections.observableArrayList(
                "1.Audio-content",
                "2. Video-content",
                "3. Audio-Video-content",
                "4. licensed Audio-content",
                "5. licensed Video-content",
                "6. licensed Audio-Video-content"
        ));
        main.getEventHandler().sendRequestUploaderListEvent();
        for (Uploader uploader : main.getEventHandler().getUploaderList()) {
            uploaderList.add(uploader);
        }
        uploaderChoiceBox.setItems(uploaderList);
    }

    @FXML
    private void onCancelButtonClick() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(null);
        alert.setHeaderText("Are you sure that you want to cancel this process? ");
        alert.setContentText(null);

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            alert.close();
            main.closeAddContentWindow();
        }else{

        }

    }
}
