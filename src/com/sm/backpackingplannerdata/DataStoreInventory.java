package com.sm.backpackingplannerdata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import android.content.Context;
import android.content.SharedPreferences;

	
	//this class is used for storing and calling data from the SharedPreferences
	public class DataStoreInventory {
		
		private static final String PREFKEY = "inventory";
		private SharedPreferences itemPrefs;
		
		public DataStoreInventory(Context context){
			itemPrefs = context.getSharedPreferences(PREFKEY, Context.MODE_PRIVATE);	
		}
		
		public List<Item> findAll(){
			
			Map<String, ?> itemsMap = itemPrefs.getAll();
			
			
			//sorts by amazon ASINS
			SortedSet<String> keys = new TreeSet<String>(itemsMap.keySet());
			
			List<Item> inventory = new ArrayList<Item>();
			for (String key: keys) {
				Item item = new Item();
				item.setKey(key);
				item.setTitle((String) itemsMap.get(key));
				inventory.add(item);	
			}
			return inventory;
			
		}
		
		public boolean update(Item item){
			
			SharedPreferences.Editor editor = itemPrefs.edit();
			editor.putString(item.getASIN(), item.getTitle());
			editor.commit();
			return true;
		}
		
		public boolean remove(Item item){
			
			if (itemPrefs.contains(item.getASIN())){
			
			SharedPreferences.Editor editor = itemPrefs.edit();
			editor.remove(item.getASIN());
			editor.commit();
			}	
			return true;
		}
	}