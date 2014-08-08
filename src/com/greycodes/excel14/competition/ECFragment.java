package com.greycodes.excel14.competition;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.greycodes.excel14.R;

public class ECFragment extends Fragment {

Fragment fragment=  null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.fragment_ec, container, false);
		 
		 Button btExtrinsicity = (Button) rootView.findViewById(R.id.extrinsicity);
		
		 Button btDefuse = (Button) rootView.findViewById(R.id.defuse);
		 Button btWave = (Button) rootView.findViewById(R.id.wavecloning);
btExtrinsicity.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		ECViewPager.pagetodisplay=0;
		Fragment fragment =new  ECViewPager();
		FragmentManager fragmentManager = getFragmentManager();
		 FragmentTransaction transaction=fragmentManager.beginTransaction();
		 transaction.setCustomAnimations(R.anim.fadeinright, R.anim.fadeoutleft, R.anim.fadeinleft, R.anim.fadeoutright);
		 transaction.replace(R.id.competition_content_frame,fragment);
			// Add this transaction to the back stack
        transaction.addToBackStack("extrinsicity");
        transaction.commit();
	}
});

btDefuse.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		ECViewPager.pagetodisplay=1;
		Fragment fragment =new  ECViewPager();
		FragmentManager fragmentManager = getFragmentManager();
		 FragmentTransaction transaction=fragmentManager.beginTransaction();
		 transaction.setCustomAnimations(R.anim.fadeinright, R.anim.fadeoutleft, R.anim.fadeinleft, R.anim.fadeoutright);
		 transaction.replace(R.id.competition_content_frame,fragment);
			// Add this transaction to the back stack
        transaction.addToBackStack("defuse");
        transaction.commit();
	}
});
btWave.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		ECViewPager.pagetodisplay=2;
		Fragment fragment =new  ECViewPager();
		FragmentManager fragmentManager = getFragmentManager();
		 FragmentTransaction transaction=fragmentManager.beginTransaction();
		 transaction.setCustomAnimations(R.anim.fadeinright, R.anim.fadeoutleft, R.anim.fadeinleft, R.anim.fadeoutright);
		 transaction.replace(R.id.competition_content_frame,fragment);
			// Add this transaction to the back stack
        transaction.addToBackStack("wavecloning");
        transaction.commit();
	}
});


		return rootView;
		
	}

	


}
