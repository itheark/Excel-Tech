package com.greycodes.excel14.csevents;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.greycodes.excel14.R;


public class FragmentLOC extends SherlockFragment implements OnClickListener {
	
	 ImageView intro,rules,format;
	 
	  static TextView tv;
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.cs_loc, container, false);

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
			updateTextValue("“Talk is cheap, show me the code ” – Linus Torvalds The ‘Lord of the Code’ is a prize that only the deserving receive. A title that only the mostbrilliant of those coders who use their knowledge and acumen are bestowed with. If you think that you are worthy of this honour, be ready to manipulate lines of code to outwit and outshine your opponents and rise as the one true Lord of the Code.\n\n" );
			
		}
		if (v.equals(rules))
		{
			updateTextValue("1. Each team can have a maximum of two members. Cross - college teams are allowed.\n2. Decision of the judges and organizers will be final.\n3. Final round participants can use C, C++, Java or Python for coding. \n4. Logical questions focus more on better logic and less on completion time. \n5. Coding focuses equally on the completion time and the logic. \n6. Rules are subject to change at any point in time. All rules will be explained at the event as\n\n");
			
		}
		
		if (v.equals(format))
		{
			updateTextValue("1. Preliminary Round - MCQ round of 30 questions \n(Top 14 teams move to the second round) \n2. Final Round- consists of two rounds running parallel to each other. Half of the teams will solve logical questions while the other half will be coding . At exactly 90 minutes the roles will be switched.\nBoth the rounds will have 2 questions each and the winner will be decided by the most points scored .\n\n");
			
		}
		
		
		
		
	}

			
		
		

	public static void updateTextValue(CharSequence newText) {
	    tv.setText(newText);
		}
	}
