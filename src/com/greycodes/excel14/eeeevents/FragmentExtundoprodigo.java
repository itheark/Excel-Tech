package com.greycodes.excel14.eeeevents;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.greycodes.excel14.R;


public class FragmentExtundoprodigo extends SherlockFragment implements OnClickListener {
	 ImageView intro,rules,format;
	 
	  static TextView tv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.eee_extundoprodigo, container, false);
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
			updateTextValue("“Creativity is seeing what everybody has seen and thinking what nobody has thought.”\nThe moment has come to give wings to your imagination. Use your expert electrical and mechanical skills as a weapon to crown yourself as the master creator; give birth to your own gadget, crack the problem and dare your opponents. The crowd awaits the gadget of the day!\n\n" );
			
		}
		if (v.equals(rules))
		{
			updateTextValue("1. Cross college teams allowed and each team may contain upto a maximum of 4 members.\n2. Participants must be aware of basic workshop safety and operational precautions.\n3. Participants are not permitted to use any external component other than that provided by the coordinators.\n4. Decision of the judges will be final and binding.\n5. Excel team reserves the right to take the final decisions in case of any dispute.\n7. Basic tools like pliers,wire strippers,screw drivers will be provided.\n8. Excel team is not responsible for any loss of property, injury and delays caused by participants during the event.\n9. Rules are subject to change at any point in time. \n\n");
			
		}
		
		if (v.equals(format))
		{
			updateTextValue("Level 1 : Prelims\nWritten test conducted based on basic electrical & mechanical engineering and aptitude .Top 8 teams move to next round.\nLevel 2 : Design\nEach team given a problem statement and four types of arena chosen on basis of picking slots. Teams are shown their arena as well as the components they receive. Teams are required to design a gadget according to the given problem statement using the available components.\nLevel 3 : Construction\nRealization of the design produced in level 2. Components will be provided and the machine will be tested. Teams can spend two hours in the workshop.\nLevel 4 : Finals\nThe finalist teams compete with each other using their gadgets at the arena.\nStage 1:\n8 teams compete with each other using their gadgets at the primary arena. Four of them are selected for stage 2 based on the time taken to complete the path.\nStage 2:  Four finalists compete simultaneously on the main arena.\n\n");
			
		}
		
		
		
		
	}

			
		
		

	public static void updateTextValue(CharSequence newText) {
	    tv.setText(newText);
		}
	

}

