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
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LonelyTwitterActivity extends Activity {

	private static final String FILENAME = "file.sav"; // Model
	private EditText bodyText; // Model
	private ListView oldTweetsList; // Model
	private ArrayList<Tweet> tweets = new ArrayList<Tweet>(); // Model
	private ArrayAdapter<Tweet> adapter; // Model

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) { // Model

		super.onCreate(savedInstanceState); // Model
		setContentView(R.layout.main); // Model

		bodyText = (EditText) findViewById(R.id.body); // Model
		Button saveButton = (Button) findViewById(R.id.save); // Model
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