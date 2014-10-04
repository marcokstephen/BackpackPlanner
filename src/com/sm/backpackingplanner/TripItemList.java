package com.sm.backpackingplanner;

import org.json.JSONObject;

import com.sm.backpackingplannerdata.DataStoreInventory;
import com.sm.backpackingplannerdata.Datastore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class TripItemList extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		int idValue = intent.getIntExtra(MainActivity.INTENT_BUNDLE_ID, 0);
		new DataStoreInventory(this);
		JSONObject eventJsonObject = DataStoreInventory.getEventById(idValue);

		setContentView(R.layout.activity_trip_item_list);
		TextView testOutput = (TextView) findViewById(R.id.test_output);
		testOutput.setText(eventJsonObject.toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.trip_item_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
