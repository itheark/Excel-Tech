package com.greycodes.excel14;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greycodes.excel14.excelpro.MarketManiaFragment;
import com.greycodes.excel14.excelpro.TagURItFragment;

public class ExcelProViewPager extends Fragment {
	ViewPager view=null;
	
	android.support.v4.app.FragmentManager fragmentmanager;
	View rootView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.excelpro_viewpager, container, false);
		 view = (ViewPager) rootView.findViewById(R.id.excelpro_viewpager);
			fragmentmanager=  getChildFragmentManager();
			view.setAdapter(new ExcelProViewPageAdapter(fragmentmanager));
			
		return rootView;
	}
	
	class ExcelProViewPageAdapter extends FragmentStatePagerAdapter{

		public ExcelProViewPageAdapter(android.support.v4.app.FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int position) {
			// TODO Auto-generated method stub
			Fragment fragment=null;
			switch(position){
			
			case 0:
				fragment = new MarketManiaFragment();
				break;
			case 1:
				fragment = new TagURItFragment();
				break;
			
				
			}
			return fragment;
		}
		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			switch(position){
			case 0:
				return "Market Mania";
				
			case 1:
				return "Tag You're It";
			
					}
			return null;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 2;
		}
	}
}
