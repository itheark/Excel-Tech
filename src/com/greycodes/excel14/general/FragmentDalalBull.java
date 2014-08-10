package com.greycodes.excel14.general;

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


public class FragmentDalalBull extends SherlockFragment implements OnClickListener {
	ImageView intro,rules,format;
	 
	public  static TextView tv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.general_dalalbull, container, false);
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
			updateTextValue("Is it your whim to surmount the arena of stock market? Do the words ‘investment’ and ‘trade’ invigorate you? THEN come unveil the crafty businessman in you, manage your own portfolio and debacle your competitors in a risk-free environment. The dynamic simulation of virtual stock market awaits the real dealer!\n" );
			
		}
		if (v.equals(rules))
		{
			updateTextValue("\n\n");
			
		}
		
		if (v.equals(format))
		{
			updateTextValue("\n\n");
			
		}
		
		
		
		
	}
	

	public  void updateTextValue(CharSequence newText) {
	    tv.setText(newText);
	    tv.setAnimation(AnimationUtils.loadAnimation(getActivity(),android.R.anim.fade_in));
		}
	

}

