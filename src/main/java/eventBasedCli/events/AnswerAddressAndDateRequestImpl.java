package eventBasedCli.events;

public class AnswerAddressAndDateRequestImpl {
    private String answerString;

    public AnswerAddressAndDateRequestImpl(String answerString) {
        this.answerString = answerString;
    }

    public String getAnswerString() {
        return answerString;
    }
}
