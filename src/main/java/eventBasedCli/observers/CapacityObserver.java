package eventBasedCli.observers;

import management.HeadQuarter;

public class CapacityObserver implements IObserver {
private HeadQuarter headQuarter;
private int size;


    public  CapacityObserver(HeadQuarter headQuarter) {
        this.headQuarter = headQuarter;
        this.headQuarter.register(this);

    }
    @Override
    public void update() {
        size = headQuarter.getMediaList().size();
        if(size >= (headQuarter.getMaxSizeOfDatabase() *0.9)){
            System.out.println("The Database has reached "+ size/headQuarter.getMaxSizeOfDatabase()*100 +"% of its capacity !");
        }
    }
}
