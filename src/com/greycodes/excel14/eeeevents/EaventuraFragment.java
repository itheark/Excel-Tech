package com.greycodes.excel14.eeeevents;

import com.greycodes.excel14.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class EaventuraFragment extends Fragment implements OnClickListener {
	 ImageView intro,rules,format;
	 
	public  static TextView tv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.eee_eaventura, container, false);
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
			updateTextValue("'Are you ready for an adventure?\nTo unravel the mysterious clues.. To explore the hidden secrets'\nPolish your technical know-how and get ready to crack the concealed, for we bring you the ever challenging technical treasure hunt of excel 2014!\nTread forward by solving the puzzles leading you to the ultimate treasure!\nIt�s time to pool your knowledge, play your best and prove your excellence!\n\n" );
			
		}
		if (v.equals(rules))
		{
			updateTextValue("1.  A team may have a maximum of four members.\n2.  It is not mandatory for all the members of a team to be from the same institution.\n3. Contestants should present their college ID cards.\n4.All the required components and data-sheets will be provided. No external components   may be used.\n5.  Any team not abiding by the rules of the event will be immediately disqualified.\n5. In case of a tie or incomplete output, a viva-voce session will be conducted.\n\n");
			
		}
		
		if (v.equals(format))
		{
			updateTextValue("This Event Consist Of 2 levels\nLevel 1\n1.  This stage consists of a 30 minute MCQ test.\n2. Questions will test the participant�s knowledge in basic electrical and logical reasoning skills.\n3. Six teams selected from this round will proceed to the next stage.\nLevel 2\n1.The teams which cleared the first level move on to the second level which is a series of electrical and electronics tasks connected to each other by a set of clues. \nThis is a knockout level in which the number of teams are filtered on each of the tasks based on their completion speed .\nTwo teams avoiding knockout in these tasks will proceed onto the final round where they have to compete each other for the final treasure\n\n");
			
		}
		
		
		
		
	}

			
		
		

	public void updateTextValue(CharSequence newText) {
	    tv.setText(newText);
	    tv.setAnimation(AnimationUtils.loadAnimation(getActivity(),android.R.anim.fade_in));	
	}
	

}