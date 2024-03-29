package eventBasedCli.observers;

import management.HeadQuarter;
import mediaDB.Tag;

import java.util.Collection;

public class GetNewTagsObserver implements IObserver {
    HeadQuarter headQuarter;
    private Collection<Tag> tags;

    public GetNewTagsObserver(HeadQuarter headQuarter) {
        this.headQuarter = headQuarter;
        this.tags = headQuarter.getUsedTags();
        this.headQuarter.register(this);
    }


    @Override
    public void update() {
        Collection<Tag> oldTags = tags;
        Collection<Tag> updatedTags = headQuarter.getUsedTags();
        updatedTags.removeAll(oldTags);

       if(1 <= updatedTags.size()){
           System.out.println("currently added: " + updatedTags);
       }
       tags = headQuarter.getUsedTags();
    }
}
