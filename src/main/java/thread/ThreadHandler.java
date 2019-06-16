package thread;

import management.HeadQuarter;
import mediaDB.Content;
import mediaDB.MediaContentImpl;
import mediaDB.Tag;
import uploaderDB.UploaderImpl;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ThreadHandler {
    private HeadQuarter storageThread;
    private HeadQuarter storageThread2;
    private int capacity;

    public ThreadHandler() {
        this.storageThread = new HeadQuarter();
        this.storageThread2 = new HeadQuarter();
        storageThread.addUploader(new UploaderImpl("Paul"));
        storageThread2.addUploader(new UploaderImpl("Paul"));

    }

    public synchronized void store() {
        String address = generateString();
        Collection<Tag> tags = generateRandomTag();
        Boolean isStored;
        int bitrate = ThreadLocalRandom.current().nextInt(100, 200);
        long length = ThreadLocalRandom.current().nextLong(3, 6);
        MediaContentImpl mediaContent = new MediaContentImpl(storageThread.getUploader("Paul"), address, tags, bitrate, length);
        isStored = storageThread.addMediaContent(mediaContent);
        if (isStored) {
            capacity += bitrate*length;
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

        Content content = storageThread.getOldestContent();
        System.out.println(storageThread.deleteContent(content));
        if (!storageThread2.addMediaContent(content)) {
            System.out.println("Storage full, existing now ! ");
            System.out.println(storageThread.printList());
            System.out.println(storageThread2.printList());
            System.out.println(capacity);
            System.exit(0);
        }
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
