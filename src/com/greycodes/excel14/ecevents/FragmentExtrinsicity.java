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


public class FragmentExtrinsicity extends SherlockFragment implements OnClickListener {
	ImageView intro,rules,format;
	 
	public  static TextView tv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.ec_extrinsicity, container, false);
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
			updateTextValue("Are you an electronic wiz? Does the world of electronics and circuits excite you? Then buckle up to face the ultimatum in the arena of electronics at excel 2k14. Challenge your intellect and analytical competence at the battle ground of 'Extrinsicity'.  The traps are set, Are u?\n\n" );
			
		}
		if (v.equals(rules))
		{
			updateTextValue("1. A team can have a maximum of three members only.\n2. Intercollegiate teams are allowed.\n3. Contestants should present their college ID cards.\n4. Winner of the teaser round should present their college ID card and the unique Excel ID obtained during registration. It is the sole discretion of the organizers to verify and approve of their participation.\n5. In case of a tie or incomplete output, winners will be judged based on their performance in the previous rounds.\n6. Decisions of the judges will be final and binding.\n7. All the necessary components and datasheets will be provided.\n\n");
			
		}
		
		if (v.equals(format))
		{
			updateTextValue("The event consists of 3 rounds. This will be a classic circuit based event. Circuit analysis and synthesis are the aspects being stressed on and the contestant's designing, wiring and analysing ability on electronic circuits is tested. The team coming in the last position of each stage will eliminated.\nOnline Teaser Round-\nThis online round will involve circuit designing and/or debugging. 2 weeks prior to Excel\n2014, a series of questions will be given. The top 5 teams from that round will be provided with a problem statement to solve and the team which brings out the best solution will be awarded with cash prize.\nRound 1: PRELIMS\nDuration: 30 minsdfcrowd.com\nContestants will be tested on the field of basic electronic circuits.\nThe one team which gets through the different stages will be lauded with laurels.\n8 Teams will be selected.\nRound 2 : CIRCUIT MARATHON\nDuration : 1.5 hrs\nThe top 8 teams chosen from the screening stage will take part in this round. Through 3 stages, 5 teams will be eliminated successively. In these stages, designing and wiring skills of the participants will be tested.\nRound 3 : DEBUGGING ROUND\nDuration :1.5 hrs\nThe top 3 teams of round two & 1 team from Online teaser contest in the final round. \nThe contestants will be required to debug an Analog/Digital circuit within the stipulated time. The team which debugs the circuit first will be declared as the winner. In case of tie or no output performance of teams in the second round is analysed, on the basis of which the final decision will be made.\n\n");
			
		}
		
		
		
		
	}
	

	public static void updateTextValue(CharSequence newText) {
	    tv.setText(newText);
		}
	

}