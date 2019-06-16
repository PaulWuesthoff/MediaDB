package eventBasedCli.events;

public class GetAmountOfUploadsAnswerEventImpl {
    private int amount;

    public GetAmountOfUploadsAnswerEventImpl(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
