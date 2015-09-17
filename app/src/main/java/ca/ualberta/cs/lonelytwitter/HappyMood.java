package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by dhaberst on 9/16/15.
 */

public class HappyMood extends CurrentMood {

    public HappyMood(Date date) {
        super(date);
    }

    public HappyMood() {
    }

    @Override
    public String format() {
        return "Happy";
    }

}
