package com.greycodes.excel14.competition;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greycodes.excel14.R;
import com.greycodes.excel14.eeeevents.FragmentExtundoprodigo;
import com.greycodes.excel14.eeeevents.FragmentLumiere;

public class EEEViewPager extends Fragment {
	ViewPager view=null;
	android.support.v4.app.FragmentManager fragmentmanager;
	View rootView;
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			rootView =	 inflater.inflate(R.layout.fragment_eee_viewpager, container, false);

			 final ViewPagerParallax pager = (ViewPagerParallax) rootView.findViewById(R.id.eeepager);
			 fragmentmanager=  getChildFragmentManager();
			 pager.set_max_pages(2);
		        pager.setBackgroundAsset(R.raw.noweeee);
		        pager.setAdapter(new EEEViewPageAdapter(fragmentmanager));
		        pager.setCurrentItem(0);

			return rootView;
			
		}
		@Override
		public void onSaveInstanceState(Bundle outState) {
	        super.onSaveInstanceState(outState);
	        outState.putInt("num_pages", 2);
	        final ViewPagerParallax pager = (ViewPagerParallax) rootView.findViewById(R.id.eeepager);
	        outState.putInt("current_page", pager.getCurrentItem());
	    }
		
		
		@Override
		public void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			
		}

		
		
	}

	class EEEViewPageAdapter extends FragmentStatePagerAdapter{

		public EEEViewPageAdapter(android.support.v4.app.FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int position) {
			// TODO Auto-generated method stub
			Fragment fragment=null;
			switch(position){
			
			case 0:
				fragment = new FragmentLumiere();
				break;
			case 1:
				fragment = new FragmentExtundoprodigo();
				
				break;
						
				
			}
			return fragment;
		}
		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			switch(position){
			case 0:
				return "Lumiere";
				
			case 1:
				return "Extundo Prodigo";
			
		
			}
			return null;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 2;
		}
}
