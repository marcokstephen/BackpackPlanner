package com.sm.backpackingplanner;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

//this class is used for storing and calling data from the SharedPreferences
public class Datastore {
	
	SharedPreferences prefs;
	
	private static final String GET_EVENT_LIST = "com.sm.backpackingplanner.GET_EVENT_LIST";
	
	
	public Datastore(Context c){
		prefs = PreferenceManager.getDefaultSharedPreferences(c);
	}
	
	public JSONArray getEventList(){
		JSONArray newArray = new JSONArray();
		try {
			newArray = new JSONArray(prefs.getString(GET_EVENT_LIST, ""));
		} catch (JSONException e){
			e.printStackTrace();
		}
		return newArray;
	}
	
}
