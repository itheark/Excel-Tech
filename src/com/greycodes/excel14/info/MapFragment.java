package com.greycodes.excel14.info;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.greycodes.excel14.R;

public class MapFragment extends Fragment  {
	private static View view;
	private GoogleMap map;
	private final LatLng LOCATION_MEC = new LatLng(10.02836,76.328829);
	private final LatLng LOCATION_CITY = new LatLng(10.025140,76.308970);
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		  if (view != null) {
		        ViewGroup parent = (ViewGroup) view.getParent();
		        if (parent != null)
		            parent.removeView(view);
		    }
		    try {
		        view = inflater.inflate(R.layout.info_map, container, false);
		    } catch (InflateException e) {
		        /* map is already there, just return view as it is */
		    }
		    		
		    map = ((SupportMapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
			map.addMarker(new MarkerOptions().position(LOCATION_MEC).title("Model Engineering College"));
		 
		 ImageView navigate = (ImageView) view.findViewById(R.id.navigation);
		 navigate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			try {
				Intent navigation = new Intent(Intent.ACTION_VIEW, Uri.parse("https://maps.google.co.in/maps?q=Model+Engineering+College+Thrikkakara,+Ernakulam,+Kerala&hl=en&ll=10.028558,76.328748&spn=0.006772,0.009645&sll=10.027935,76.328716&sspn=0.006772,0.009645&t=h&z=17&iwloc=A"));
				startActivity(navigation);
				
			} catch (Exception e){}
				
			
			}
		});
		 
		 ImageView street = (ImageView) view.findViewById(R.id.street);
		street.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
				CameraUpdate update =CameraUpdateFactory.newLatLngZoom(LOCATION_CITY,13); 
				map.animateCamera(update);
				
				
				
			}
		});
		 
		 
		 
		 ImageView college = (ImageView) view.findViewById(R.id.college);
		 college.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
				CameraUpdate update =CameraUpdateFactory.newLatLngZoom(LOCATION_MEC,16); 
				map.animateCamera(update);
			
			}
		});
		 
		 
		 
		 return view;
	
			 
	} 



	




}






