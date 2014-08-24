package com.greycodes.excel14.nontechnical;

import com.greycodes.excel14.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class FunZoneFragment extends Fragment implements OnClickListener {
	
	ImageView intro;
	 
	public  static TextView tv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.nt_funzone, container, false);
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
			updateTextValue("If its clean, unadulterated fun that you pursue, then this is the place for you. Crunchy Zone aka FunZone is the ultimate place to unwind and relax during your otherwise hectic life. \nWe guarantee you unlimited fun and awesome prizes at Kappothlon, a competition of nine fun rounds sponsored by Kappo Chips. Its fun to get paid while having fun. \n");
			
		}
		
		
		
		
	}
	

	public void updateTextValue(CharSequence newText) {
	    tv.setText(newText);
	    tv.setAnimation(AnimationUtils.loadAnimation(getActivity(),android.R.anim.fade_in));
		}
	

}

