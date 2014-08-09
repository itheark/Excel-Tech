package com.greycodes.excel14.robotics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.greycodes.excel14.R;


public class FragmentTerrainMaster extends SherlockFragment implements OnClickListener {
	 ImageView intro,rules,format;
	 
	 public  static TextView tv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.ec_terrainmaster, container, false);
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
			updateTextValue("Will your robot be the master of this one event which involves exploring treacherous paths, defeating tricky hurdles and enduring the unexpected twists to conquer any terrain?  Here, survival is the ultimate key to victory!\n\n" );
			
		}
		if (v.equals(rules))
		{
			updateTextValue("1. It is not mandatory for all the members of a team to be from the same educational institution.\n2. A team can have a maximum of up to 4 members.\n3. The participants can view the actual arena an hour prior to the event and will be permitted to conduct trial runs.\n4. The teams must not make use of lego kits for building the bot.\n5. bot size should not exceed a size of 30x30x30 cm.\n6. The weight of the bot should not exceed 10 kg.\n7. All teams will have to have their bots inspected by the coordinators before the start of the event.\n8. Dimensions of the arena will be disclosed on the day of the event.9. Members of a team showing disrespect to the rules or to the other competitors, shall cause immediate disqualification of his/her team.\n10. Incase of a situation where no team completes the task, then the team which has completed more of the task (having the maximum points) will be declared as the winner.\n11. Request for a restart may be denied by the judges.\n12. decision of the judges will be final and binding.\n13. Rules are subject to change at any point in time. All rules will be explained at the event as well. \n\n");
			
		}
		
		if (v.equals(format))
		{
			updateTextValue("Teams are required to build a wireless/wired robot which is capable of navigating an obstacle filled environment within the least possible time. The event itself comprises of one single round. Each team is allotted a duration of 15 minutes to complete the task (i.e finish the race) and will get only three chances. The bot will have to start from the initial position, navigate the path by itself, avoid all obstacles and get to the finishing point. Each team will initially be given 50 points. In case of the bot touching the boundary, or in case of each chance after the first, points would be deducted. The team that completes the event in the shortest time compared to other teams would earn bonus points. The team having the most points at the end of the event, is judged the winner\n\n");
			
		}
		
		
		
		
	}
	

	public static void updateTextValue(CharSequence newText) {
	    tv.setText(newText);
		}
	

}



			