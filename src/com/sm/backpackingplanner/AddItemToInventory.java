package com.sm.backpackingplanner;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.sm.backpackingplannerdata.ItemLookup;
import com.sm.backpackingplannerdata.ServiceHandler;

public class AddItemToInventory extends Activity {

	String someKeywords = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_item_to_inventory);

		// allows the app icon in the menu to become a button
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_item_to_inventory, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		if (item.getItemId() == android.R.id.home) {
			finish();
		}
		return super.onOptionsItemSelected(item);
	}

	public class CallAPI extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute(){
                super.onPreExecute();
                //do things to prepare your network call here
        }

        @Override
        protected Void doInBackground(Void... arg0) {
                //make your network call here
                String url = ItemLookup.makeCall(someKeywords);
                ServiceHandler sh = new ServiceHandler();
                String outputString = sh.makeServiceCall(url, ServiceHandler.GET);
                return null;
        }

        @Override
        protected void onPostExecute(Void result){
                super.onPostExecute(result);
                //process your data here
        }
}
}
