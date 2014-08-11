package com.greycodes.excel14.excelpro;

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

public class MarketManiaFragment extends Fragment implements OnClickListener {
	
	 ImageView intro,rules,format;
	 
	public  static TextView tv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.excelpro_marketmania, container, false);
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
			updateTextValue(" �Business has only two functions - marketing and innovation.�- Milan Kundera\nBut you need both, some knowledge, some experience and a pinch of wisdom, in order to survive the gauntlet that is �Market Mania�. That�s the reason why the event is open to only professionals.\nProve your marketing acumen and troubleshooting skills, as you take on other professionals in an epic battle to decide who will wear the marketing crown.\n\n" );
			
		}
		if (v.equals(rules))
		{
			updateTextValue("Teams should comprise of 2 members, who need not necessarily be from the same company and can be post graduates.\nThere is no age limit for the participants.\nAny content used in any of the rounds must meet professional standards and must not contain any obscene content.\nThe teams will be allowed to participate in the on-site rounds only after proof of identity and their company ID are submitted which will be verified by the event coordinators.\nThe teams must bring their own laptops and other software required to make presentations, videos etc.\nDecision of judges and organizers will be final and binding.\nTeams not following the above said rules and regulations will be liable for disqualification at any point during the event.\nRules are subject to change at any point in time. All rules will be explained at the event as well.\nRegistration details will be announced soon\n\n");
			
		}
		
		if (v.equals(format))
		{
			updateTextValue("Market Mania is split into 3 rounds\nRound 1 (Trivia Round)\nJust a lot of fun trivia, puzzles and interesting conundrums to help you unwind and set the stage for a day of fun, marketing and competition.\nRound 2 (Elevator Pitch)\nHow long would it take to convince a client that your product is the best on the market? Well, it shouldn�t take any longer than an elevator ride with a potential client and any marketer worth his/her salt will tell you it�s all about convincing, communicating and making the best pitch. Try your hand at it!\nRound 3 (Invest or Divest!)\nThe highlight round of the event. Do you have what it takes to make the wisest investment choice? Represent an investment group, study company profiles, make the best investment and pitch for your choice.\n\n");
			
		}
		
		
		
		
	}

			
		
		

	public  void updateTextValue(CharSequence newText) {
	    tv.setText(newText);
	    tv.setAnimation(AnimationUtils.loadAnimation(getActivity(),android.R.anim.fade_in));	
	}
	

}

