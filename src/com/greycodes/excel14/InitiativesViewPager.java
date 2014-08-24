package com.greycodes.excel14;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greycodes.excel14.competition.ViewPagerParallax;
import com.greycodes.excel14.initiatives.DevconFragment;
import com.greycodes.excel14.initiatives.IbetoFragment;
import com.greycodes.excel14.initiatives.IbetoJrFragment;
import com.greycodes.excel14.initiatives.OrganicFarmingFragment;

public class InitiativesViewPager extends Fragment {
	ViewPagerParallax pager;
	public static int  tspagetoset=0;
	//Button call;
	private int num_pages = 5;
	android.support.v4.app.FragmentManager fragmentmanager;
	View rootView;
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			rootView =	 inflater.inflate(R.layout.initiatives_viewpager, container, false);
			  pager = (ViewPagerParallax) rootView.findViewById(R.id.initiativespager);
			 fragmentmanager=  getChildFragmentManager();
		        pager.set_max_pages(3);
		        
		        pager.setBackgroundAsset(R.raw.initiativesbg);
		        pager.setAdapter(new InitiativesViewPageAdapter(fragmentmanager));
		        pager.setCurrentItem(tspagetoset);
		      
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
		        final ViewPagerParallax pager = (ViewPagerParallax) rootView.findViewById(R.id.initiativespager);
		        outState.putInt("current_page", pager.getCurrentItem());
		    }

			
	
	class InitiativesViewPageAdapter extends FragmentStatePagerAdapter{

		public InitiativesViewPageAdapter(android.support.v4.app.FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int position) {
			// TODO Auto-generated method stub
			Fragment fragment=null;
			switch(position){
			
			case 0:
				fragment = new IbetoFragment();
				break;
			case 1:
				fragment = new IbetoJrFragment();
				break;
			case 2:
				fragment = new DevconFragment();
				break;
			case 3:
				fragment = new OrganicFarmingFragment();
				break;
				
				
			}
			return fragment;
		}
		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			switch(position){
			case 0:
				return "Ibeto";
				
			case 1:
				return "Ibeto Jr";
			
			case 2:
				return "Devcon";
			case 3:
				return "Organic Farming";
					
			}
			return null;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 4;
		}
	}
}
