package com.greycodes.excel14.talkseries;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greycodes.excel14.R;
import com.greycodes.excel14.competition.ViewPagerParallax;

public class TalkSeriesViewPager extends Fragment {
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
			rootView =	 inflater.inflate(R.layout.talkseries_viewpager, container, false);
			  pager = (ViewPagerParallax) rootView.findViewById(R.id.talkseriespager);
			 fragmentmanager=  getChildFragmentManager();
		        pager.set_max_pages(5);
		        
		        pager.setBackgroundAsset(R.raw.sanfran);
		        pager.setAdapter(new TalkSeriesViewPageAdapter(fragmentmanager));
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
		        final ViewPagerParallax pager = (ViewPagerParallax) rootView.findViewById(R.id.talkseriespager);
		        outState.putInt("current_page", pager.getCurrentItem());
		    }

			
	
	class TalkSeriesViewPageAdapter extends FragmentStatePagerAdapter{

		public TalkSeriesViewPageAdapter(android.support.v4.app.FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int position) {
			// TODO Auto-generated method stub
			Fragment fragment=null;
			switch(position){
			
			case 0:
				fragment = new DotIssueFragment();
				break;
			case 1:
				fragment = new TedXMecFragment();
				break;
			case 2:
				fragment = new ExhibitionFragment();
				break;
			case 3:
				fragment = new SeminarFragment();
				break;
			case 4:
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
				return ".Issue!";
				
			case 1:
				return "TedxMEC";
			
			case 2:
				return "Exhibitions";
			
			case 3:
				return "Seminars";
		
			case 4:
				return "Workshop";
		
			}
			return null;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 5;
		}
	}
}
