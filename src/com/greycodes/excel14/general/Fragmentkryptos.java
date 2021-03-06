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


public class Fragmentkryptos extends SherlockFragment implements OnClickListener {
	ImageView intro,rules,format;
	 
	public  static TextView tv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.general_kryptos, container, false);
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
			updateTextValue("The world is full of obvious things, which nobody by chance ever observes. - Sherlock Holmes\nAre you ready to plunge into the vivid world of mysteries?  Are you game to explore the hidden and unseen? Use your nuts to unlock the krypt. Extricate the tricky puzzles and expound the mysterious clues.  Battle it out in the race to discover the ultimate treasure!\nNOTE: Think deep�.Only the astute, intelligent and versatile make it to the end.\n " );
			
		}
		if (v.equals(rules))
		{
			updateTextValue("1.The game will begin 2 weeks before Excel 2014.\n2.This is an individual event.\n3.Anyone who gives away an answer will be disqualified for the prize and will be banned from further participation.\n4.Decision of the judges shall be final.\n\n");
			
		}
		
		if (v.equals(format))
		{
			updateTextValue("1.This is a multi-level game that tests your skill over several levels spread over two weeks.\n2.This is an individual event.\n\n");
			
		}
		
		
		
		
	}
	

	public    void updateTextValue(CharSequence newText) {
	    tv.setText(newText);
	    tv.setAnimation(AnimationUtils.loadAnimation(getActivity(),android.R.anim.fade_in));
		}
	

}

