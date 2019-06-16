package eventBasedCli.observers;

public interface IObservable {
    boolean register(IObserver observer);
    boolean unregister(IObserver observer);
    void notifyObservers();
}
