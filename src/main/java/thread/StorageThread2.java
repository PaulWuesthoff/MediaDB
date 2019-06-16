package thread;

public class StorageThread2 extends Thread{
    private ThreadHandler threadHandler;

    public StorageThread2(ThreadHandler threadHandler) {
        this.threadHandler = threadHandler;
    }
    @Override
    public void run(){
        while (true){
            try {
                Thread.sleep(0);
                threadHandler.moveContentToNextDatabase();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
