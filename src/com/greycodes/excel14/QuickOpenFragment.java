package com.greycodes.excel14;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class QuickOpenFragment extends ListFragment {
public QuickOpenAdapter adapter;

String[] ename,cat,stime,duration,venue;
int[] eid,hotness,level;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		setListAdapter(HomeNDActivity.adapter);
		 View rootView = inflater.inflate(R.layout.quickopen_fragment, container, false);
		
		 
		 return rootView;
	}

}
