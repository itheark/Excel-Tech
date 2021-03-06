package com.greycodes.excel14.nontechnical;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.greycodes.excel14.R;


public class FragmentBestManager extends SherlockFragment implements OnClickListener {
	ImageView intro;
	 
	public  static TextView tv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.nt_bestmanager, container, false);
		 tv=(TextView)rootView.findViewById(R.id.txtinclude);
		 intro=(ImageView)rootView.findViewById(R.id.intro);
		 
		 
		 intro.setOnClickListener(this);
		 
		return rootView;
		
		
		
		
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		
		if(v.equals(intro))
		{
			updateTextValue("Ever seen a company that runs without that one guy up there?  As Peter Drucker says, � The manager is the dynamic, life-giving element in every business� The World Needs Managers; to lead... to plan... to motivate... We challenge you to walk the walk and talk the talk and convince us that you are in the �top� of the world�s best managers.\n\n" );
			
		}
		
		
		
		
	}
	

	public  void updateTextValue(CharSequence newText) {
	    tv.setText(newText);
	    tv.setAnimation(AnimationUtils.loadAnimation(getActivity(),android.R.anim.fade_in));
		}
	

}

