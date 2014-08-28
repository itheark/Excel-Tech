package com.greycodes.excel14.eeeevents;

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


public class FragmentLumiere extends SherlockFragment implements OnClickListener {
	 ImageView intro,rules,format;
	 
	 public  static TextView tv;
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.eee_lumiere, container, false);
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
			updateTextValue("Wire your way to light the bulb of victory!\nEver wanted an opportunity to be able to apply basic wiring skills in a practical situation? Then...... Lumiere is the platform just for you! The challenge here is to come up with efficient solutions for a given wiring problem and all this .....while the 'clock is ticking'!!\n\n" );
			
		}
		if (v.equals(rules))
		{
			updateTextValue("1.Each team can have up to 2 members.\n2.Cross - college teams are allowed.\n3.The contestants must explain the design to the satisfaction of the judges and convince them to proceed to the implementation part.\n4. Any requests to view the corrected answer sheets of the preliminary round will not be entertained.\n5. All the required components and tools will be provided for the final round.\n6. Members of a team showing disrespect to the rules or to other competitors shall cause immediate disqualification of his/her team.\n7.Decision of judges will be final and binding. Rules are subject to change at any point in time. All rules will be explained at the event as well.\n\n");
			
		}
		
		if (v.equals(format))
		{
			
			updateTextValue("Prelims\nA 30 minute preliminary round will be conducted. Each team can have a maximum of2 members. 12 teams will be shortlisted for the next round.\nSecond round (time max 15min)\nIn this session each team will be given an electrical puzzle, basically a logical circuit. Each team will have to implement the circuit in conduit system using bulbs and wires. The first 8 teams to successfully complete the puzzle enters the final round.\nThird round-Design round\nIn the design round, competitors will have to debug the three errors (hard, medium, easy) given to in the wiring solution for a problem statement specified and redraw the complete wiring diagram considering economical and safety factors. Maximum time for designing is 25 minutes. Accuracy of solution will be evaluated and teams will also receive time bonus for arriving at the solution quickly. First 5 teams to successfully debug the errors in the circuit will enter the final round. Selection for 5 teams will be made depending on the fastness and completion of the design.\nFourth round-Implementation round\nIn this round, the five finalists will have to wire up the circuit in third round in conduit system within 15 minutes. Within a team, the first member will have to wire up a certain portion of the given circuit and the rest will be completed by the second.  The final result will be announced after considering the overall performance of the contestants in the third and fourth rounds.\n\n");
		}
		
		
		
		
	}

			
		
		

	public  void updateTextValue(CharSequence newText) {
	    tv.setText(newText);
	    tv.setAnimation(AnimationUtils.loadAnimation(getActivity(),android.R.anim.fade_in));
		}
	

}

