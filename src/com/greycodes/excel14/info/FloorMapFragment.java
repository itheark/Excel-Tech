package com.greycodes.excel14.info;

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

import com.greycodes.excel14.R;

public class FloorMapFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.info_floormap_fragment, container, false);
		 
		 
		 Button btFloor1 = (Button) rootView.findViewById(R.id.floormap1);
		 Button btFloor2 = (Button) rootView.findViewById(R.id.floormap2);
		 Button btFloor3 = (Button) rootView.findViewById(R.id.floormap3);
		 Button btFloor4 = (Button) rootView.findViewById(R.id.floormap4);
		 Button btFloor5 = (Button) rootView.findViewById(R.id.floormap5);
		
		 
		 final Animation animTranslatetoright = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_translatetoright);
		 final Animation animTranslatetoleft = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_translatetoleft);
		 
		 btFloor1.startAnimation(animTranslatetoright);
		 btFloor2.startAnimation(animTranslatetoleft);
		 btFloor3.startAnimation(animTranslatetoright);
		 btFloor4.startAnimation(animTranslatetoleft);
		 btFloor5.startAnimation(animTranslatetoright);
		
		 
		 
		 
		 
		 
btFloor1.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		FloorMapViewPager.fmpagetodisplay=0;
		Fragment fragment =new  FloorMapViewPager();
		FragmentManager fragmentManager = getFragmentManager();
		 FragmentTransaction transaction=fragmentManager.beginTransaction();
		 transaction.replace(R.id.info_content_frame,fragment);
			// Add this transaction to the back stack
        transaction.addToBackStack("floor1");
        transaction.commit();
	}
});
btFloor2.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		FloorMapViewPager.fmpagetodisplay=1;
		Fragment fragment =new  FloorMapViewPager();
		FragmentManager fragmentManager = getFragmentManager();
		 FragmentTransaction transaction=fragmentManager.beginTransaction();
		 transaction.replace(R.id.info_content_frame,fragment);
			// Add this transaction to the back stack
        transaction.addToBackStack("floor2");
        transaction.commit();
	}
});
btFloor3.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		FloorMapViewPager.fmpagetodisplay=2;
		Fragment fragment =new  FloorMapViewPager();
		FragmentManager fragmentManager = getFragmentManager();
		 FragmentTransaction transaction=fragmentManager.beginTransaction();
		 transaction.replace(R.id.info_content_frame,fragment);
			// Add this transaction to the back stack
        transaction.addToBackStack("floor3");
        transaction.commit();
	}
});
btFloor4.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		FloorMapViewPager.fmpagetodisplay=3;
		Fragment fragment =new  FloorMapViewPager();
		FragmentManager fragmentManager = getFragmentManager();
		 FragmentTransaction transaction=fragmentManager.beginTransaction();
		 transaction.replace(R.id.info_content_frame,fragment);
			// Add this transaction to the back stack
        transaction.addToBackStack("floor4");
        transaction.commit();
	}
});
btFloor5.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		FloorMapViewPager.fmpagetodisplay=4;
		Fragment fragment =new  FloorMapViewPager();
		FragmentManager fragmentManager = getFragmentManager();
		 FragmentTransaction transaction=fragmentManager.beginTransaction();
		 transaction.replace(R.id.info_content_frame,fragment);
			// Add this transaction to the back stack
        transaction.addToBackStack("floor5");
        transaction.commit();
	}
});

		return rootView;
		
	}

	
}
