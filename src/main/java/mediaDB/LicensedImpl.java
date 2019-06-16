package mediaDB;

import uploaderDB.Uploader;

import java.util.Collection;


public class LicensedImpl extends ContentImpl implements Licensed {
    private String holder;
    public LicensedImpl(Uploader uploader, String address, Collection<Tag> tags, String holder) {
        super(uploader, address, tags);
        this.holder = holder;
    }

    @Override
    public String getHolder() {
        return holder;
    }
}
