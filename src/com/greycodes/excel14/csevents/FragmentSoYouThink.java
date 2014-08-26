package com.greycodes.excel14.csevents;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.greycodes.excel14.R;


public class FragmentSoYouThink extends Fragment implements OnClickListener {
	 ImageView intro,rules,format;
	 
	  public static TextView tv;
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.cs_soyouthink, container, false);
		 
		 
		 tv=(TextView)rootView.findViewById(R.id.txtinclude);
		 intro=(ImageView)rootView.findViewById(R.id.intro);
		 rules =(ImageView)rootView.findViewById(R.id.rules);
		 format =(ImageView)rootView.findViewById(R.id.event);
		 
		 intro.setOnClickListener(this);
		 rules.setOnClickListener(this);
		 format.setOnClickListener(this);
		return rootView;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		
		if(v.equals(intro))
		{
			updateTextValue("“ If You control the code,You control the world”\nAnybody can code, So can you! Or can you?For the first time at Excel, show off your coding skills at ‘So you think you can code’ with brand-new rules and surprises, this event will test you till you break or fight back!  Defying the usual norms, this event will challenge you, surprise you and test you till you break or fight back! Get –Set- code!\n\n" );
			
		}
		if (v.equals(rules))
		{
			updateTextValue("(To be updated ...)\n\n");
			
		}
		
		if (v.equals(format))
		{
			updateTextValue("The idea behind this on spot event is to find the best coder.\nThe event comprises of 2 rounds.\nPreliminary Round: Teams of 2 are put through a screening process consisting of standard objective questions. Top 6 teams qualify for the final round.\nFinal Round:The final stage consists of 3 stages.\nStage 1: Teams are broken down at this stage and it’s an individual fight. 6 pairs are chosen at random and pit against each other in a round of 1vs 1 coding. There is a chance that the team members may face each other. The person who finishes first in time in each of the 6 pairs proceeds to the next stage.\nStage 2: The advancing 6 are again matched up as pairs and pit against each other. The winners from each pair advances to the last stage.\nStage 3: CONTENT CLASSIFIED. BE PREPARED.\n\n");
			
		}
		
		
		
		
	}

			
		
		

	public  void updateTextValue(CharSequence newText) {
	    tv.setText(newText);
	    tv.setAnimation(AnimationUtils.loadAnimation(getActivity(),android.R.anim.fade_in));
		}
	

}
