package com.greycodes.excel14.talkseries;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.greycodes.excel14.R;

public class TedXMecFragment extends Fragment implements OnClickListener {
	public  static TextView tv;
	RelativeLayout rr1;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.talkseries_tedxmeec, container, false);
		 rr1=(RelativeLayout)rootView.findViewById(R.id.rrl);
		 tv=(TextView)rootView.findViewById(R.id.txtinclude);
		 rr1.setOnClickListener(this);
		 return rootView;
		
		
	}
	

public  void updateTextValue(CharSequence newText) {
    tv.setText(newText);
    tv.setAnimation(AnimationUtils.loadAnimation(getActivity(),android.R.anim.fade_in));
	}


@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	if(v.equals(rr1)){
		
		updateTextValue("TEDxMEC is an independently organised TED event which seeks to establish the internationally renowned TED experience at Govt. Model Engineering College, Kochi. At TEDxMEC, TED Talks videos and live speakers combine to spark deep discussion and connection in a small group. We present a platform where people bring about changes in one of the oldest languages known to man - stories! The world around us is less curious about people and more curious about ideas. TEDxMEC is here to inspire, to celebrate ideas. This year TEDxMEC is scheduled to be held on 25th September 2014 from 5pm to 9pm in the college amphitheatre at Thrikkakara, Kochi.\n\n");
	}
	
}


}
