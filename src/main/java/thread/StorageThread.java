package thread;

public class StorageThread extends Thread{
    private ThreadHandler threadHandler;

    public StorageThread(ThreadHandler threadHandler) {
        this.threadHandler = threadHandler;
    }
    @Override
    public void run(){
        while (true){
            try {
                Thread.sleep(0);
                threadHandler.store();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
