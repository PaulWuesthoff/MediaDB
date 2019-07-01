package thread;

public class StorageThread3 extends Thread{
    private ThreadHandler threadHandler;

    public StorageThread3(ThreadHandler threadHandler) {
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
