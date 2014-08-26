package com.greycodes.excel14.excelpro;

import android.content.Intent;
import android.net.Uri;
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

public class TagURItFragment extends Fragment implements OnClickListener {
	
	 
	 ImageView bky ;
	public  static TextView tv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.excelpro_tagurit, container, false);
		 tv=(TextView)rootView.findViewById(R.id.txtinclude);
		bky=(ImageView)rootView.findViewById(R.id.buckylink);
		bky.setOnClickListener(this);
		return rootView;
		
		
		
		
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.equals(bky)){
			String url = "https://play.google.com/store/apps/details?id=com.gc.freefall";
			 Intent i = new Intent(Intent.ACTION_VIEW);
			 i.setData(Uri.parse(url));
			 startActivity(i);
			
			
		}
		
		
		
		
		
	}

			
		
		

	public  void updateTextValue(CharSequence newText) {
	    tv.setText(newText);
	    tv.setAnimation(AnimationUtils.loadAnimation(getActivity(),android.R.anim.fade_in));
		}
	

}

