package com.sm.backpackingplanner;

import com.google.android.gms.maps.model.LatLng;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class NewTripActivity extends FragmentActivity implements OnItemSelectedListener{
 private LatLng[] locations = new LatLng[5];

 
 
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_trip_v2);
		
		//allows the app icon in the menu to become a button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		Spinner spinner = (Spinner) findViewById(R.id.locationSpinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.locations_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		
	}

	public void initLocations(){
		locations[0] = new LatLng(39.1555198, -120.1651629);
		locations[1] = new LatLng(36.331608, -121.8181792);
		locations[2] = new LatLng(34.4281937, -119.702067);
		locations[3] = new LatLng(37.8217929, -119.5565588);
//		locations[4] = new LatLng(39.1555198, -120.1651629);
		
		}
	
	public void onItemSelected(AdapterView<?> parent, View view, 
            int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_trip, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		if (item.getItemId() == android.R.id.home){
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void DataPicker(View v){
//		Intent intent = new Intent(this,DatePickerFragment.class);
//		startActivity(intent);
		
	    DialogFragment newFragment = new DatePickerFragment();
	    newFragment.show(getFragmentManager(), "datePicker");
	}
	
	
}
