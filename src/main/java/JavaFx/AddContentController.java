package JavaFx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

import javafx.fxml.FXML;
import mediaDB.Tag;
import uploaderDB.Uploader;


import java.util.ArrayList;
import java.util.Collection;
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
    private ArrayList<Tag> tags;

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
        contentTypeChoiceBox.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                onCategorySelected();
            }
        });
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
        if (result.get() == ButtonType.OK) {
            alert.close();
            main.closeAddContentWindow();
        } else {

        }

    }

    @FXML
    private void onAddContentButtonClick() {
        tags = new ArrayList<>();

        if (animalTagCheckBox.isSelected()) {
            tags.add(Tag.Animal);
        }
        if (lifestyleTagCheckBox.isSelected()) {
            tags.add(Tag.Lifestyle);
        }
        if (tutorialTagCheckBox.isSelected()) {
            tags.add(Tag.Tutorial);
        }
        if (newsTagCheckBox.isSelected()) {
            tags.add(Tag.News);
        }

        switch (contentTypeChoiceBox.getValue()) {
            case "1.Audio-content": {
                main.getEventHandler().sendAddAudioContent(uploaderChoiceBox.getValue().toString(), addressTextField.getText(),
                        tags, Integer.parseInt(bitrateTextField.getText()), Long.parseLong(lengthTextField.getText()),
                        Integer.parseInt(samplingrateTextField.getText()), encodingTextField.getText());
                break;
            }
            case "2. Video-content": {
                main.getEventHandler().sendAddVideoContent(uploaderChoiceBox.getValue().toString(), addressTextField.getText(),
                        tags, Integer.parseInt(bitrateTextField.getText()), Long.parseLong(lengthTextField.getText()),
                        Integer.parseInt(widthTextField.getText()), Integer.parseInt(heightTextField.getText()),
                        encodingTextField.getText());
                break;
            }
            case "3. Audio-Video-content": {
                main.getEventHandler().sendAudioVideoContentEvent(uploaderChoiceBox.getValue().toString(), addressTextField.getText(),
                        tags, Integer.parseInt(bitrateTextField.getText()), Long.parseLong(lengthTextField.getText()),
                        Integer.parseInt(samplingrateTextField.getText()), encodingTextField.getText(),
                        Integer.parseInt(widthTextField.getText()), Integer.parseInt(heightTextField.getText()));
                break;
            }
            case "4. licensed Audio-content": {
                main.getEventHandler().sendLicensedAudioContent(uploaderChoiceBox.getValue().toString(), addressTextField.getText(),
                        tags, Integer.parseInt(bitrateTextField.getText()), Long.parseLong(lengthTextField.getText()),
                        Integer.parseInt(samplingrateTextField.getText()), encodingTextField.getText(), holderTextField.getText());
                break;
            }
            case "5. licensed Video-content": {
                main.getEventHandler().sendLicensedVideoContent(uploaderChoiceBox.getValue().toString(),addressTextField.getText(),
                        tags,Integer.parseInt(bitrateTextField.getText()),Long.parseLong(lengthTextField.getText()),
                        Integer.parseInt(widthTextField.getText()),Integer.parseInt(heightTextField.getText()),
                        encodingTextField.getText(),holderTextField.getText());
                break;
            }
            case "6. licensed Audio-Video-content": {
               main.getEventHandler().sendLicensedAudioVideoContentEvent(uploaderChoiceBox.getValue().toString(),
                       addressTextField.getText(),tags,Integer.parseInt(bitrateTextField.getText()),
                       Long.parseLong(lengthTextField.getText()),Integer.parseInt(samplingrateTextField.getText()),
                       encodingTextField.getText(),Integer.parseInt(widthTextField.getText()),Integer.parseInt(heightTextField.getText()),
                       holderTextField.getText());
                break;
            }
        }

    }

    @FXML
    private void onCategorySelected() {
        switch (contentTypeChoiceBox.getValue()) {

            case "1.Audio-content": {
                setVisibility(true, true, true, true, true, false, false, false);
                break;
            }
            case "2. Video-content": {
                setVisibility(true, true, true, false, true, true, true, false);
                break;
            }
            case "3. Audio-Video-content": {
                setVisibility(true, true, true, true, true, true, true, false);
                break;
            }
            case "4. licensed Audio-content": {
                setVisibility(true, true, true, true, true, false, false, true);
                break;
            }
            case "5. licensed Video-content": {
                setVisibility(true, true, true, false, true, true, true, true);
                break;
            }
            case "6. licensed Audio-Video-content": {
                setVisibility(true, true, true, true, true, true, true, true);
                break;
            }

        }
    }

    private void setVisibility(boolean address, boolean bitrate, boolean length, boolean samplingRate, boolean encoding, boolean width, boolean height, boolean holder) {
        addressTextField.setVisible(address);
        bitrateTextField.setVisible(bitrate);
        lengthTextField.setVisible(length);
        samplingrateTextField.setVisible(samplingRate);
        encodingTextField.setVisible(encoding);
        widthTextField.setVisible(width);
        heightTextField.setVisible(height);
        holderTextField.setVisible(holder);

    }
}
