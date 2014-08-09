package com.greycodes.excel14.info;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greycodes.excel14.R;
import com.greycodes.excel14.competition.ViewPagerParallax;

public class FloorMapViewPager extends Fragment {
	ViewPagerParallax pager;
	public static int  fmpagetodisplay=0;
	//Button call;
	private int num_pages = 5;
	android.support.v4.app.FragmentManager fragmentmanager;
	View rootView;
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			rootView =	 inflater.inflate(R.layout.info_floormap_viewpager, container, false);
			  pager = (ViewPagerParallax) rootView.findViewById(R.id.floormappager);
			 fragmentmanager=  getChildFragmentManager();
		        pager.set_max_pages(5);
		        
		        pager.setBackgroundAsset(R.raw.nowcs);
		        pager.setAdapter(new FMViewPageAdapter(fragmentmanager));
		        pager.setCurrentItem(fmpagetodisplay);
		      
		        if (savedInstanceState!=null) {
		            num_pages = savedInstanceState.getInt("num_pages");
		            pager.setCurrentItem(savedInstanceState.getInt("current_page"), false);
		        }
		        
		        
		        
				return rootView;

		    }

		    @Override
			public void onSaveInstanceState(Bundle outState) {
		        super.onSaveInstanceState(outState);
		        outState.putInt("num_pages", num_pages);
		        final ViewPagerParallax pager = (ViewPagerParallax) rootView.findViewById(R.id.floormappager);
		        outState.putInt("current_page", pager.getCurrentItem());
		    }

		
		
	}

	class FMViewPageAdapter extends FragmentStatePagerAdapter{

		public FMViewPageAdapter(android.support.v4.app.FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int position) {
			// TODO Auto-generated method stub
			Fragment fragment=null;
			switch(position){
			
			case 0:
				fragment = new FloorOne();
				break;
			case 1:
				fragment = new FloorTwo();
				break;
			case 2:
				fragment = new FloorThree();
				break;
			case 3:
				fragment = new FloorFour();
				break;
			case 4:
				fragment = new FloorFive();
				break;
			
				
			}
			return fragment;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			switch(position){
			case 0:
				return "1st Floor";
				
			case 1:
				return "2nd Floor";
			
			case 2:
				return "3rd Floor";
			
			case 3:
				return "4th Floor";
		
			case 4:
				return "5th Floor";
			
			}
			return null;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 5;
		}
}
