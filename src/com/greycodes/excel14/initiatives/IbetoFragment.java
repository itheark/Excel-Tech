package com.greycodes.excel14.initiatives;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.greycodes.excel14.R;

public class IbetoFragment extends Fragment implements OnClickListener {
	public  static TextView tv;
	ImageView heading;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.initiatives_ibeto, container, false);
		 
		 heading =(ImageView)rootView.findViewById(R.id.include);
		heading.setOnClickListener(this);
		 
		 
		return rootView;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if(v.equals(heading)){
			
			 String url = "http://ibeto.excelmec.org/";
			 Intent i = new Intent(Intent.ACTION_VIEW);
			 i.setData(Uri.parse(url));
			 startActivity(i);
			
			
		}
		
		
	}
	

	
	


}




