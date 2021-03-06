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
			updateTextValue("If You control the code,You control the world\nAnybody can code, So can you! Or can you?For the first time at Excel, show off your coding skills at 'So you think you can code' with brand-new rules and surprises, defying the usual norms, this event will challenge you, surprise you and test you till you break or fight back! Get �Set- code!\n\n" );
			
		}
		if (v.equals(rules))
		{
			updateTextValue("1.It is not mandatory that the team members should be from the same college.\n2.Participants will need to know C, C++.\n3.The participants of a team will not be allowed to communicate with one another once.\n4.Use of mobile phones will not be allowed during the event.\n5.The time received by each participant for coding depends on the cycle (Refer Event Format)\n6.The team that codes the most optimal solution in the shortest time will be declared as winners.\n7.Programs with least complexity would be given preference.\n8.During the round the teams can contact the coordinators or the volunteers in case of emergency.\n9.Rules are subject to change at any point in time. All rules will be explained at the event.\n10.Decision of the judges will be final.\n\n");
			
		}
		
		if (v.equals(format))
		{
			updateTextValue("The idea behind this on spot event is to find the best coder.\nThe event comprises of 2 rounds.\nPreliminary Round: Teams of 2 are put through a screening process consisting of standard objective questions. Top 6 teams qualify for the final round.\nFinal Round:The final stage consists of 3 stages.\nStage 1: Teams are broken down at this stage and it's an individual fight. 6 pairs are chosen at random and pit against each other in a round of 1vs 1 coding. There is a chance that the team members may face each other. The person who finishes first in time in each of the 6 pairs proceeds to the next stage.\nStage 2: The advancing 6 are again matched up as pairs and pit against each other. The winners from each pair advances to the last stage.\nStage 3: CONTENT CLASSIFIED. BE PREPARED.\n\n");
			
		}
		
		
		
		
	}

			
		
		

	public  void updateTextValue(CharSequence newText) {
	    tv.setText(newText);
	    tv.setAnimation(AnimationUtils.loadAnimation(getActivity(),android.R.anim.fade_in));
		}
	

}
