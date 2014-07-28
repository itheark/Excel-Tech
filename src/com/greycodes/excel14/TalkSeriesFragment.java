package com.greycodes.excel14;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.greycodes.excel14.talkseries.TalkSeriesViewPager;

public class TalkSeriesFragment extends Fragment {
Fragment fragment=  null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.homend_talkseires_fragment, container, false);
		 
		 Button btDot = (Button) rootView.findViewById(R.id.dotissue);
		 Button btTed = (Button) rootView.findViewById(R.id.tedxMec);
		 Button btExhibition = (Button) rootView.findViewById(R.id.exhibitions);
		 Button btSeminar = (Button) rootView.findViewById(R.id.seminars);
		 Button btWorkshop = (Button) rootView.findViewById(R.id.workshops);
		
		 final Animation animTranslatetoright = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_translatetoright);
		 final Animation animTranslatetoleft = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_translatetoleft);
		 
		 btDot.startAnimation(animTranslatetoright);
		 btTed.startAnimation(animTranslatetoleft);
		 btExhibition.startAnimation(animTranslatetoright);
		 btSeminar.startAnimation(animTranslatetoleft);
		 btWorkshop.startAnimation(animTranslatetoright);
		
		 
		 
		 
		 
		 
btDot.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		TalkSeriesViewPager.tspagetoset=0;
		Fragment fragment =new  TalkSeriesViewPager();
		FragmentManager fragmentManager = getFragmentManager();
		 FragmentTransaction transaction=fragmentManager.beginTransaction();
		 transaction.setCustomAnimations(R.anim.fadeinright, R.anim.fadeoutleft, R.anim.fadeinleft, R.anim.fadeoutright);
		 transaction.replace(R.id.home_content_frame,fragment);
			// Add this transaction to the back stack
        transaction.addToBackStack("DotIssue");
        transaction.commit();
	}
});
btTed.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		TalkSeriesViewPager.tspagetoset=1;
		Fragment fragment =new  TalkSeriesViewPager();
		FragmentManager fragmentManager = getFragmentManager();
		 FragmentTransaction transaction=fragmentManager.beginTransaction();
		 transaction.setCustomAnimations(R.anim.fadeinright, R.anim.fadeoutleft, R.anim.fadeinleft, R.anim.fadeoutright);
		 transaction.replace(R.id.home_content_frame,fragment);
			// Add this transaction to the back stack
        transaction.addToBackStack("TedxMec");
        transaction.commit();
	}
});
btExhibition.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		TalkSeriesViewPager.tspagetoset=2;
		Fragment fragment =new  TalkSeriesViewPager();
		FragmentManager fragmentManager = getFragmentManager();
		 FragmentTransaction transaction=fragmentManager.beginTransaction();
		 transaction.setCustomAnimations(R.anim.fadeinright, R.anim.fadeoutleft, R.anim.fadeinleft, R.anim.fadeoutright);
		 transaction.replace(R.id.home_content_frame,fragment);
			// Add this transaction to the back stack
        transaction.addToBackStack("Exhibition");
        transaction.commit();
	}
});
btSeminar.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		TalkSeriesViewPager.tspagetoset=3;
		Fragment fragment =new  TalkSeriesViewPager();
		FragmentManager fragmentManager = getFragmentManager();
		 FragmentTransaction transaction=fragmentManager.beginTransaction();
		 transaction.setCustomAnimations(R.anim.fadeinright, R.anim.fadeoutleft, R.anim.fadeinleft, R.anim.fadeoutright);
		 transaction.replace(R.id.home_content_frame,fragment);
			// Add this transaction to the back stack
        transaction.addToBackStack("Seminar");
        transaction.commit();
	}
});
btWorkshop.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		TalkSeriesViewPager.tspagetoset=4;
		Fragment fragment =new  TalkSeriesViewPager();
		FragmentManager fragmentManager = getFragmentManager();
		 FragmentTransaction transaction=fragmentManager.beginTransaction();
		 transaction.setCustomAnimations(R.anim.fadeinright, R.anim.fadeoutleft, R.anim.fadeinleft, R.anim.fadeoutright);
		 transaction.replace(R.id.home_content_frame,fragment);
			// Add this transaction to the back stack
        transaction.addToBackStack("Workshop");
        transaction.commit();
	}
});

		return rootView;
		
	}
}
