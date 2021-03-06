package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by dhaberst on 9/16/15.
 */

public abstract class CurrentMood {

    protected Date date;

    public CurrentMood(Date date) {
        this.date = date;
    }

    public CurrentMood() {
        this.date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public abstract String format();
}
