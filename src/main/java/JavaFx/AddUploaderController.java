package JavaFx;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class AddUploaderController {
    @FXML
    private Button addUploaderButton;

    @FXML
    private Button cancelAddUploaderButton;

    @FXML
    private TextField addUploaderTextfield;

    private Main main;

// do i need a hq here ? I dont think so !

    public void lateInit(Main main) {
        this.main = main;
    }

    @FXML
    private void onAddCustomerButtonClick() {
        if (addUploaderTextfield.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Non valid input!");
            alert.setContentText("Please check if you have inserted something !");
            alert.showAndWait();
        } else {
            main.getEventHandler().sendAddUploaderEvent(addUploaderTextfield.getText());
            main.closeAddUploaderWindow();
        }
    }
    @FXML
    private void onCancelAddUploaderButton(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(null);
        alert.setHeaderText("Are you sure that you want to cancel this process? ");
        alert.setContentText(null);

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            alert.close();
            main.closeAddUploaderWindow();
        }else{

        }

    }
}
