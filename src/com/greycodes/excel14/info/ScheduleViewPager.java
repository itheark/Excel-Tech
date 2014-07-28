package com.greycodes.excel14.info;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greycodes.excel14.R;

public class ScheduleViewPager extends Fragment {
	ViewPager view=null;

	android.support.v4.app.FragmentManager fragmentmanager;
	View rootView;
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			rootView =	 inflater.inflate(R.layout.info_schedule_viewpager, container, false);
			view = (ViewPager) rootView.findViewById(R.id.info_schedule_viewpager);
			fragmentmanager=  getChildFragmentManager();
			view.setAdapter(new ScheduleViewPageAdapter(fragmentmanager));
			view.setCurrentItem(1);

			return rootView;
			
		}

		@Override
		public void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			
		}

		
		
	}

	class ScheduleViewPageAdapter extends FragmentStatePagerAdapter{

		public ScheduleViewPageAdapter(android.support.v4.app.FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int position) {
			// TODO Auto-generated method stub
			Fragment fragment=null;
			switch(position){
			
			case 0:
				fragment = new ScheduleDay1Fragment();
				break;
			case 1:
				fragment = new ScheduleDay2Fragment();
				break;
			case 2:
				fragment = new ScheduleDay3Fragment();
				break;
			
				
			}
			return fragment;
		}
		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			switch(position){
			case 0:
				return "1st Day";
				
			case 1:
				return "2nd Day";
			
			case 2:
				return "3rd Day";
			
			
		
			}
			return null;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 3;
		}
	

}
