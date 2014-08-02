package com.greycodes.excel14.competition;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.greycodes.excel14.R;
import com.greycodes.excel14.csevents.FragmentSoYouThink;
import com.greycodes.excel14.csevents.FragmentFourOneTwenty;
import com.greycodes.excel14.csevents.FragmentHackMaster;
import com.greycodes.excel14.csevents.FragmentHashInclude;
import com.greycodes.excel14.csevents.FragmentLOC;
import com.greycodes.excel14.csevents.FragmentWebBots;
import com.greycodes.excel14.csevents.FragmentAlgorithms;


public class CSViewPager extends Fragment implements OnClickListener {
ViewPager view=null;
ImageView call;

ViewPagerParallax pager;
public static int  pagetodisplay=0;
//Button call;
private int num_pages = 7;
android.support.v4.app.FragmentManager fragmentmanager;
View rootView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootView =	 inflater.inflate(R.layout.fragment_cs_viewpager, container, false);
		  pager = (ViewPagerParallax) rootView.findViewById(R.id.pager);
		 fragmentmanager=  getChildFragmentManager();
	        pager.set_max_pages(7);
	        pager.setBackgroundAsset(R.raw.sanfran);
	        pager.setAdapter(new CSViewPageAdapter(fragmentmanager));
	        call=(ImageView)rootView.findViewById(R.id.imageView4);
	        pager.setCurrentItem(pagetodisplay);
	      
	        
	        
	        call.setOnClickListener(this);
			return rootView;

	    }

	    @Override
		public void onSaveInstanceState(Bundle outState) {
	        super.onSaveInstanceState(outState);
	        outState.putInt("num_pages", num_pages);
	        final ViewPagerParallax pager = (ViewPagerParallax) rootView.findViewById(R.id.pager);
	        outState.putInt("current_page", pager.getCurrentItem());
	    }

		
	

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		
		if(v.equals(call))
		
		{
			switch(pager.getCurrentItem())
			{
			case 0 :  Toast.makeText(getActivity(), "Calling PAge 0",
					   Toast.LENGTH_LONG).show();
			FragmentHashInclude.updateTextValue("Calling hasinclude");
					break;
			case 1 :  Toast.makeText(getActivity(), "Calling PAge 1",
					   Toast.LENGTH_LONG).show();break;
			case 2 :  Toast.makeText(getActivity(), "Calling PAge 2",
					   Toast.LENGTH_LONG).show();break;
			case 3 :  Toast.makeText(getActivity(), "Calling PAge 3",
					   Toast.LENGTH_LONG).show();break;
			default:Toast.makeText(getActivity(), "Calling PAge other",
					   Toast.LENGTH_LONG).show();
			//search for the page
			}
		}
		
	
		
		
	}

	
	
}

class CSViewPageAdapter extends FragmentStatePagerAdapter{

	public CSViewPageAdapter(android.support.v4.app.FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		Fragment fragment=null;
		switch(position){
		
		case 0:
			fragment = new FragmentHashInclude();
			break;
		case 1:
			fragment = new FragmentSoYouThink();
			break;
		case 2:
			fragment = new FragmentFourOneTwenty();
			break;
		case 3:
			fragment = new FragmentWebBots();
			break;
		case 4:
			fragment = new FragmentHackMaster();
			break;
		case 5:
			fragment = new FragmentLOC();
			break;
		case 6:
			fragment = new FragmentAlgorithms();
			break;
			
		}
		return fragment;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		switch(position){
		case 0:
			return "#Include";
			
		case 1:
			return "App Genius";
		
		case 2:
			return "4*120";
		
		case 3:
			return "Web Bots";
	
		case 4:
			return "Hack Master";
		case 5:
			return "LOC";
		case 6:
			return "Web Mandrake";
		}
		return null;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 7;
	}
	
}
