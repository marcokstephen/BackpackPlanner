package com.sm.backpackingplannerdata;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Item {
	
	    public String Brand;
	    public String Feature;
	    public String Title;
	    public String ASIN;
	    public String key;
	    

	    public Item() {
	        this.Brand = "";
	        this.Feature = "";
	        this.Title = "";
	        this.ASIN = "";
	        
			Locale locale = new Locale("en_US"); //easily sortable timestamp type -- en_US
			Locale.setDefault(locale);
			
			String pattern = "yyyy-MM-dd HH:mm:ss Z"; //datetime stamp e.g. 1989-02-14 12:20:32 -9
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			String keyer = formatter.format(new Date());
			
			this.key = keyer;	
		}
	

		public Item(String Brand, String Feature, String Title, String ASIN) {
	        this.Brand = Brand;
	        this.Feature = Feature;
	        this.Title = Title;
	        this.ASIN = ASIN;
	        
			
			Locale locale = new Locale("en_US"); //easily sortable timestamp type -- en_US
			Locale.setDefault(locale);
			
			String pattern = "yyyy-MM-dd HH:mm:ss Z"; //datetime stamp e.g. 1989-02-14 12:20:32 -9
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			String keyer = formatter.format(new Date());
			
			this.key = keyer;	
		}
		




		public String getBrand() {
			return Brand;
		}




		public void setBrand(String brand) {
			Brand = brand;
		}




		public String getFeature() {
			return Feature;
		}




		public void setFeature(String feature) {
			Feature = feature;
		}




		public String getTitle() {
			return Title;
		}




		public void setTitle(String title) {
			Title = title;
		}




		public String getASIN() {
			return ASIN;
		}




		public void setASIN(String aSIN) {
			ASIN = aSIN;
		}




		public String getKey() {
			return key;
		}




		public void setKey(String key) {
			this.key = key;
		}
	    
	
	// End getters and setters
	
		
		

//		Might use later...
		
//		@Override
//		public String toString() {
//			return this.getText();
//		}
	
	

}
