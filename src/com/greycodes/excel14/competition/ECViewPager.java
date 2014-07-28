package com.greycodes.excel14.competition;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greycodes.excel14.R;
import com.greycodes.excel14.ecevents.FragmentDefuse;
import com.greycodes.excel14.ecevents.FragmentExtrinsicity;
import com.greycodes.excel14.ecevents.FragmentRobowars;
import com.greycodes.excel14.ecevents.FragmentTerrainMaster;
import com.greycodes.excel14.ecevents.FragmentCircuim;

public class ECViewPager extends Fragment {
	ViewPager view=null;
	public static int  pagetodisplay=0;
	android.support.v4.app.FragmentManager fragmentmanager;
	private int num_pages = 5;
	View rootView;
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			rootView =	 inflater.inflate(R.layout.fragment_ec_viewpager, container, false);
			
			 final ViewPagerParallax pager = (ViewPagerParallax) rootView.findViewById(R.id.ecpager);
			 fragmentmanager=  getChildFragmentManager();
		        pager.set_max_pages(5);
		        pager.setBackgroundAsset(R.raw.sanfran);
		        pager.setAdapter(new ECViewPageAdapter(fragmentmanager));
		        pager.setCurrentItem(pagetodisplay);

			return rootView;
			
		}

		@Override
		public void onSaveInstanceState(Bundle outState) {
	        super.onSaveInstanceState(outState);
	        outState.putInt("num_pages", num_pages);
	        final ViewPagerParallax pager = (ViewPagerParallax) rootView.findViewById(R.id.ecpager);
	        outState.putInt("current_page", pager.getCurrentItem());
	    }
		
		@Override
		public void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			
		}

		
		
	}

	class ECViewPageAdapter extends FragmentStatePagerAdapter{

		public ECViewPageAdapter(android.support.v4.app.FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int position) {
			// TODO Auto-generated method stub
			Fragment fragment=null;
			switch(position){
			
			case 0:
				fragment = new FragmentExtrinsicity();
				break;
			case 1:
				fragment = new FragmentTerrainMaster();
				break;
			case 2:
				fragment = new FragmentRobowars();
				break;
			case 3:
				fragment = new FragmentDefuse();
				break;
			case 4:
				fragment = new FragmentCircuim();
				break;
			
				
			}
			return fragment;
		}
		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			switch(position){
			case 0:
				return "Extrinsicity";
				
			case 1:
				return "Terrain Master";
			
			case 2:
				return "RoboWars";
			
			case 3:
				return "Defuse";
		
			case 4:
				return "Wave Cloning";
		
			}
			return null;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 5;
		}
}
