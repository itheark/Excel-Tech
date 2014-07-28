package com.greycodes.excel14;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greycodes.excel14.initiatives.DevconFragment;
import com.greycodes.excel14.initiatives.IbetoFragment;
import com.greycodes.excel14.initiatives.IbetoJrFragment;

public class InitiativesViewPager extends Fragment {
ViewPager view=null;
	
	android.support.v4.app.FragmentManager fragmentmanager;
	View rootView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.initiatives_viewpager, container, false);
		 view = (ViewPager) rootView.findViewById(R.id.initiatives_viewpager);
			fragmentmanager=  getChildFragmentManager();
			view.setAdapter(new InitiativesViewPageAdapter(fragmentmanager));
			view.setCurrentItem(1);
		return rootView;
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
			
					
			}
			return null;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 3;
		}
	}
}
