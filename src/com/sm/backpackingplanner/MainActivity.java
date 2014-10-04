package com.sm.backpackingplanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends Activity {

	Context c;
	
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
        
        ListView tripListView = (ListView)findViewById(R.id.tripListView);
        
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
        return super.onOptionsItemSelected(item);
    }
    
    public class TripListAdapter extends BaseAdapter {
    	
    	private LayoutInflater myLayoutInflater;
    	private JSONArray myJsonArray;
    	
    	public TripListAdapter(Context ctxt, String jsonTripList){
    		myLayoutInflater = (LayoutInflater) ctxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    		try {
    			myJsonArray = new JSONArray(jsonTripList);
    		} catch (JSONException e){
    			e.printStackTrace();
    		}
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
			return null;
		}
    	
    }
}
