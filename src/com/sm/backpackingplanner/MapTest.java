package com.sm.backpackingplanner;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

public class MapTest extends FragmentActivity {

	private GoogleMap map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_test);

		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		map.setMyLocationEnabled(true);
		
//		map.getMyLocation();
//		CameraPosition cameraPosition = new CameraPosition.Builder().target(
//                new LatLng(myLoc.getLatitude(), myLoc.getLongitude())).zoom(12).build();
// 
//map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
	}

}