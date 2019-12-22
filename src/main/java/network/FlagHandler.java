package network;

import eventBasedCli.EventHandler;
import management.EventManager;
import mediaDB.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class FlagHandler {
    private EventHandler eventHandler;
    private EventManager eventManager;


    private StringTokenizer stringTokenizer;

    public FlagHandler() {
        eventHandler = new EventHandler();
        eventManager = new EventManager();
        eventManager.linkToEventHandler(eventHandler);
        eventHandler.linkToEventManager(eventManager);
    }

    public String handleFlag(String flag) {
        String toClient = "";
        switch (flag) {
            case "1": {
                toClient = "Name:";
                break;
            }
            case "2": {
                toClient = "Please type in the [Uploader], [Address], [bitrate], [length], [samplingRate], [encoding], [Tags]";
                break;
            }
            case "3": {
                toClient = "Please type in the [Uploader], [Address], [bitrate], [length], [width],[height] [encoding], [Tags]";
                break;
            }
            case "4": {
                toClient = "Please type in the [Uploader], [Address], [bitrate], [length], [samplingRate], [encoding], [width], [height], [Tags]";
                break;
            }
            case "5": {
                toClient = "Please type in the [Uploader], [Address], [bitrate], [length], [samplingRate], [encoding], [holder], [Tags]";
                break;
            }
            case "6": {
                toClient = "Please type in the [Uploader], [Address], [bitrate], [length], [width], [height], [encoding], [holder], [Tags]";
                break;
            }
            case "7": {
                toClient = "Please type in the [Uploader], [Address], [bitrate], [length], [samplingRate], [encoding], [width], [height],[holder],[Tags]";
                break;
            }
            case "8": {
                toClient = "Type in the Name of the Uploader you want to delete";
                break;
            }
            case "9": {
                toClient = "Please type in the Type of Content you ar looking for !";
                break;
            }
            case "10": {
                toClient = "Please type in the position of the content for checking the address and date";
                break;
            }
            case "11": {
                toClient = "Please type in the position of the content you want to delete";
                break;
            }
            case "12": {
                toClient = "Please give me the Name of the User !";
                break;
            }
            case "13": {
                toClient = "<-------    Contentlist    ------->";
                break;
            }
            default:
                toClient = "error";
        }
        return toClient;
    }

    public String setNewFlags(String flag) {
        switch (flag) {
            case "1": {
                return "uploader: ";
            }
            case "2": {
                return "audio: ";
            }
            case "3": {
                return "video: ";
            }
            case "4": {
                return "audioVideo: ";
            }
            case "5": {
                return "lAudio: ";
            }
            case "6": {
                return "lVideo: ";
            }
            case "7": {
                return "lAudioVideo: ";
            }
            case "8": {
                return "delUploader: ";
            }
            case "9": {
                return "dataFromType: ";
            }
            case "10": {
                return "addressAndDate: ";
            }
            case "11": {
                return "delContent: ";
            }
            case "12": {
                return "amountOneUploader:";
            }
            case "13": {
                return "print: ";
            }
            default: {
                // errorflag ?
                return "error";
            }
        }
    }

    public String handleUserInteraction(String request) {

        if (request.startsWith("uploader: ")) {
            return addUploader( request.substring(10));
        } else if (request.startsWith("audio: ")) {
            return addUAudioContent(request.substring(7));
        } else if (request.startsWith("video: ")) {
            return addVideoContent(request.substring(7));
        } else if (request.startsWith("audioVideo: ")) {
            return addAudioVideoContent(request.substring(12));
        } else if (request.startsWith("lAudio: ")) {
            return addLicensedAudioContent(request.substring(8));
        } else if (request.startsWith("lVideo: ")) {
            return addLicensedVideoContent(request.substring(8));
        } else if (request.startsWith("lAudioVideo: ")) {
            return addLicensedAudioVideoContent(request.substring(13));
        } else if (request.startsWith("delUploader: ")) {
            return deleteUploader(request.substring(13));
        } else if (request.startsWith("dataFromType: ")) {
            return getContentForOneSpecificType(request.substring(14));
        } else if (request.startsWith("addressAndDate: ")) {
            return getDateAndAddressForOneUploader(request.substring(16));
        } else if (request.startsWith("delContent: ")) {
            return deleteContent(request.substring(12));
        } else if (request.startsWith("amountOneUploader: ")) {
            return getAmountOfDataForOneUploader(request.substring(19));
        } else if (request.startsWith("print: ")) {
            return printContentList();
        }
        //errorflag  ?
        return "error";
    }
    private String addUploader(String name) {
        System.out.println(name);
        eventHandler.sendAddUploaderEvent(name);
        if (eventHandler.isExecuted()) {
            return "added the uploader: " + name;
        } else {
            return "could not add the uploader!";
        }
    }
    private String addUAudioContent(String userInput) {
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
        eventHandler.sendAddAudioContent(uploader, address, tag, bitrate, length, samplingRate, encoding);
        if (eventHandler.isExecuted()) {
            return "added Audio-Content for the uploader: " + uploader;
        } else {
            return "could not add Audio-Content for the uploader: " + uploader;
        }
    }
    private String addVideoContent(String userInput) {
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
        eventHandler.sendAddVideoContent(uploader, address, tag, bitrate, length, width, height, encoding);
        if (eventHandler.isExecuted()) {
            return "added Video-Content for the uploader: " + uploader;
        } else {
            return "could not add Video-Content for the uploader: " + uploader;
        }
    }

    private String addAudioVideoContent(String userInput) {
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
        eventHandler.sendAudioVideoContentEvent(uploader, address, tag, bitrate, length, samplingRate, encoding, width, height);
        if (eventHandler.isExecuted()) {
            return "added Audio-Video-Content for the uploader: " + uploader;
        } else {
            return "could not add Audio-Video-Content for the uploader: " + uploader;
        }
    }

    private String addLicensedAudioContent(String userInput) {
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
        eventHandler.sendLicensedAudioContent(uploader, address, tag, bitrate, length, samplingRate, encoding, holder);
        if (eventHandler.isExecuted()) {
            return "added licensed Audio-Content for the uploader: " + uploader;
        } else {
            return "could not add licensed Audio-Content for the uploader: " + uploader;
        }
    }

    private String addLicensedVideoContent(String userInput) {
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
        eventHandler.sendLicensedVideoContent(uploader, address, tag, bitrate, length, width, height, encoding, holder);
        if (eventHandler.isExecuted()) {
            return "added licensed Video-Content for the uploader: " + uploader;
        } else {
            return "could not add licensed Video-Content for the uploader: " + uploader;
        }
    }

    private String addLicensedAudioVideoContent(String userInput) {
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
        eventHandler.sendLicensedAudioVideoContentEvent(uploader, address, tag, bitrate, length, samplingRate, encoding, width, height, holder);
        if (eventHandler.isExecuted()) {
            return "added licensed Audio-Video-Content for the uploader: " + uploader;
        } else {
            return "could not add licensed Audio-Video-Content for the uploader: " + uploader;
        }
    }

    private String deleteUploader(String userInput) {

        eventHandler.sendDeleteUploaderEvent(userInput);
        if (eventHandler.isExecuted()) {
            return "deleted the Uploader: " + userInput;
        } else {
            return "could not delete the uploader: " + userInput;
        }
    }

    private String getContentForOneSpecificType(String userInput) {
        eventHandler.sendGetContentByNameEvent(userInput);
        List<?> contentList = eventHandler.getContentList();
        return Arrays.toString(contentList.toArray());
    }

    private String deleteContent(String userInput) {
        eventHandler.sendDeleteContentEvent(Integer.parseInt(userInput));
        if (eventHandler.isExecuted()) {
            return "deleted content on position: " + userInput;
        } else {
            return "could not delete content on position: " + userInput;
        }
    }

    private String getAmountOfDataForOneUploader(String userInput) {
        eventHandler.sendRequestAmountOfUploadsEvent(userInput);
        return Integer.toString(eventHandler.getAmountOfUploads());
    }

    private String getDateAndAddressForOneUploader(String userInput) {
        eventHandler.sendGetAddressAndDateEvent(Integer.parseInt(userInput));
        return eventHandler.getAddressAndDate();
    }

    private String printContentList() {
        eventHandler.sendRequestPrintListEvent();
        System.out.println(eventHandler.getStringContentList() );
        return eventHandler.getStringContentList();
    }

}
