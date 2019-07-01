package eventBasedCli;

import JavaFx.Main;
import eventBasedCli.observers.CapacityObserver;
import eventBasedCli.observers.GetNewTagsObserver;
import eventBasedCli.observers.GetRemovedTagsObserver;
import management.EventManager;
import mediaDB.Tag;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class CommandLineInterface {
    private InputStreamReader reader = new InputStreamReader(System.in);
    private BufferedReader inputReader = new BufferedReader(reader);
    StringTokenizer stringTokenizer;
    /*private EventManager eventManager;
    private EventHandler eventHandler;*/
    private CapacityObserver capacityObserver;
    private GetNewTagsObserver getNewTagsObserver;
    private GetRemovedTagsObserver getRemovedTagsObserver;
    private final String USERNOTE = "Notice: You can only add Content to existing Uploader's";
    private final String COULDNOTADDCONTENT = "Could not add the Content, returning to menu";
    private Main main;


    public CommandLineInterface() {
        main = new Main();
        this.capacityObserver = new CapacityObserver(main.getEventManager().getHeadQuarter());
        this.getNewTagsObserver = new GetNewTagsObserver(main.getEventManager().getHeadQuarter());
        this.getRemovedTagsObserver = new GetRemovedTagsObserver(main.getEventManager().getHeadQuarter());

    }

    public void displayCommandLineInterface() {
        System.out.println("[1] Add uploader\n" +
                "[2] Add Audio-Content\n" +
                "[3] Add Video-Content\n" +
                "[4] Add Audio-Video-Content\n" +
                "[5] Add licensed Audio-Content\n" +
                "[6] Add licensed Video-Content\n" +
                "[7] Add licensed Audio-Video-Content\n" +
                "[8] Remove uploader\n" +
                "[9] Get media data from type\n" +
                "[10] Get the address and the creation date\n" +
                "[11] Remove content from database\n" +
                "[12] Get the Amount of Data for one Uploader\n" +
                "[13] Print the Contentlist\n" +
                "[q] Quit the Application\n");
        String userInput = getUserInput();
        switch (userInput) {
            case "1":
                addUploader();
                break;
            case "2":
                addAudioContent();
                break;
            case "3":
                addVideoContent();
                break;
            case "4":
                addAudioVideoContent();
                break;
            case "5":
                addLicensedAudioContent();
                break;
            case "6":
                addLicensedVideoContent();
                break;
            case "7":
                addLicensedAudioVideoContent();
                break;
            case "8":
                deleteUploader();
                break;
            case "9":
                getMediaDataFromType();
                break;
            case "10":
                getAddressAndDate();
                break;
            case "11":
                removeContent();
                break;
            case "12":
                getAmountOfDataForOneUploader();
                break;
            case "13":
                printContentList();
                break;
            case "q":
                quitApplication();
                break;
             default:
                 displayCommandLineInterface();
                 break;
        }

    }


    private void addUploader() {
        System.out.println("Type in the Name to add");
        String userInput = getUserInput();
        main.getEventHandler().sendAddUploaderEvent(userInput);
        if (main.getEventHandler().isExecuted()) {
            System.out.println("Added the Uploader: " + userInput);
        } else {
            System.out.println("Could not add the Uploader: " + userInput);
        }
        displayCommandLineInterface();
    }

    private void deleteUploader() {
        System.out.println("Type in the Name of the Uploader you want to delete");
        String userInput = getUserInput();
        main.getEventHandler().sendDeleteUploaderEvent(userInput);

        if (main.getEventHandler().isExecuted()) {
            System.out.println("Removed the Uploader: " + userInput);
        } else {
            System.out.println("Could not remove the User, returning to menu");
        }
        displayCommandLineInterface();
    }


    private void addAudioContent() {
        System.out.println(USERNOTE);
        System.out.println("Please type in the [Uploader], [Address], [bitrate], [length], [samplingRate], [encoding], [Tags]");
        String userInput = getUserInput();
        stringTokenizer = new StringTokenizer(userInput, ",");
        ArrayList<Tag> tag = new ArrayList<>();

        String uploader = stringTokenizer.nextToken();
        String address = stringTokenizer.nextToken();
        int bitrate = Integer.parseInt(stringTokenizer.nextToken());
        long length = Long.parseLong(stringTokenizer.nextToken());
        int samplingRate = Integer.parseInt(stringTokenizer.nextToken());
        String encoding = stringTokenizer.nextToken();
        while (stringTokenizer.hasMoreElements()) {
            String tags = "";
            tags += stringTokenizer.nextToken();
            tag.add(Tag.valueOf(tags));
        }
        main.getEventHandler().sendAddAudioContent(uploader, address, tag, bitrate, length, samplingRate, encoding);
        if (main.getEventHandler().isExecuted()) {
            System.out.println("Added Audio-Content for the Uploader: " + uploader);
        } else {
            System.out.println(COULDNOTADDCONTENT);
        }
        displayCommandLineInterface();
    }

    private void addVideoContent() {
        System.out.println(USERNOTE);
        System.out.println("Please type in the [Uploader], [Address], [bitrate], [length], [width],[height] [encoding], [Tags]");
        String userInput = getUserInput();
        stringTokenizer = new StringTokenizer(userInput, ",");
        ArrayList<Tag> tag = new ArrayList<>();

        String uploader = stringTokenizer.nextToken();
        String address = stringTokenizer.nextToken();
        int bitrate = Integer.parseInt(stringTokenizer.nextToken());
        long length = Long.parseLong(stringTokenizer.nextToken());
        int width = Integer.parseInt(stringTokenizer.nextToken());
        int height = Integer.parseInt(stringTokenizer.nextToken());
        String encoding = stringTokenizer.nextToken();
        while (stringTokenizer.hasMoreElements()) {
            String tags = "";
            tags += stringTokenizer.nextToken();
            tag.add(Tag.valueOf(tags));
        }
        main.getEventHandler().sendAddVideoContent(uploader, address, tag, bitrate, length, width, height, encoding);
        if (main.getEventHandler().isExecuted()) {
            System.out.println("Added Video-Content for the Uploader: " + uploader);
        } else {
            System.out.println(COULDNOTADDCONTENT);
        }
        displayCommandLineInterface();
    }

    private void addAudioVideoContent() {
        // Multiple Tags einfach ans Ende setzten, dann kann ich einfach soviele Tags eingeben wie möglich
        System.out.println(USERNOTE);
        System.out.println("Please type in the [Uploader], [Address], [bitrate], [length], [samplingRate], [encoding], [width], [height], [Tags]");
        String userInput = getUserInput();
        stringTokenizer = new StringTokenizer(userInput, ",");
        ArrayList<Tag> tag = new ArrayList<>();

        String uploader = stringTokenizer.nextToken();
        String address = stringTokenizer.nextToken();
        int bitrate = Integer.parseInt(stringTokenizer.nextToken());
        long length = Long.parseLong(stringTokenizer.nextToken());
        int samplingRate = Integer.parseInt(stringTokenizer.nextToken());
        String encoding = stringTokenizer.nextToken();
        int width = Integer.parseInt(stringTokenizer.nextToken());
        int height = Integer.parseInt(stringTokenizer.nextToken());
        while (stringTokenizer.hasMoreElements()) {
            String tags = "";
            tags += stringTokenizer.nextToken();
            tag.add(Tag.valueOf(tags));
        }
        main.getEventHandler().sendAudioVideoContentEvent(uploader, address, tag, bitrate, length, samplingRate, encoding, width, height);
        if (main.getEventHandler().isExecuted()) {
            System.out.println("Added Audio-Video-Content for the Uploader: " + uploader);
        } else {
            System.out.println(COULDNOTADDCONTENT);
        }
        displayCommandLineInterface();
    }

    private void addLicensedAudioContent() {
        System.out.println(USERNOTE);
        System.out.println("Please type in the [Uploader], [Address], [bitrate], [length], [samplingRate], [encoding], [holder], [Tags]");
        String userInput = getUserInput();
        stringTokenizer = new StringTokenizer(userInput, ",");
        ArrayList<Tag> tag = new ArrayList<>();

        String uploader = stringTokenizer.nextToken();
        String address = stringTokenizer.nextToken();
        int bitrate = Integer.parseInt(stringTokenizer.nextToken());
        long length = Long.parseLong(stringTokenizer.nextToken());
        int samplingRate = Integer.parseInt(stringTokenizer.nextToken());
        String encoding = stringTokenizer.nextToken();
        String holder = stringTokenizer.nextToken();
        while (stringTokenizer.hasMoreElements()) {
            String tags = "";
            tags += stringTokenizer.nextToken();
            tag.add(Tag.valueOf(tags));
        }
        main.getEventHandler().sendLicensedAudioContent(uploader, address, tag, bitrate, length, samplingRate, encoding, holder);
        if (main.getEventHandler().isExecuted()) {
            System.out.println("Added licensed Audio-Content for the Uploader: " + uploader);
        } else {
            System.out.println(COULDNOTADDCONTENT);
        }
        displayCommandLineInterface();
    }

    private void addLicensedVideoContent() {
        System.out.println(USERNOTE);
        System.out.println("Please type in the [Uploader], [Address], [bitrate], [length], [width], [height], [encoding], [holder], [Tags]");
        String userInput = getUserInput();
        stringTokenizer = new StringTokenizer(userInput, ",");
        ArrayList<Tag> tag = new ArrayList<>();

        String uploader = stringTokenizer.nextToken();
        String address = stringTokenizer.nextToken();
        int bitrate = Integer.parseInt(stringTokenizer.nextToken());
        long length = Long.parseLong(stringTokenizer.nextToken());
        int width = Integer.parseInt(stringTokenizer.nextToken());
        int height = Integer.parseInt(stringTokenizer.nextToken());
        String encoding = stringTokenizer.nextToken();
        String holder = stringTokenizer.nextToken();

        while (stringTokenizer.hasMoreElements()) {
            String tags = "";
            tags += stringTokenizer.nextToken();
            tag.add(Tag.valueOf(tags));
        }
        main.getEventHandler().sendLicensedVideoContent(uploader, address, tag, bitrate, length, width, height, encoding, holder);
        if (main.getEventHandler().isExecuted()) {
            System.out.println("Added licensed Video-Content for the Uploader: " + uploader);
        } else {
            System.out.println("Could not add the Content, returning to menu !");
        }
        displayCommandLineInterface();
    }

    private void addLicensedAudioVideoContent() {
        System.out.println(USERNOTE);
        System.out.println("Please type in the [Uploader], [Address], [bitrate], [length], [samplingRate], [encoding], [width], [height],[holder],[Tags]");
        String userInput = getUserInput();
        stringTokenizer = new StringTokenizer(userInput, ",");
        ArrayList<Tag> tag = new ArrayList<>();

        String uploader = stringTokenizer.nextToken();
        String address = stringTokenizer.nextToken();
        int bitrate = Integer.parseInt(stringTokenizer.nextToken());
        long length = Long.parseLong(stringTokenizer.nextToken());
        int samplingRate = Integer.parseInt(stringTokenizer.nextToken());
        String encoding = stringTokenizer.nextToken();
        int width = Integer.parseInt(stringTokenizer.nextToken());
        int height = Integer.parseInt(stringTokenizer.nextToken());
        String holder = stringTokenizer.nextToken();

        while (stringTokenizer.hasMoreElements()) {
            String tags = "";
            tags += stringTokenizer.nextToken();
            tag.add(Tag.valueOf(tags));
        }
        main.getEventHandler().sendLicensedAudioVideoContentEvent(uploader, address, tag, bitrate, length, samplingRate, encoding, width, height, holder);
        if (main.getEventHandler().isExecuted()) {
            System.out.println("Added licensed Audio-Video-Content for the Uploader: " + uploader);
        } else {
            System.out.println(COULDNOTADDCONTENT);
        }
        displayCommandLineInterface();
    }

    private void getMediaDataFromType() {
        System.out.println("Please type in the Type of Content you ar looking for !");
        String userInput = getUserInput();
        main.getEventHandler().sendGetContentByNameEvent(userInput);
        List<?> contentList;
        contentList = main.getEventHandler().getContentList();
        System.out.println(Arrays.toString(contentList.toArray()));
        displayCommandLineInterface();
    }

    private void getAddressAndDate() {
        main.getEventHandler().sendRequestPrintListEvent();
        System.out.println(main.getEventHandler().getStringContentList());
        System.out.println("Please type in the position of the content for checking the address and date");
        String userInput = getUserInput();
        main.getEventHandler().sendGetAddressAndDateEvent(Integer.parseInt(userInput));
        String addressAndDate = main.getEventHandler().getAddressAndDate();
        System.out.println(addressAndDate);
        displayCommandLineInterface();

    }

    private void removeContent() {
        main.getEventHandler().sendRequestPrintListEvent();
        System.out.println(main.getEventHandler().getStringContentList());
        System.out.println("Please type in the position of the content you want to delete");
        String userInput = getUserInput();
        main.getEventHandler().sendDeleteContentEvent(Integer.parseInt(userInput));
        if (main.getEventHandler().isExecuted()) {
            System.out.println("Removed the content on position: " + userInput);
            displayCommandLineInterface();
        } else {
            System.out.println("Could not remove content.. returning to main menu");
            displayCommandLineInterface();
        }


    }

    private void getAmountOfDataForOneUploader() {
        System.out.println("Please give me the Name of the User !");
        String userInput = getUserInput();
        main.getEventHandler().sendRequestAmountOfUploadsEvent(userInput);
        int amount = main.getEventHandler().getAmountOfUploads();
        System.out.println("The User " + userInput + " got " + amount + " upload[s].");
        displayCommandLineInterface();

    }

    private void printContentList() {
        main.getEventHandler().sendRequestPrintListEvent();
        System.out.println(main.getEventHandler().getStringContentList());
        displayCommandLineInterface();

    }

    private void quitApplication() {
        System.exit(0);
    }

    private String getUserInput() {
        String input = "";
        try {
            input = inputReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }
}
