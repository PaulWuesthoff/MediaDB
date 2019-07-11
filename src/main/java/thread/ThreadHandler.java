package thread;

import management.HeadQuarter;
import mediaDB.Content;
import mediaDB.MediaContentImpl;
import mediaDB.Tag;
import uploaderDB.UploaderImpl;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ThreadHandler {
    private List<HeadQuarter> storageList;
//    private HeadQuarter storageThread;
//    private HeadQuarter storageThread2;
//    private HeadQuarter storageThread3;


    public ThreadHandler() {
        storageList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            HeadQuarter headQuarter = new HeadQuarter();
            headQuarter.addUploader(new UploaderImpl("Paul"));
            storageList.add(headQuarter);
        }
//        this.storageThread = new HeadQuarter();
//        this.storageThread2 = new HeadQuarter();
//        this.storageThread3 = new HeadQuarter();
//        storageThread.addUploader(new UploaderImpl("Paul"));
//        storageThread2.addUploader(new UploaderImpl("Paul"));
//        storageThread3.addUploader(new UploaderImpl("Paul"));


    }

    public synchronized void store() {
        String address = generateString();
        Collection<Tag> tags = generateRandomTag();
        int bitrate = ThreadLocalRandom.current().nextInt(100, 200);
        long length = ThreadLocalRandom.current().nextLong(3, 6);
        Random random = new Random();
        int i = random.nextInt(storageList.size());
        MediaContentImpl mediaContent = new MediaContentImpl(storageList.get(i).getUploader("Paul"), address, tags, bitrate, length);
        Boolean isStored = storageList.get(i).addMediaContent(mediaContent);
        if (isStored) {
            System.out.println("The content has been stored");
        } else {
            System.out.println("Could not store content!");
            notify();
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public synchronized void moveContentToNextDatabase() {

        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        HeadQuarter oldestHeadQuarter = null;
        Content oldestContent = null;
        if (storageList.stream().allMatch(h -> h.getMaxSizeOfDatabase() == h.getMediaList().size())) {
            System.out.println("Exiting now.. ");

            System.exit(0);
        }

        for (HeadQuarter headQuarter : storageList) {
            if (headQuarter.getMediaList().size() == headQuarter.getMaxSizeOfDatabase()) {
                if (oldestContent == null) {
                    oldestHeadQuarter = headQuarter;
                    oldestContent = oldestHeadQuarter.getOldestContent();
                } else if (oldestContent.getTimestamp().before(headQuarter.getOldestContent().getTimestamp())) {
                    oldestHeadQuarter = headQuarter;
                    oldestContent = oldestHeadQuarter.getOldestContent();

                }
            }
        }

        oldestHeadQuarter.deleteContent(oldestContent);

        Random random = new Random();
        while (!storageList.get(random.nextInt(storageList.size())).addMediaContent(oldestContent)) {

        }

//        if (!storageThread2.addMediaContent(content)) {
//            if (!storageThread3.addMediaContent(content)) {
//                System.out.println("Storage full, existing now ! ");
//                System.out.println(storageThread.printList());
//                System.out.println(storageThread2.printList());
//                System.out.println(storageThread3.printList());
//                System.exit(0);
//            }
//
//        }
        System.out.println("Moving content to next database !");
        notify();
    }


    //source: https://kodejava.org/how-do-i-generate-random-string/
    private String generateString() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        char[] text = new char[10];
        for (int i = 0; i < text.length; i++) {
            text[i] = characters.charAt(new Random().nextInt(characters.length()));
        }
        return new String(text);
    }

    private Collection<Tag> generateRandomTag() {
        String[] tagArray = getEnumsAsString(Tag.class);
        Random random = new Random();
        ArrayList<Tag> tags = new ArrayList<>();
        for (int i = random.nextInt(Tag.values().length); i < tagArray.length; i++) {
            tags.add(Tag.valueOf(tagArray[i]));
        }
        return tags;
    }

    private String[] getEnumsAsString(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }


}
