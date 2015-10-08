package ca.ualberta.cs.lonelytwitter;


/**
 * Created by dhaberst on 10/7/15.
 */
public interface MyObservable {
    void addObserver(MyObserver o);
    void notifyObservers();
}
