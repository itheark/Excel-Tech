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

public class DefactoFragment extends Fragment implements OnClickListener {
	public  static TextView tv;
	ImageView intro;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.nt_defacto, container, false);
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
			updateTextValue("Defacto was born out of Excel's ambition to reach out the young Einsteins and Madam Curies in school. A quiz aimed solely at school students, Defacto promises to be a fun and interactive session, combining the innovation of college quizzing and the inquisitiveness of school quizzing. \nLet your intellect shine, let the rolling of brain race with every passing of minute and get yourselves ready for a gem of a quiz!\n");
			
		}
		
		
		
		
	}
	

	public void updateTextValue(CharSequence newText) {
	    tv.setText(newText);
	    tv.setAnimation(AnimationUtils.loadAnimation(getActivity(),android.R.anim.fade_in));
		}
	

}

