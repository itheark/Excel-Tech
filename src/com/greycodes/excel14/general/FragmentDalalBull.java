package com.greycodes.excel14.general;

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


public class FragmentDalalBull extends SherlockFragment implements OnClickListener {
	ImageView intro,rules,format;
	 
	public  static TextView tv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.general_dalalbull, container, false);
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
			updateTextValue("Is it your whim to surmount the arena of stock market? Do the words ‘investment’ and ‘trade’ invigorate you? THEN come unveil the crafty businessman in you, manage your own portfolio and debacle your competitors in a risk-free environment. The dynamic simulation of virtual stock market awaits the real dealer!\n" );
			
		}
		if (v.equals(rules))
		{
			updateTextValue("1.This contest is open only for the students of recognized educational institutions. Professionals are not allowed to participate.\n2.Contestants who register with fake information will be disqualified.\n3.Same person cannot have multiple registrations. In such a case, either the contestant will be disqualified or all registrations except one will be deleted. It is the sole discretion of the organisers to decide on the course of action.\n4.Any bugs or anomalies found in DALAL BULL should be immediately reported to one of the coordinators. Contestants found to make use of any such bugs knowingly for their own gains, without reporting to the coordinators, will be disqualified.\n5.The winner should bring his/her college ID cards for claiming the prize. Prizes will be given only after verification of the information given by the contestant at the time of registration.\n6.In case of a tie, the prize money will be split appropriately.\n\n");
			
		}
		
		if (v.equals(format))
		{
			updateTextValue("Virtual money would be assigned to everyplayer to everyplayer who registers for this online stock exchange simulation.You may buy,sell,short sell and short cover the shares of 50 companies will live data feeds from NSE to draft your destiny in the market.A tutorial will equip you with all information necessary to play the game at the start.The only thing limiting your intensity of trading shares will be the virtual money that you have in hand.\n\n");
			
		}
		
		
		
		
	}
	

	public  void updateTextValue(CharSequence newText) {
	    tv.setText(newText);
	    tv.setAnimation(AnimationUtils.loadAnimation(getActivity(),android.R.anim.fade_in));
		}
	

}

