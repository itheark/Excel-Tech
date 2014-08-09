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


public class FragmentWebBots extends SherlockFragment implements OnClickListener {


	 ImageView intro,rules,format;
	 
	 public static TextView tv;	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.cs_webbots, container, false);
		 
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
		updateTextValue("“We’ve all played the game ‘Snake’. Haven’t we? We were all addicted to “Snake” at some point of our lives. Guiding a snake to eat as much food it can without hitting the walls and its own ever growing length was always exciting.\n\n" );
		
	}
	if (v.equals(rules))
	{
		updateTextValue("1. Each player can enter only one bot for the final tournament, although you could modify the code for your bot, your final saved code would be used for the competition.\n2. Players would be disqualified if they have been found to tamper with the site or the system.\n3. Rules are subject to change at any point in time.\n4. The decision of the Web-Bots team would be final.\n\n");
		
	}
	
	if (v.equals(format))
	{
		updateTextValue("Web Bots will be an online multi-level game that tests your skill over several levels spread Players are to code an AI bot for a game. (Don't worry, we've made it darn easy to write abot).These bots are pitted against each other and the bot that wins, advances to the nextround. The bot who wins all the rounds, bags the prize.The game is simple. Bots are two coloured cells on an NxN grid. Bots leave a coloured trail wherever they move. A bot must not hit its own trail or that of another and also the walls.Got it? Those are the bots that you'll be coding.\n\n");
		
	}
	



}

		
	
	

public static void updateTextValue(CharSequence newText) {
    tv.setText(newText);
	}
}

