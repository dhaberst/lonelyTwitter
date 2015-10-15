package ca.ualberta.cs.lonelytwitter;

/**
 * Created by dhaberst on 10/14/15.
 */
public class TweetEdit {
    private static TweetEdit ourInstance = new TweetEdit();

    public static TweetEdit getInstance() {
        return ourInstance;
    }

    private TweetEdit() {
    }
}
