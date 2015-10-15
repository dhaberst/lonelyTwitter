package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LonelyTwitterActivity extends Activity {

	private static final String FILENAME = "file.sav"; // Model
	private LonelyTwitterActivity activity = this;
	private EditText bodyText; // Model
	private ListView oldTweetsList; // Model
	private Button saveButton;

	public ListView getOldTweetsList() {
		return oldTweetsList;
	}

	public Button getSaveButton() {
		return saveButton;
	}

	public ArrayList<Tweet> getTweets() {
		return tweets;
	}

	public EditText getBodyText() {
		return bodyText;
	}

	private ArrayList<Tweet> tweets = new ArrayList<Tweet>(); // Model
	private ArrayAdapter<Tweet> adapter; // Model

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) { // Model

		super.onCreate(savedInstanceState); // Model
		setContentView(R.layout.main); // Model

		bodyText = (EditText) findViewById(R.id.body); // Model
		saveButton = (Button) findViewById(R.id.save);
		Button clearButton = (Button) findViewById(R.id.clear); // Model
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList); // Model

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) { // Controller
				setResult(RESULT_OK); // Model
				String text = bodyText.getText().toString(); // Model
				tweets.add(new NormalTweet(text)); // Controller
				saveInFile(); // Model
				adapter.notifyDataSetChanged(); // View
				bodyText.setText(""); // View
			}
		});

		clearButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) { // Controller
				clear(); // Model
				adapter.notifyDataSetChanged(); // View
			}
		});

		oldTweetsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(activity, EditTweetActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart(); // View
		loadFromFile(); // Model
		adapter = new ArrayAdapter<Tweet>(this,
				R.layout.list_item, tweets); // Model
		oldTweetsList.setAdapter(adapter); // Model
		adapter.notifyDataSetChanged(); // View
	}

	private void loadFromFile() {
		try {
			FileInputStream fis = openFileInput(FILENAME); // Model
			BufferedReader in = new BufferedReader(new InputStreamReader(fis)); // Model
			Gson gson = new Gson(); // Model
			// https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015/09/23
			Type arraylistType = new TypeToken<ArrayList<NormalTweet>>() {
			}.getType(); // Model
			tweets = gson.fromJson(in, arraylistType); // Model

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			tweets = new ArrayList<Tweet>();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	private void saveInFile() {
		try {
			FileOutputStream fos = openFileOutput(FILENAME,0); // Model
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos)); // Model
			Gson gson = new Gson(); // Model
			gson.toJson(tweets, out); // Model
			out.flush(); // Model
			fos.close(); // Model
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	private void clear() {
		tweets.clear(); // Controller
		saveInFile(); // Model
	}
}