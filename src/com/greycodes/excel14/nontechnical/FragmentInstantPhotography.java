package com.greycodes.excel14.nontechnical;

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


public class FragmentInstantPhotography extends SherlockFragment implements OnClickListener {
	ImageView intro,rules,format;
	 
	public  static TextView tv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.nt_instantphotography, container, false);
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
			updateTextValue("Photography is something that cuts across all boundaries, it transcends the constraints of language and culture. Are you an artist with a camera who has his own story to tell? Then show us your the photographs, not pictures just taken, but works of art that are painstakingly fashioned. For all those shutterbugs who believe their favourite personal photograph is the one.....\n");
			
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
	

	public   void updateTextValue(CharSequence newText) {
	    tv.setText(newText);
	    tv.setAnimation(AnimationUtils.loadAnimation(getActivity(),android.R.anim.fade_in));
		}
	

}

