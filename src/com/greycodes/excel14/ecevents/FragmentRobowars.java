package com.greycodes.excel14.ecevents;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.greycodes.excel14.R;


public class FragmentRobowars extends SherlockFragment implements OnClickListener {
	ImageView intro,rules,format;
	 
	  static TextView tv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.ec_robowars, container, false);
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
			updateTextValue("“The moment has come to attack, endure, withstand and sustain! Its time to face some spine chilling action! Plunge yourselves in the sounds of metal bashing and chainsaws ripping! Witness the epic battle of machines, the ultimate combat, to crown the ultimate survivor!  Come.compete.conquer!\n\n" );
			
		}
		if (v.equals(rules))
		{
			updateTextValue("1. Each team can have up to four members.\n2. Registrations close at 9.30 am.\n3. It is not mandatory for all the members of a team to be from the same institution.\n4. The Robot must be manually controlled through wired/wireless interface.\n5. Maximum permitted dimensions of the robot are 45 X 45 X 45 cm and its weight must not exceed 10kg.\n6. A power supply of 24V / 4A will be provided; or else each robot may have its own power supply not exceeding 24V.\n7. Far-fetched ideas of implementing fire, lubricants etc. for attack will not be entertained.\n8. All teams will have to have their bots inspected by the coordinators both before and after each stage.\n9. Teams are permitted to make minor changes to the body of their robot after every stage if they wish; but modifying the entire body of the robot will not be permitted.\n10. Arena dimensions will be disclosed only on the day of the event.\n11. Only two members from each team are allowed to operate the bot during each stage.\n12. Any team/team member found disrespecting the rules/other competitors, will be      disqualified\n13. The decision of the judges will be final and binding.\n14. For wired robots the length of the wire should be minimum of four meters, failing of which fetch the opponent 50 points.\n15.Rules are subject to change at any point in time. All rules will be explained at the event as well.\n16.The decision of the judges and organizers will be final and binding.\n\n");
			
		}
		
		if (v.equals(format))
		{
			updateTextValue("Round 1 (Preliminary Round)\n1.	Each robot will be allotted an opponent and each pair of opponents will take on each other in a one-on-one fight and the winner is the one with higher points at the end of the battle. The winner proceeds to the next round.\n\n2.	Points are awarded based on the following fields - speed, skill, controlling ability and strength.\n3.	Starting from its home territory each bot will have to travel a narrow tricky path and cross the battlefield onto the other side of the arena, in the course of which it will find itself face-to-face with an opponent on the same mission.\n\n4.	Each bot will have to travel through a muddy, rough and slippery surface in order to reach the battlefield. The bot quicker to accomplish this will receive bonus points.\n5.	Once the bot reaches the battlefield, it has to fend off the opponent bot from the arena, overcoming obstacles on the way. The bot selecting the more dangerous path will receive bonus points.\n6.	If any bot remains tangled in the hurdles for more than an allotted time, the opponent will be rewarded points.\n7.	Match Duration - 5 minutes.\nPoint Distribution:\n1.       Crossing the territory of the opponent – 30 points\n2.       Handling all the difficulties in the path and entering to the height – 60 point\n3.       Knocking the opponent off the arena/immobilising it for 10 seconds - 30 points\nRound 2\nThe rules are similar to that of Round 1. The arena will have undergone some changes and the challenge will, obviously, be greater. Here the bots get a chance to showcase the actual power of their armour and their skills. The overall ability of the bot will be validated. At the end of this round, only 4 bots will proceed to fight the next and final round. They will be the ones with the highest points.\n\nRound 3 (The Semi-finals)\n1.	One-on-One Knockout.\n2.	There will be two semi-finals.\n3.	The winners of the two semi-finals will qualify to the Grand Finale.\n4.	Time limit of this round will be 10 minutes.\nPoint Distribution:\n1.	Knockout – 10 point.n\2.	Immobilising the opponent for 15 seconds – 10 point.\nRound 4 (The Grand finale)\n1.	One on one knockout round.\n2.	10 minute battle.\n3.	The bot with the maximum points wins.\n4.	The arena will be modified with more obstacles.\nPoint Distribution:\n1.	Knockout – 10 point.\n2.	Immobilising the opponent for 15 seconds – 10 point.\n3.	Entering at the height first and remaining there for 10 sec-10point\nNOTE: POINTS ARE NOT CARRIED TO THE NEXT LEVEL. Higher points at the end of a round do not guarantee any leverage in the next round(s).\n\n");
			
		}
		
		
		
		
	}

			
		
		

	public static void updateTextValue(CharSequence newText) {
	    tv.setText(newText);
		}
	

}

