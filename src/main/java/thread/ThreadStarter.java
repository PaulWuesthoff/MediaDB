package thread;

public class ThreadStarter {
    public static void main(String[] args) {
        ThreadHandler threadHandler = new ThreadHandler();
        StorageThread storageThread = new StorageThread(threadHandler);
        StorageThread2 storageThread2 = new StorageThread2(threadHandler);

        storageThread.start();
        storageThread2.start();

        try {
            storageThread.join();
            storageThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       System.exit(0);
    }
}
