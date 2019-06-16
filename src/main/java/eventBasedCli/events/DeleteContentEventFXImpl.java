package eventBasedCli.events;

import mediaDB.Content;

public class DeleteContentEventFXImpl {
    private Content content;

    public DeleteContentEventFXImpl(Content content) {
        this.content = content;
    }

    public Content getContent() {
        return content;
    }
}
