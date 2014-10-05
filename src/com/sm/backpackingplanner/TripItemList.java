package com.sm.backpackingplanner;

import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.sm.backpackingplannerdata.DataStoreInventory;
import com.sm.backpackingplannerdata.Item;

public class TripItemList extends ListActivity {

	
	private DataStoreInventory inventorysource;
	List<Item> itemsList;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trip_item_list);
		
		inventorysource = new DataStoreInventory(this);
		
		refreshDisplay();

//		Intent intent = getIntent();
//		int idValue = intent.getIntExtra(MainActivity.INTENT_BUNDLE_ID, 0);
//		inventorysource = new DataStoreInventory();
//		JSONObject eventJsonObject = DataStoreInventory.getEveItemId(idValue);
//		TextView testOutput = (TextView) findViewById(R.id.test_output);
//		testOutput.setText(eventJsonObject.toString());
	}

	private void refreshDisplay() {
		itemsList = inventorysource.findAll();
		ArrayAdapter<Item> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemsList);
		setListAdapter(adapter);
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
