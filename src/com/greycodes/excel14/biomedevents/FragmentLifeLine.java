package com.greycodes.excel14.biomedevents;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.greycodes.excel14.R;


public class FragmentLifeLine extends SherlockFragment implements OnClickListener {
	ImageView intro,rules,format;
	 
	  static TextView tv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.biomed_lifeline, container, false);
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
			updateTextValue("A unique event that lets you envisage yourself as a life saver with a quest to prove your adroitness in debugging. Examine and “operate”the impaired medical instrument still connected to the patient. It’s time to act. It’s time to save.\n\n" );
			
		}
		if (v.equals(rules))
		{
			updateTextValue("1.	The competition is held for teams of 2 members.\n2.	Cross-college teams ARE allowed.\n3.	Failing to save the 'patient' in given time results in immediate elimination.\n4.	The coordinators of the event reserve the right to take appropriate decisions in case of any issues/conflicts.\n5.	Rules are subject to change at any point in time. All rules will be explained at the event as well.\n6.	The decision of the judges would be final and binding.\n\n");
			
		}
		
		if (v.equals(format))
		{
			updateTextValue("Lifeline consists of 3 stages and will test participants on their knowledge of electronics, its applications in the field of biomedical engineering and their ability to diagnose and debug problems related to a circuit as quickly as possible.\nFIRST LEVEL:\n1.	This round consists of a 30 minute MCQ test.\n2.	the test will contain electronics and biomed related questions.\n3.	Six teams selected from this round will proceed to the next round.\nSEMI FINALS:\n1.	Each team will be given 6 tasks to complete.\n2.	A time of 5 minutes will be allotted for each task.\n3.	The groups completing the maximum no of tasks move to the finals.\nFINALS:\n1.	Each team will be given one circuit to design.\n2.	The team completing the design with correct output within the time allotted will be declared winners.\n\n");
			
		}
		
		
		
		
	}
	

	public static void updateTextValue(CharSequence newText) {
	    tv.setText(newText);
		}
	

}	