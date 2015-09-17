package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by dhaberst on 9/16/15.
 */

public abstract class Tweet implements Tweetable {

    private String text;
    private Date date;
    private ArrayList<CurrentMood> moods = new ArrayList<CurrentMood>();

    public ArrayList<CurrentMood> getMoods() {
        return moods;
    }

    public void addMoods(CurrentMood mood) {
        this.moods.add(mood);
    }

    //Constructor
    public Tweet(String tweet, Date date) {
        this.setText(tweet);
        this.date = date;
    }

    public Tweet(String tweet) {
        this.setText(tweet);
        this.date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        try {
            if (text.length() <= 140) {
                this.text = text;
            } else {
                throw new IllegalArgumentException("Tweet was to long");
            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e); //Crash program and print out stack trace
        }
    }

    public abstract Boolean isImportant();
}
