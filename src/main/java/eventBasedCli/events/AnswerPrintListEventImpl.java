package eventBasedCli.events;

public class AnswerPrintListEventImpl {
    private String contentList;

    public AnswerPrintListEventImpl(String contentList) {
        this.contentList = contentList;
    }

    public String getContentList() {
        return contentList;
    }
}
