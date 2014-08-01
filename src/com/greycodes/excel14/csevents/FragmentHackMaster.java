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


public class FragmentHackMaster extends SherlockFragment implements OnClickListener {
	
	
	 ImageView intro,rules,format;
	 
	  static TextView tv;
     

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.cs_hackmaster, container, false);
		 
		 
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
			updateTextValue("�Most hackers are young because young people tend to be adaptable. As long as you remain adaptable�\nwhat�s life without a certain scandal? What�s power without some mischief? Living at the edge of the law? Be the vigilante that controls the code.\n\n" );
			
		}
		if (v.equals(rules))
		{
			updateTextValue("1.Professionals are NOT allowed to participate.\n2. Attacking or flooding the server will lead to disqualification.\n3. Multiple accounts from a participant are NOT allowed.\n4. Participants suspected of using unfair means WILL BE disqualified.\n5. Any misuse of the Hackmaster forum will lead to immediate disqualification\n6. The decisions and judgement of the coordinators will be final.\n7. Rules are subject to change at any point in time.\n\n");
			
		}
		
		if (v.equals(format))
		{
			updateTextValue("Hackmaster will be an online event. There will be different levels of problem with varying difficulty levels. The problems will test the concepts of Cryptography and steganography related problems 2. Hackmaster is an online, individual event.\n3.Different levels will carry different weightage of points according to difficulty.\n\n");
			
		}
		
		
		
		
	}

			
		
		

	public static void updateTextValue(CharSequence newText) {
	    tv.setText(newText);
		}
	

}
