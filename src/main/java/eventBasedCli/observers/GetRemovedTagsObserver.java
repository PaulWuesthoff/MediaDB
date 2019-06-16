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
        Collection<Tag> updatedTags = headQuarter.getUnusedTags();

        updatedTags.removeAll(oldTags);
        if (tags.size() > headQuarter.getUnusedTags().size()) {
            System.out.println("currently removed: " + updatedTags);
        }
        tags = headQuarter.getUnusedTags();
    }
}

