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


public class FragmentGameZone extends SherlockFragment implements OnClickListener {
	ImageView intro;
	 
	public  static TextView tv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.nt_gamezone, container, false);
		 tv=(TextView)rootView.findViewById(R.id.txtinclude);
		 intro=(ImageView)rootView.findViewById(R.id.intro);
		
		 
		 intro.setOnClickListener(this);
		
		return rootView;
		
		
		
		
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		
		if(v.equals(intro))
		{
			updateTextValue("Whats it gonna be? \nFIFA, Counter Strike, Call of Duty or DotA? Pick your favourite. \nReady? Get Set! ITS SHOWTIME!\nFeel the exhilarating adrenaline rush while you put your skills to test against hard core gamers. Team up, use whatever it takes to beat ‘em all and get to the top! \n" );
			
		}
		
		
		
		
		
	}
	

	public  void updateTextValue(CharSequence newText) {
	    tv.setText(newText);
	    tv.setAnimation(AnimationUtils.loadAnimation(getActivity(),android.R.anim.fade_in));}
	

}

