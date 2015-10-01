package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by dhaberst on 9/30/15.
 */
public class TweetList {
    private ArrayList<Tweet> tweets = new ArrayList<Tweet>();

    public void add(Tweet tweet) {
        if (this.hasTweet(tweet)) {
           throw new IllegalArgumentException();
        }
        tweets.add(tweet);
    }

    public void delete(Tweet tweet) {
        tweets.remove(tweet);
    }

    public boolean hasTweet(Tweet tweet) {
        return tweets.contains(tweet);
    }

    public Tweet getTweet(int index) {
        return tweets.get(index);
    }

    public int getTweetCount() {
        return tweets.size();
    }

    public void removeTweet(Tweet tweet) {
        tweets.remove(tweet);

    }

    public ArrayList getTweets() {
        Collections.sort(tweets, new Comparator<Tweet>() {

            public int compare(Tweet tweet1, Tweet tweet2) {

                return tweet1.getDate().compareTo(tweet2.getDate());
            }
        });
        return tweets;
    }

}
