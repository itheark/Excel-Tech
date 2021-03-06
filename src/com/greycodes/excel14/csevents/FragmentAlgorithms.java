package com.greycodes.excel14.csevents;

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


public class FragmentAlgorithms extends SherlockFragment implements OnClickListener {
	
	 ImageView intro,rules,format;
	 
	  public static TextView tv;
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.cs_algorithm, container, false);
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
			updateTextValue("Even the hardest problems in this world are solved with the most simplest of logic. If complex algorithms are just a child�s play for you, then get ready to prove yourself at Excel �Algorithms�. Amaze us with your efficient plans and you might be the one to land your hands on that handsome cash prize.\n\n" );
			
		}
		if (v.equals(rules))
		{
			updateTextValue("1.It is not mandatory that the team members should be from the same college.\n2. Use of mobile phones will not be allowed during the event.\n3.The time received by each participant for coding depends on the cycle (Refer Event Format)\n4.The team that codes the most optimal solution in the shortest time will be declared as winners.\n5.Programs with least complexity would be given preference.\n6.During the round the teams can contact the coordinators or the volunteers in case of emergency.\n7.Rules are subject to change at any point in time. All rules will be explained at the event.\n8.Decision of the judges will be final.\n\n");
			
		}
		
		if (v.equals(format))
		{
			updateTextValue("The game is for everyone with Analytical and Logical Skills, in which participants will be given questions based on Algorithms. Various factors such as; complexity, efficiency, speed, resource utilization (time and speed) are considered for final judging.\nThere will be 2 rounds of 2 or 4 per team.\nPreliminary Round\n1.	The prelims consist of 60 MCQ questions with 2 minutes per questions.\n2. There will be 5 questions descriptive type, 25 easy questions, 20 intermediate questions and 10 Hard Questions.\nFinal Round\n1. Final Round will be Coding Round with C/C++ as languages.\n2. The answers will be executed and the time and space complexity of Algorithm is calculated.\n3.   	Winners will be decided on factors including, First to Finish, Most Finish Questions and Most Efficient\n\n");
			
		}
		
		
		
		
	}

			
		
		

	public  void updateTextValue(CharSequence newText) {
	    tv.setText(newText);
	    tv.setAnimation(AnimationUtils.loadAnimation(getActivity(),android.R.anim.fade_in));
		}
	

}

