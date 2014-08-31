package com.greycodes.excel14.ecevents;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.greycodes.excel14.CompetitionNDActivity;
import com.greycodes.excel14.R;


public class FragmentCircuim extends SherlockFragment implements OnClickListener {
	ImageView intro,rules,format;
	 
	public  static TextView tv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.ec_circuim, container, false);
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
			updateTextValue("Do you find yourself enriched with the spirit of a true engineer? Does your mind ponder upon ways to solve real life problems with your technical cognizance? Circuimstance is here with  the right key to unlock your whim to explore the world of engineering !\nTricky tasks await your call.. Keep calm and slay them all!\nConfront the problem.Concoct the answer.Conquer the title.\nThink Complicated.Expect the unexpected! Think complicated.\n\n" );
			
		}
		if (v.equals(rules))
		{
			updateTextValue("1.	The event will be a team event with each team comprising of three or four members.\n2.	All the members of a team need NOT be from the same college.\n3.	College ID cards are mandatory at the time of participation.\n4.	All the required components and datasheets will be provided. NO external components are allowed.\n5.	Use of mobile phones during the event is not allowed.\n6.	The decision of the judges will be final and binding.\n7.	Any team that does not abide by the rules will be suspended immediately.\n8.	The rules are subject to change. The rules will be explained before the start of the event as well.\n\n");
			
		}
		
		if (v.equals(format))
		{
			updateTextValue("The event will be a team event with a team of 3 or 4.\n1.	The event has total 3 rounds, where the first round is a prelims and the third round is the final.\n2.	We use a simple and attractive card based system to decided the finalists. One of three coloured cards with different weights will be awarded on successful completion of each task.\nROUND #1\n1.	Some basic electronics questions to test your knowledge and thinking abilities.\n2.	Only the worthy pass through.\nROUND #2: \n1.	You can do it as a team itself.\n2.	Some basic tasks to test the skills at an application level.\n3.	The top four teams go into the final round.\nROUND #3\n1.	The final round! Your ability to apply your knowledge in a real world scenario is tested.\n2.	All your skills as an engineer is tested here.\n3.	Do you call yourself an engineer?? Come and prove it!!\n4.	Find out the rest for yourself by coming to EXCEL 2014 ;)\n\n");
			
		}
		
		
		
		
	}
	

	public  void updateTextValue(CharSequence newText) {
	    tv.setText(newText);
	    tv.setAnimation(AnimationUtils.loadAnimation(getActivity(),android.R.anim.fade_in));
		}
	

}

