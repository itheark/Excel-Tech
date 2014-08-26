package com.greycodes.excel14;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.greycodes.excel14.competition.ViewPagerParallax;
import com.greycodes.excel14.excelpro.TagURItFragment;

public class ExcelProViewPager extends Fragment implements OnClickListener {
	ViewPagerParallax pager;
	public static int  tspagetoset=0;
	//Button call;
	private int num_pages = 1;
	android.support.v4.app.FragmentManager fragmentmanager;
	View rootView;
	 
	 ProgressDialog progressDialog;
	 
		Misc  misc;
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			rootView =	 inflater.inflate(R.layout.excelpro_viewpager, container, false);
			  pager = (ViewPagerParallax) rootView.findViewById(R.id.excelpropager);
			 fragmentmanager=  getChildFragmentManager();
		        pager.set_max_pages(1);
		        
		        pager.setBackgroundAsset(R.raw.noweee);
		        pager.setAdapter(new ExcelProViewPageAdapter(fragmentmanager));
		        pager.setCurrentItem(tspagetoset);
		      
		        if (savedInstanceState!=null) {
		            num_pages = savedInstanceState.getInt("num_pages");
		            pager.setCurrentItem(savedInstanceState.getInt("current_page"), false);
		        }
		        
		       
		         misc = new Misc(getActivity());
		         
		         			       		        
				return rootView;

		    }

		    @Override
			public void onSaveInstanceState(Bundle outState) {
		        super.onSaveInstanceState(outState);
		        outState.putInt("num_pages", num_pages);
		        final ViewPagerParallax pager = (ViewPagerParallax) rootView.findViewById(R.id.excelpropager);
		        outState.putInt("current_page", pager.getCurrentItem());
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
				return "Bucky Free Fall";
			
					}
			return null;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 1;
		}
	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		
			
			
		
		}
		
		
	
}
