package eventBasedCli.observers;

import management.HeadQuarter;

public class CapacityObserver implements IObserver {
private HeadQuarter headQuarter;
private int size;


    public  CapacityObserver(HeadQuarter headQuarter) {
        this.headQuarter = headQuarter;
        this.headQuarter.register(this);

    }
// Es geht nicht weil ich checke bevor das Objekt erstellt wurde
    @Override
    public void update() {
        System.out.println(size);
        System.out.println(headQuarter.getMaxSizeOfDatabase());
        if(size >= (headQuarter.getMaxSizeOfDatabase() *0.9)){
            System.out.println("The Database has reached 90% of its capacity !");
        }
        size = headQuarter.getMediaList().size();
    }
}
