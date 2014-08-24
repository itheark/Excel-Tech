package com.greycodes.excel14.info;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.animation.AnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.greycodes.excel14.Misc;
import com.greycodes.excel14.R;

public class HospitalityFragment extends Fragment implements OnClickListener, OnLongClickListener {
 ImageView call;
 public static TextView tv;
 RelativeLayout rr;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.info_hopitality, container, false);
		 rr=(RelativeLayout)rootView.findViewById(R.id.relative1);
		 call =(ImageView)rootView.findViewById(R.id.imageView4);
		 tv=(TextView)rootView.findViewById(R.id.txtinclude);
		 call.setOnClickListener(this);
		 tv.setOnClickListener(this);
		 call.setOnLongClickListener(this);
		 rr.setOnClickListener(this);
		return rootView;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.equals(call)){
			
			Toast.makeText(getActivity(), "Press & Hold to call", Toast.LENGTH_LONG).show();
			updateTextValue("Call Sajay Sunny ?\n");
			
			
		}
			if(v.equals(tv)){
				
				updateTextValue("As one of South India’s largest techno-managerial festivals, Excel is attended by participants from across the nation. The comfortable accommodation and hospitality of all our guests (from local areas to those from far-flung places in the country) are top priority to us. We strive to ensure that your stay here at Kochi, is as satisfying and cozy as possible.\nWe leave no stone unturned towards the satisfaction of all and in ensuring that your days at Excel 2014 are some of the most memorable experiences of your life.\n");
			}
			if(v.equals(rr)){
				
				updateTextValue("As one of South India’s largest techno-managerial festivals, Excel is attended by participants from across the nation. The comfortable accommodation and hospitality of all our guests (from local areas to those from far-flung places in the country) are top priority to us. We strive to ensure that your stay here at Kochi, is as satisfying and cozy as possible.\nWe leave no stone unturned towards the satisfaction of all and in ensuring that your days at Excel 2014 are some of the most memorable experiences of your life.\n");
			}
		
	}
	
	@Override
	public boolean onLongClick(View v) {
		
		
if(v.equals(call)){
			String number ="09495177360";
	Intent intent = new Intent(Intent.ACTION_CALL);
	intent.setData(Uri.parse("tel:"+number));
	startActivity(intent);
			
			
		}
		
		
	return true;	
	}
	

	
	public  void updateTextValue(CharSequence newText) {
	    tv.setText(newText);
	    tv.setAnimation(AnimationUtils.loadAnimation(getActivity(),android.R.anim.fade_in));
		}

}
