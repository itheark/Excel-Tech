package com.greycodes.excel14.conference;

import com.greycodes.excel14.R;
import com.greycodes.excel14.competition.ViewPagerParallax;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class ConferenceViewPager extends Fragment implements OnClickListener {

	ViewPager view=null;
	public static int  pagetodisplay=0;
	android.support.v4.app.FragmentManager fragmentmanager;
	private int num_pages = 3;
	View rootView;
	
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			rootView =	 inflater.inflate(R.layout.conferenceviewpager, container, false);
			
			 final ViewPagerParallax pager = (ViewPagerParallax) rootView.findViewById(R.id.conferencepager);
			 fragmentmanager=  getChildFragmentManager();
		        pager.set_max_pages(3);
		        pager.setBackgroundAsset(R.raw.nowec);
		        pager.setAdapter(new ConferenceViewPageAdapter(fragmentmanager));
		        pager.setCurrentItem(pagetodisplay);
		        
		        
		        return rootView;
			
		}

		@Override
		public void onSaveInstanceState(Bundle outState) {
	        super.onSaveInstanceState(outState);
	        outState.putInt("num_pages", num_pages);
	        final ViewPagerParallax pager = (ViewPagerParallax) rootView.findViewById(R.id.conferencepager);
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
			
			
		}

		
		
	}

	class ConferenceViewPageAdapter extends FragmentStatePagerAdapter{

		public ConferenceViewPageAdapter(android.support.v4.app.FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int position) {
			// TODO Auto-generated method stub
			Fragment fragment=null;
			switch(position){
			
			case 0:
				fragment = new SeminarFragment();
				break;
			case 1:
				fragment = new ExhibitionFragment();
				break;
			case 2:
				fragment = new WorkshopFragment();
				break;
			
				
			}
			return fragment;
		}
		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			switch(position){
			case 0:
				return "Seminar";
				
			case 1:
				return "Exhibition";
			
			case 2:
				return "Workshop";
			
		
		
			}
			return null;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 3;
		}
}
