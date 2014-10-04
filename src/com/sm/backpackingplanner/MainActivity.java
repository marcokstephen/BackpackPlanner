package com.sm.backpackingplanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sm.backpackingplannerdata.Datastore;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	Context c;
	public static final String INTENT_BUNDLE_ID = "com.sm.backpackingplanner.INTENT_BUNDLE_ID";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c = this;
        
        Button addToInventoryButton = (Button)findViewById(R.id.addNewButton);
        addToInventoryButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(c, AddItemToInventory.class);
				startActivity(intent);
			}
		});
        
        new Datastore(c);
		JSONArray tripsJsonArray = Datastore.getEventList();
        ListView tripListView = (ListView)findViewById(R.id.tripListView);
        tripListView.setAdapter(new TripListAdapter(this, tripsJsonArray));
        
        tripListView.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				Intent tripItemList = new Intent(c, TripItemList.class);
				tripItemList.putExtra(INTENT_BUNDLE_ID,position);
				startActivity(tripItemList);
			}
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_new_list) {
        	Intent intent = new Intent(this, NewTripActivity.class);
        	startActivity(intent);
        	return true;
        }
        else if (id == R.id.map_action){
        	Intent intent = new Intent(this, MapTest.class);
        	startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    
    public class TripListAdapter extends BaseAdapter {
    	
    	private LayoutInflater myLayoutInflater;
    	private JSONArray myJsonArray;
    	
    	public TripListAdapter(Context ctxt, JSONArray jsonTripList){
    		myLayoutInflater = (LayoutInflater) ctxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    		myJsonArray = jsonTripList;
    	}
    	
		@Override
		public int getCount() {
			return myJsonArray.length();
		}

		@Override
		public Object getItem(int arg0) {
			try {
				return myJsonArray.get(arg0);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@Override
		public View getView(int arg0, View view, ViewGroup arg2) {
			if (view == null){
				view = myLayoutInflater.inflate(R.layout.trip_list_view_element, null);
			}
			//should be working with JSONObjects. 4 fields: id,location,date,weather
			try {
				JSONObject myJsonObj = myJsonArray.getJSONObject(arg0);
				String location = myJsonObj.getString("location");
				String date = myJsonObj.getString("date");
				String weather = myJsonObj.getString("weather");
				
				TextView locationTextView = (TextView)view.findViewById(R.id.location_text_view);
				TextView weatherTextView = (TextView)view.findViewById(R.id.weather_summary_text_view);
				TextView dateTextView = (TextView)view.findViewById(R.id.date_text_view);
				
				locationTextView.setText(location);
				weatherTextView.setText(weather);
				dateTextView.setText(date);
				return view;
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}
    	
    }
}
