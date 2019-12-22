package eventBasedCli.observers;

import management.HeadQuarter;
import mediaDB.Tag;

import java.util.Collection;

public class GetRemovedTagsObserver implements IObserver {
    private HeadQuarter headQuarter;
    private Collection<Tag> tags;

    public GetRemovedTagsObserver(HeadQuarter headQuarter) {
        this.headQuarter = headQuarter;
        this.tags = headQuarter.getUsedTags();
        this.headQuarter.register(this);
    }

    @Override
    public void update() {

        Collection<Tag> oldTags = tags;
        Collection<Tag> updatedTags = headQuarter.getUsedTags();


        if (tags.size() >= updatedTags.size()) {
            oldTags.removeAll(updatedTags);
            System.out.println("currently removed: " + oldTags);
        }
        tags = headQuarter.getUsedTags();
    }
}

