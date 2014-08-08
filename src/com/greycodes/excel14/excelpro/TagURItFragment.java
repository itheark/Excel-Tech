package com.greycodes.excel14.excelpro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.greycodes.excel14.R;

public class TagURItFragment extends Fragment implements OnClickListener {
	
	 ImageView intro,rules,format;
	 
	  static TextView tv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.excelpro_tagurit, container, false);
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
			updateTextValue("Are you one of those people whose taglines under photos and status updates gain double-digit or even triple-digit ‘likes’? Do you produce witty comments and cool taglines at the drop of a hat?\nWell, even if you don’t,’ TAG! You’re IT’ will offer you hours of amusement. Come up with witty and relevant taglines for the images provided, that too from the comfort of your homes (or offices) and you could win some serious cash here.\n\n" );
			
		}
		if (v.equals(rules))
		{
			updateTextValue("Professionals from any field can participate in the event. No age limit.\nEach participant can provide only one tag line for a particular image.\nTaglines once entered is final and cannot be edited again.\nRules are subject to change at any point in time.\nThe organizers reserve the right to make decisions in case of conflicts or any issues. \n\n");
			
		}
		
		if (v.equals(format))
		{
			updateTextValue("One image per day will be put up on the site for the entire duration of the event. Contestants will have to enter an appropriate tagline for the image on the same day itself in the space provided. The best tagline for the image is selected and exciting prizes await the daily winners.\nThe captions / taglines may be witty, humourous or simply thought provoking! So go on ahead… Tag! You’re IT!\nJudging Criteria\nCreativity\nOriginality\nAptness\nUnconventionality\n\n");
			
		}
		
		
		
		
	}

			
		
		

	public static void updateTextValue(CharSequence newText) {
	    tv.setText(newText);
		}
	

}

