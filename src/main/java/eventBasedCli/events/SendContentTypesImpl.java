package eventBasedCli.events;


import java.util.List;

public class SendContentTypesImpl {
    private List<?> contentList;

    public SendContentTypesImpl(List<?> contentList) {
        this.contentList = contentList;
    }

    public List<?> getContentList() {
        return contentList;
    }
}
