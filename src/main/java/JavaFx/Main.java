package JavaFx;

import eventBasedCli.CommandLineInterface;
import eventBasedCli.EventHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import management.EventManager;
import management.HeadQuarter;

import java.io.IOException;

public class Main extends Application {
    private HeadQuarter headQuarter;
    private Stage addUploaderStage;
    private Stage addContentStage;
    private EventHandler eventHandler;
    private EventManager eventManager;

    public static void main(String[] args) {

        Main main = new Main();
        //main.launchCLI();
        launch(args);
    }

    public Main() {
        eventHandler = new EventHandler();
        eventManager = new EventManager();
        eventHandler.linkToEventManager(eventManager);
        eventManager.linkToEventHandler(eventHandler);
    }

    private void launchCLI() {
        CommandLineInterface commandLineInterface = new CommandLineInterface();
        commandLineInterface.displayCommandLineInterface();
    }


    public void showMainWindow(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("MainView.fxml"));
            AnchorPane pane = loader.load();
            MainController controller = loader.getController();
            controller.lateInit(this, headQuarter);
            stage.setScene(new Scene(pane));
            stage.setTitle("Storage");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAddUploaderWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("AddUploaderView.fxml"));
            AnchorPane pane = loader.load();
            AddUploaderController controller = loader.getController();
            controller.lateInit(this);
            addUploaderStage = new Stage();
            addUploaderStage.setResizable(false);
            addUploaderStage.setScene(new Scene(pane));
            addUploaderStage.setTitle("Add Uploader");
            addUploaderStage.initModality(Modality.APPLICATION_MODAL);
            addUploaderStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAddContentView() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("AddContentView.fxml"));
            AnchorPane pane = loader.load();
            AddContentController controller = loader.getController();
            controller.lateInit(this);
            addContentStage = new Stage();
            addContentStage.setResizable(false);
            addContentStage.setScene(new Scene(pane));
            addContentStage.setTitle("Add Content");
            addContentStage.initModality(Modality.APPLICATION_MODAL);
            addContentStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        headQuarter = eventManager.getHeadQuarter();
        showMainWindow(primaryStage);
    }

    public void closeAddUploaderWindow() {
        addUploaderStage.close();
    }

    public void closeAddContentWindow() {
        addContentStage.close();
    }

    public EventHandler getEventHandler() {
        return eventHandler;
    }

    public EventManager getEventManager() {
        return eventManager;
    }
}
