package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import junit.framework.TestCase;

/**
 * Created by wz on 14/09/15.
 */

public class LonelyTwitterActivityTest extends ActivityInstrumentationTestCase2 {

    private EditText bodytext;
    private Button saveButton;
    private EditText editTweet;

    public LonelyTwitterActivityTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    public void testStart() throws Exception {
        Activity activity = getActivity();
    }

    public void testEditTweet() {
        // when you call getactivity android will start the app and the activity
        LonelyTwitterActivity activity = (LonelyTwitterActivity) getActivity();

        // reset the app to a known state
        activity.getTweets().clear();

        // add a tweet using the UI
        bodytext = activity.getBodyText();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                bodytext.setText("test tweet");
            }
        });

        getInstrumentation().waitForIdleSync();

        saveButton = activity.getSaveButton();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                saveButton.performClick();
            }
        });

        getInstrumentation().waitForIdleSync();

        // make sure the tweet was actually added
        final ListView oldTweetList = activity.getOldTweetsList();
        Tweet tweet = (Tweet) oldTweetList.getItemAtPosition(0);
        assertEquals("test tweet", tweet.getText());

        // ensure the tweet editor activity starts up
        // following from https://developer.android.com/training/activity-testing/activity-functional-testing.html 14/10/2015
        // Set up an ActivityMonitor
        Instrumentation.ActivityMonitor receiverActivityMonitor = getInstrumentation().addMonitor(EditTweetActivity.class.getName(), null, false);

        // click on the tweet to edit
        activity.runOnUiThread(new Runnable() {
            public void run() {
                View v = oldTweetList.getChildAt(0);
                oldTweetList.performItemClick(v, 0, v.getId());
            }
        });

        getInstrumentation().waitForIdleSync();
        // the code where they clicked was here
        // Validate that ReceiverActivity is started
        EditTweetActivity receiverActivity = (EditTweetActivity) receiverActivityMonitor.waitForActivityWithTimeout(1000);
        assertNotNull("ReceiverActivity is null", receiverActivity);
        assertEquals("Monitor for ReceiverActivity has not been called", 1, receiverActivityMonitor.getHits());
        assertEquals("Activity is of wrong type", EditTweetActivity.class, receiverActivity.getClass());

        // Remove the ActivityMonitor
        getInstrumentation().removeMonitor(receiverActivityMonitor);

        // test that the editor starts up with the right tweet in it to edit
        editTweet = receiverActivity.getEditTweet();
        editTweet.getText();
        assertEquals("test tweet",tweet.getText());

        // test that we can edit that tweet

        // test that we can push some kind of save button for that tweet

        // the new modified tweet text was actually saved

        // the new modified tweet text is displayed on the other activity


        // clean up our activities at the end of our test
        receiverActivity.finish();
    }

}