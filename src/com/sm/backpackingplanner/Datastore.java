package com.sm.backpackingplanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

//this class is used for storing and calling data from the SharedPreferences
public class Datastore {
	
	static SharedPreferences prefs;
	private static final String GET_EVENT_LIST = "com.sm.backpackingplanner.GET_EVENT_LIST";
	
	public Datastore(Context c){
		prefs = PreferenceManager.getDefaultSharedPreferences(c);
	}
	
	//The following three functions are used for accessing data
	//about the upcoming trips that are planned
	public static JSONArray getEventList(){
		JSONArray newArray = new JSONArray();
		String jsonArrayAsString = prefs.getString(GET_EVENT_LIST, "[{'location':'sample','weather':'normal','date':'today'}]");
		try {
			newArray = new JSONArray(jsonArrayAsString);
		} catch (JSONException e){
			e.printStackTrace();
		}
		return newArray;
	}
	
	public static void saveEventList(JSONObject obj){
		JSONArray newArray = new JSONArray();
		try {
			newArray = new JSONArray(prefs.getString(GET_EVENT_LIST, ""));
		} catch (JSONException e){
			e.printStackTrace();
		}
		newArray.put(obj);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(GET_EVENT_LIST, newArray.toString());
		editor.commit();
	}
	
	public static JSONObject getEventById(int id){
		JSONArray newArray = new JSONArray();
		try {
			newArray = new JSONArray(prefs.getString(GET_EVENT_LIST,""));
		} catch (JSONException e){
			e.printStackTrace();
		}
		try {
			return newArray.getJSONObject(id);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
}
