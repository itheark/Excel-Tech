package com.greycodes.excel14.initiatives;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.greycodes.excel14.R;

public class IbetoJrFragment extends Fragment implements OnClickListener {
	 ImageView intro,rules,format;
	 
	  static TextView tv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.initiatives_ibetojr, container, false);
		 
		return rootView;
	}
	
	


@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	
	
	
	
	
}

		
	
	

public  void updateTextValue(CharSequence newText) {
    tv.setText(newText);
    tv.setAnimation(AnimationUtils.loadAnimation(getActivity(),android.R.anim.fade_in));
	}
}




