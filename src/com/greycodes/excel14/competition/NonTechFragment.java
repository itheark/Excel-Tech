package com.greycodes.excel14.competition;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.greycodes.excel14.R;

public class NonTechFragment extends Fragment {
Fragment fragment=  null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.fragment_nontech, container, false);
		 
		 ImageView btbest = (ImageView) rootView.findViewById(R.id.bestman);
		 ImageView btcsi = (ImageView) rootView.findViewById(R.id.csi);
		 ImageView btgame = (ImageView) rootView.findViewById(R.id.gamezone);
		 ImageView btspider = (ImageView) rootView.findViewById(R.id.spider);
		 ImageView btinstant = (ImageView) rootView.findViewById(R.id.instant);
		 
		
		 btbest.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		NonTechViewPager.pagetodisplay=0;
		Fragment fragment =new  NonTechViewPager();
		FragmentManager fragmentManager = getFragmentManager();
		 FragmentTransaction transaction=fragmentManager.beginTransaction();
		 transaction.setCustomAnimations(R.anim.fadeinright, R.anim.fadeoutleft, R.anim.fadeinleft, R.anim.fadeoutright);
		 transaction.replace(R.id.competition_content_frame,fragment);
			// Add this transaction to the back stack
        transaction.addToBackStack("generalquiz");
        transaction.commit();
	}
});
		 btcsi.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
NonTechViewPager.pagetodisplay=1;
		Fragment fragment =new  NonTechViewPager();
		FragmentManager fragmentManager = getFragmentManager();
		 FragmentTransaction transaction=fragmentManager.beginTransaction();
		 transaction.setCustomAnimations(R.anim.fadeinright, R.anim.fadeoutleft, R.anim.fadeinleft, R.anim.fadeoutright);
		 transaction.replace(R.id.competition_content_frame,fragment);
			// Add this transaction to the back stack
        transaction.addToBackStack("onlinephotography");
        transaction.commit();
	}
});
		 btgame.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					NonTechViewPager.pagetodisplay=2;
					Fragment fragment =new  NonTechViewPager();
					FragmentManager fragmentManager = getFragmentManager();
					 FragmentTransaction transaction=fragmentManager.beginTransaction();
					 transaction.setCustomAnimations(R.anim.fadeinright, R.anim.fadeoutleft, R.anim.fadeinleft, R.anim.fadeoutright);
					 transaction.replace(R.id.competition_content_frame,fragment);
						// Add this transaction to the back stack
			        transaction.addToBackStack("instantphotography");
			        transaction.commit();
				}
			});
		 btspider.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					NonTechViewPager.pagetodisplay=3;
					Fragment fragment =new  NonTechViewPager();
					FragmentManager fragmentManager = getFragmentManager();
					 FragmentTransaction transaction=fragmentManager.beginTransaction();
					 transaction.setCustomAnimations(R.anim.fadeinright, R.anim.fadeoutleft, R.anim.fadeinleft, R.anim.fadeoutright);
					 transaction.replace(R.id.competition_content_frame,fragment);
						// Add this transaction to the back stack
			        transaction.addToBackStack("gamezone");
			        transaction.commit();
				}
			});
		 
		 btinstant.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
			NonTechViewPager.pagetodisplay=4;
					Fragment fragment =new  NonTechViewPager();
					FragmentManager fragmentManager = getFragmentManager();
					 FragmentTransaction transaction=fragmentManager.beginTransaction();
					 transaction.setCustomAnimations(R.anim.fadeinright, R.anim.fadeoutleft, R.anim.fadeinleft, R.anim.fadeoutright);
					 transaction.replace(R.id.competition_content_frame,fragment);
						// Add this transaction to the back stack
			        transaction.addToBackStack("photography");
			        transaction.commit();
				}
			});
		

		return rootView;
		
	}
}
