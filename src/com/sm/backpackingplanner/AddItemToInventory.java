package com.sm.backpackingplanner;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sm.backpackingplannerdata.AmazonParser;
import com.sm.backpackingplannerdata.DataStoreInventory;
import com.sm.backpackingplannerdata.Item;
import com.sm.backpackingplannerdata.ItemLookup;
import com.sm.backpackingplannerdata.ServiceHandler;

public class AddItemToInventory extends Activity {

	String someKeywords = null;
	String outputString= null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_item_to_inventory);

		// allows the app icon in the menu to become a button
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}
	
	public void CallAmazon(View v){
		EditText amazonView = (EditText) findViewById(R.id.searchView1);
		outputString= amazonView.getText().toString();
		new CallAPI().execute(null, null, null);
		
		Intent intent = new Intent(this, TripItemList.class).putExtra("outputStream", outputString);
		startActivity(intent);	
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
// second class ---- needs to be separated -- but not today!! Hackathon!!!!
	public class CallAPI extends AsyncTask<Void,Void,Void>{
		
		
		
        @Override
        protected void onPreExecute(){
                super.onPreExecute();
                //do things to prepare your network call here
        }

        @Override
        protected Void doInBackground(Void... arg0) {
                //make your network call here
                try{
        		String url = ItemLookup.makeCall(someKeywords);
                System.out.print(url);
               
                Log.d("url", url); ///for testing
                ServiceHandler sh = new ServiceHandler();
                outputString = sh.makeServiceCall(url, ServiceHandler.GET);
                Log.d("xml", outputString); ///for testing
                } catch (Exception e){
                	e.printStackTrace();
                }
                return null;
        }

        @Override
        protected void onPostExecute(Void result){
                super.onPostExecute(result);
                //process your data here
                Toast.makeText(getBaseContext(), outputString, Toast.LENGTH_LONG).show();
                
        }
}
}
