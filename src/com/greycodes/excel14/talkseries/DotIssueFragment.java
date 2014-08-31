package com.greycodes.excel14.talkseries;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.greycodes.excel14.R;

public class DotIssueFragment extends Fragment implements OnClickListener {
	public  static TextView tv;
	RelativeLayout rr1;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.talkseries_dotissue, container, false);
		 
		 rr1=(RelativeLayout)rootView.findViewById(R.id.rrl);
		tv=(TextView)rootView.findViewById(R.id.txtinclude);
		 rr1.setOnClickListener(this);
		 return rootView;
		
		
	}
	

public  void updateTextValue(CharSequence newText) {
    tv.setText(newText);
    tv.setAnimation(AnimationUtils.loadAnimation(getActivity(),android.R.anim.fade_in));
	}


@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	if(v.equals(rr1)){
		
		updateTextValue(".issue! is a highlight of Excel, the national level techno-managerial symposium of Govt. Model Engineering College, Kochi. It is a debating platform where matters relevant to youth and nation are discussed, analyzed and necessary actions adopted. Vibrant speakers from different domains share their view on the topic and interact with students. .issue! 2014 discusses 'Poison on Plates' and is scheduled to be held on the 26th of September 2014 from 4pm to 6pm in the college amphitheatre at Thrikkakara, Kochi.\n\n");
	}
	
}


}
