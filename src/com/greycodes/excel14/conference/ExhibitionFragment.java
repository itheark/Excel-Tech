package com.greycodes.excel14.conference;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.greycodes.excel14.R;

public class ExhibitionFragment extends Fragment {
	public  static TextView tv;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.conference_exhibitions, container, false);
		return rootView;
	}
}
