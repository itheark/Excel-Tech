package com.greycodes.excel14.ecevents;

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


public class FragmentDefuse extends SherlockFragment implements OnClickListener {
	ImageView intro,rules,format;
	 
	public  static TextView tv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.ec_defuse, container, false);
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
			updateTextValue("Have you ever imagined accomplishing the dangerous feat of defusing a bomb? DEFUSE gives you the once-in-a-lifetime opportunity to fulfil that dream! A time bound event, where your perseverance and teamwork will be put to test. Analyse, crack and defuse the bomb before it blows. Watch out! The clock is ticking...\n\n" );
			
		}
		if (v.equals(rules))
		{
			updateTextValue("1.	A team may have a maximum of three members.\n2.	Cross - college teams are allowed.\n3.	Contestants should present their college ID cards.\n4.	All required components and data-sheets will be provided. \n5.	No external components may be used.\n6.	Any team not abiding by the rules of the event will be immediately disqualified.\n7.Decision of the judges  and organizers shall be final and binding.\n8.	In case of a tie or incomplete output, a viva-voce will decide the winner.\n9.	Rules are subject to change at any point in time. All rules will be explained at the event as well.\n\n");
			
		}
		
		if (v.equals(format))
		{
			updateTextValue("The event consists of 3 rounds.\nPreliminary Round\n1.	This round consists of a 30 minute MCQ test.\n2.	Questions will test the participants’ knowledge in basic digital electronics.\n3.	Six teams selected from this round will proceed to the next round.\nRound 1 (Circuit Analysis):\n1.	This round is based on circuit analysis where the participants are expected to identify a pattern to start ‘defusing’ the bomb.\n2.	The participants will be given a diagram of the circuit from where they’ll have to figure out the pattern within 30 minutes.\n3.	Once the pattern is identified, they may use it to initiate the timer on the bomb and start defusing.\n4.	First 4 teams who figure out the right pattern will advance to the next round.\nRound 2 (Bomb Defusal):\n1.	Participants will be provided with the bomb’s circuit diagram.\n2.	The time allotted is 60 minutes.\n3.	The first team to successfully defuse the bomb will be declared winners.\n4.	If no team is able to defuse the bomb at the end of all the rounds, winners will be decided based on a viva voce.\n\n");
			
		}
		
		
		
		
	}
	

	public  void updateTextValue(CharSequence newText) {
	    tv.setText(newText);
	    tv.setAnimation(AnimationUtils.loadAnimation(getActivity(),android.R.anim.fade_in));
		}
	

}

