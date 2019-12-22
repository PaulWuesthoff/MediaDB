package thread;

public class ThreadStarter {
    public static void main(String[] args) {
        ThreadHandler threadHandler = new ThreadHandler();
        StorageThread storageThread = new StorageThread(threadHandler);
        StorageThread storageThread3 = new StorageThread(threadHandler);
        StorageThread storageThread4 = new StorageThread(threadHandler);
        StorageThread storageThread5 = new StorageThread(threadHandler);
        moveContentThread moveContentThread = new moveContentThread(threadHandler);


        moveContentThread.start();
        storageThread.start();

        storageThread3.start();
        storageThread4.start();
        storageThread5.start();



        try {
            storageThread.join();
            moveContentThread.join();
            storageThread3.join();
            storageThread4.join();
            storageThread5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       System.exit(0);

   }

}
