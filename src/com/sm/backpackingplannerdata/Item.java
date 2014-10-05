package com.sm.backpackingplannerdata;

public class Item {
	
	    public String Brand;
	    public String Feature;
	    public String Title;
	    public String ASIN;

	    public Item(String Brand, String Feature, String Title, String ASIN) {
	        this.Brand = Brand;
	        this.Feature = Feature;
	        this.Title = Title;
	        this.ASIN = ASIN;
	    }
	    
	    
	    //Getter and setters

		public String getBrand() {
			return Brand;
		}

		public String getFeature() {
			return Feature;
		}

		public String getTitle() {
			return Title;
		}

		public String getASIN() {
			return ASIN;
		}
	    
	    
	
	
	// End getters and setters
	
		
		

//		Might use later...
		
//		@Override
//		public String toString() {
//			return this.getText();
//		}
	
	

}
