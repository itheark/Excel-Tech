package com.greycodes.excel14.competition;


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
import android.widget.ImageView;

import com.actionbarsherlock.app.SherlockFragment;
import com.greycodes.excel14.R;
 

public class CSFragment extends SherlockFragment {
	Fragment fragment=  null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.fragment_cs, container, false);
		 
		ImageView btHash = (ImageView) rootView.findViewById(R.id.include);
		ImageView btApp = (ImageView) rootView.findViewById(R.id.algorithm);
		ImageView btFour = (ImageView) rootView.findViewById(R.id.four120);
		ImageView btBots = (ImageView) rootView.findViewById(R.id.soyouthink);
		ImageView btHack = (ImageView) rootView.findViewById(R.id.hackmaster);
		ImageView btLOC = (ImageView) rootView.findViewById(R.id.loc);
		ImageView btMandrake = (ImageView) rootView.findViewById(R.id.webots);
		/* final Animation animTranslatetoright = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_translatetoright);
		 final Animation animTranslatetoleft = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_translatetoleft);
		 btHash.startAnimation(animTranslatetoright);
		 btApp.startAnimation(animTranslatetoleft);
		 btFour.startAnimation(animTranslatetoright);
		 btBots.startAnimation(animTranslatetoleft);
		 btHack.startAnimation(animTranslatetoright);
		 btLOC.startAnimation(animTranslatetoleft);
		 btMandrake.startAnimation(animTranslatetoright);
		 */
		 
		 
		 
		 
btHash.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		CSViewPager.pagetodisplay=0;
		Fragment fragment =new  CSViewPager();
		FragmentManager fragmentManager = getFragmentManager();
		 FragmentTransaction transaction=fragmentManager.beginTransaction();
		 transaction.setCustomAnimations(R.anim.fadeinright, R.anim.fadeoutleft, R.anim.fadeinleft, R.anim.fadeoutright);
		 transaction.replace(R.id.competition_content_frame,fragment);
			// Add this transaction to the back stack
        transaction.addToBackStack("hashInclude");
        transaction.commit();
	}
});
btApp.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		CSViewPager.pagetodisplay=1;
		Fragment fragment =new  CSViewPager();
		FragmentManager fragmentManager = getFragmentManager();
		 FragmentTransaction transaction=fragmentManager.beginTransaction();
		 transaction.setCustomAnimations(R.anim.fadeinright, R.anim.fadeoutleft, R.anim.fadeinleft, R.anim.fadeoutright);
		 transaction.replace(R.id.competition_content_frame,fragment);
			// Add this transaction to the back stack
        transaction.addToBackStack("AppGEnius");
        transaction.commit();
	}
});
btFour.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		CSViewPager.pagetodisplay=2;
		Fragment fragment =new  CSViewPager();;
		FragmentManager fragmentManager = getFragmentManager();
		 FragmentTransaction transaction=fragmentManager.beginTransaction();
		 transaction.setCustomAnimations(R.anim.fadeinright, R.anim.fadeoutleft, R.anim.fadeinleft, R.anim.fadeoutright);
		 transaction.replace(R.id.competition_content_frame,fragment);
			// Add this transaction to the back stack
        transaction.addToBackStack("FourOneTwenty");
        transaction.commit();
	}
});
btBots.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		CSViewPager.pagetodisplay=3;
		Fragment fragment =new  CSViewPager();
		FragmentManager fragmentManager = getFragmentManager();
		 FragmentTransaction transaction=fragmentManager.beginTransaction();
		 transaction.setCustomAnimations(R.anim.fadeinright, R.anim.fadeoutleft, R.anim.fadeinleft, R.anim.fadeoutright);
		 transaction.replace(R.id.competition_content_frame,fragment);
			// Add this transaction to the back stack
        transaction.addToBackStack("WebBots");
        transaction.commit();
	}
});
btHack.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		CSViewPager.pagetodisplay=4;
		Fragment fragment =new  CSViewPager();
		FragmentManager fragmentManager = getFragmentManager();
		 FragmentTransaction transaction=fragmentManager.beginTransaction();
		 transaction.setCustomAnimations(R.anim.fadeinright, R.anim.fadeoutleft, R.anim.fadeinleft, R.anim.fadeoutright);
		 transaction.replace(R.id.competition_content_frame,fragment);
			// Add this transaction to the back stack
        transaction.addToBackStack("HackMaster");
        transaction.commit();
	}
});
btLOC.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		CSViewPager.pagetodisplay=5;
		Fragment fragment =new  CSViewPager();
		FragmentManager fragmentManager = getFragmentManager();
		 FragmentTransaction transaction=fragmentManager.beginTransaction();
		 transaction.setCustomAnimations(R.anim.fadeinright, R.anim.fadeoutleft, R.anim.fadeinleft, R.anim.fadeoutright);
		 transaction.replace(R.id.competition_content_frame,fragment);
			// Add this transaction to the back stack
        transaction.addToBackStack("LOC");
        transaction.commit();
	}
});
btMandrake.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		CSViewPager.pagetodisplay=6;
		Fragment fragment =new  CSViewPager();
		FragmentManager fragmentManager = getFragmentManager();
		 FragmentTransaction transaction=fragmentManager.beginTransaction();
		 transaction.setCustomAnimations(R.anim.fadeinright, R.anim.fadeoutleft, R.anim.fadeinleft, R.anim.fadeoutright);
		 transaction.replace(R.id.competition_content_frame,fragment);
			// Add this transaction to the back stack
        transaction.addToBackStack("WebMandrake");
        transaction.commit();
	}
});
		return rootView;
		
	}

	


}
