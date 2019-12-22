package thread;

public class moveContentThread extends Thread{
    private ThreadHandler threadHandler;

    public moveContentThread(ThreadHandler threadHandler) {
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
