package com.greycodes.excel14.login;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.greycodes.excel14.HomeNDActivity;
import com.greycodes.excel14.R;

public class OnlineStatusFragment extends ListFragment {
	public  static TextView tv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.user_onlinestatus, container, false);
		setListAdapter(HomeNDActivity.scoreAdapter);
		 return rootView;
	}
	
}
