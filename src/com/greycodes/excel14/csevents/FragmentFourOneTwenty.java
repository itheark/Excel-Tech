package com.greycodes.excel14.csevents;



import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
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


public class FragmentFourOneTwenty extends SherlockFragment implements OnClickListener {
	
	String url= "http://greycodes.com/json/data_new3.json";
	 ImageView intro,rules,format;
	 
	 public static TextView tv;    
View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 rootView = inflater.inflate(R.layout.cs_fouronetwenty, container, false);
		 
		 
		 
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
			updateTextValue("4 people AND 120 seconds of synchronous understanding and unrivalled teamwork separate the winners from the others. Start from where your predecessor left off, and better his effort to win the title. Time is of the essence and so is the sync between the 4! \n\n" );
			
		}
		if (v.equals(rules))
		{
			updateTextValue("1. Each team should have exactly four members.\n2. It is not mandatory that the team members should be from the same college.\n3. Participants will need to know C, C++.\n4. The participants of a team will not be allowed to communicate with one another once\n5. Use of mobile phones will not be allowed during the event.\n6. The time received by each participant for coding depends on the cycle (Refer Event)\n7. The team that codes the most optimal solution in the shortest time will be declared as\n8. Programs with least complexity would be given preference.\n9. During the round the teams can contact the coordinators or the volunteers in case of\n10. Rules are subject to change at any point in time. All rules will be explained at the event \n11 Decision of the judges will be final.\n\n");
			
		}
		
		if (v.equals(format))
		{
			updateTextValue("This on-the-spot event bears resemblance to a 4*100m athletic relay race. Four members ofthe team with one relay which is your computer. Each member makes an attempt at solving the problem statement by continuing from where the previous member left off.The fastest and the smartest team shall win the race.The event will comprise of two stages.\n1. Consists of both objective and subjective type tests. After the preliminary round, eight teams with the highest scores will be selected for the final round.\n2. Comprises of 3 questions. The team can skip a question and move on to the next question, but no discussion would be allowed within the team. The decision of the team member is applicable to the whole team.\n3. The team members must decide which order they wish to follow before the final round question has been provided, and will not be allowed to change this order\n4. The question will be provided to each member only at the start of their time slot, and not before the first member's time slot. Once the question has been provided, each member can code for 3 minutes at the beginning.\n5. In the first 30 minutes, each member can code for 3 mins. In the next 30 mins,the coding time increases to 4 mins. In the next 30 minutes, coding takes place for 5 minutes. In the final 30 minutes, each member gets 2 minutes to code.\n6. Duration for the final round is two hours. The teams that get the desired output in the shortest time stand a chance to win the prize money.\n\n");
			
		}
		
		
		
		
	}

			
		
		

	public void updateTextValue(CharSequence newText) {
	    tv.setText(newText);
	    tv.setAnimation(AnimationUtils.loadAnimation(getActivity(),android.R.anim.fade_in));
		}
	
	
}

	
	
	
	
		
		
	


