package com.sm.backpackingplanner;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.sm.backpackingplannerdata.AmazonParser;
import com.sm.backpackingplannerdata.DataStoreInventory;
import com.sm.backpackingplannerdata.Item;

public class TripItemList extends ListActivity {

	private String outputForItem;
	private DataStoreInventory inventorysource;
	List<Item> itemsList;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		outputForItem = intent.getStringExtra("outputStream");
		
		InputStream in = new ByteArrayInputStream(outputForItem.getBytes(StandardCharsets.UTF_8));
        AmazonParser parser = new AmazonParser();
        try {
			Item newItem = parser.parse(in);
			itemsList.add(newItem);
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		adapter.notifyDataSetChanged();
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
