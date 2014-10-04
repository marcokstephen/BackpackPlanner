package com.sm.backpackingplannerdata;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import android.annotation.SuppressLint;


public class Trip {

	private String key;
	private String location;
	private String weather;
	private String date;
	
	ArrayList<Item> items = new ArrayList<Item>();
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}	
	
	
	// End getters and setters
	
		@SuppressLint("SimpleDateFormat")
		public static Trip getNew(){
			
			Locale locale = new Locale("en_US"); //easily sortable timestamp type -- en_US
			Locale.setDefault(locale);
			
			String pattern = "yyyy-MM-dd HH:mm:ss Z"; //datetime stamp e.g. 1989-02-14 12:20:32 -9
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			String key = formatter.format(new Date());
			
			Trip trip = new Trip();
			trip.setKey(key);
			trip.setLocation("");
			return trip;	
		}
		
		/* May use
		@Override
		public String toString() {
			return this.getlocation();
		}
		*/
}
