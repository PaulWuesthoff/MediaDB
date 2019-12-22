package eventBasedCli;

import JavaFx.MainController;
import eventBasedCli.observers.CapacityObserver;
import eventBasedCli.observers.GetNewTagsObserver;
import eventBasedCli.observers.GetRemovedTagsObserver;
import management.EventManager;
import management.HeadQuarter;


public class MainCli {
    private HeadQuarter headQuarter;
    private EventHandler eventHandler;
    private EventManager eventManager;
    public static void main(String[] args) {
        MainCli mainCli = new MainCli();
        mainCli.launchCLI();
    }

    public MainCli() {
        eventHandler = new EventHandler();
        eventManager = new EventManager();
        eventHandler.linkToEventManager(eventManager);
        eventManager.linkToEventHandler(eventHandler);
        headQuarter = eventManager.getHeadQuarter();
    }

    private void launchCLI() {
        CapacityObserver capacityObserver = new CapacityObserver(headQuarter);
        GetNewTagsObserver getNewTagsObserver = new GetNewTagsObserver(headQuarter);
        GetRemovedTagsObserver getRemovedTagsObserver = new GetRemovedTagsObserver(headQuarter);
        CommandLineInterface commandLineInterface = new CommandLineInterface(this);
        commandLineInterface.displayCommandLineInterface();
    }

    public EventHandler getEventHandler() {
        return eventHandler;
    }

    public EventManager getEventManager() {
        return eventManager;
    }
}
