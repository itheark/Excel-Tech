package com.greycodes.excel14.newsfeed;


import com.greycodes.excel14.R;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NewsFeedService extends android.support.v4.app.ListFragment
{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.homend_newsfeed, container, false);
		getActivity().startService(new Intent(getActivity(), Checkflag.class));
		 		 
		 		 
		 
		 return rootView;
	}

	
	
	
	

}
