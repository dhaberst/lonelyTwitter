package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;

import java.util.Date;
import java.util.Vector;


/**
 * Created by dhaberst on 9/30/15.
 */

public class TweetListTest extends ActivityInstrumentationTestCase2 {

    public TweetListTest() {
        super(LonelyTwitterActivity.class);
    }

    public void testDeleteTweet() {
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("HIHIHIHI");
        tweetList.add(tweet);
        tweetList.delete(tweet);
        assertFalse(tweetList.hasTweet(tweet));
    }

    public void testHasTweet() {
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("HIHIHIHI");
        assertFalse(tweetList.hasTweet(tweet));
        tweetList.add(tweet);
        assertTrue(tweetList.hasTweet(tweet));
    }

    public void testAddTweet() {
        boolean catchtrue = false;
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("HIHIHIHI");
        tweetList.add(tweet);
        assertTrue(tweetList.hasTweet(tweet));
        try { tweetList.add(tweet);

        } catch (IllegalArgumentException e) {
            catchtrue = true;
        }

        assertTrue(catchtrue);
    }

    public void testGetTweetCount() {
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("HIHIHIHI");
        tweetList.add(tweet);
        assertTrue(tweetList.getTweetCount() == 1);
    }

    public void testGetTweet() {
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("HIHIHIHI");
        tweetList.add(tweet);
        Tweet returnedTweet = tweetList.getTweet(0);
        assertTrue((tweet.date.equals(returnedTweet.date)) && tweet.getText().equals(returnedTweet.getText()));
    }

    public void testRemoveTweets() {
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("HIHIHIHI");
        tweetList.add(tweet);
        tweetList.removeTweet(tweet);
        assertTrue(tweetList.getTweetCount() == 0);
    }

    public void testGetTweets() {
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("HIHIHIHI");
        tweetList.add(tweet);
        Tweet tweet2 = new NormalTweet("HIHIHIHI2", new Date(100));
        tweetList.add(tweet2);
        assertTrue(tweetList.getTweet(0).getDate().compareTo(tweetList.getTweet(1).getDate()) > 0);
    }


}