package eventBasedCli.events;

public class AnswerRequestEventImpl  {
    private boolean executed;

    public AnswerRequestEventImpl(boolean executed) {
        this.executed = executed;
    }

    public boolean isExecuted() {
        return executed;
    }

}
