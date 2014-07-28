package com.greycodes.excel14.competition;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greycodes.excel14.R;
import com.greycodes.excel14.nontechnical.FragmentBestManager;
import com.greycodes.excel14.nontechnical.FragmentCSI;
import com.greycodes.excel14.nontechnical.FragmentDigitalDaVinci;
import com.greycodes.excel14.nontechnical.FragmentFunZone;
import com.greycodes.excel14.nontechnical.FragmentGameZone;
import com.greycodes.excel14.nontechnical.FragmentGeneralQuiz;
import com.greycodes.excel14.nontechnical.FragmentInstantPhotography;
import com.greycodes.excel14.nontechnical.FragmentKluge;
import com.greycodes.excel14.nontechnical.FragmentOnlinePhotography;
import com.greycodes.excel14.nontechnical.FragmentShortFilm;
import com.greycodes.excel14.nontechnical.FragmentSpiderWeb;
import com.greycodes.excel14.nontechnical.FragmentTikiTaka;
import com.greycodes.excel14.nontechnical.FragmentTreasureHunt;

public class NonTechViewPager extends Fragment {
	ViewPager view=null;
	public static int  pagetodisplay=0;
	android.support.v4.app.FragmentManager fragmentmanager;
	View rootView;
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			rootView =	 inflater.inflate(R.layout.fragment_nontech_viewpager, container, false);

			 final ViewPagerParallax pager = (ViewPagerParallax) rootView.findViewById(R.id.nontech_pager);
			 fragmentmanager=  getChildFragmentManager();
		        pager.set_max_pages(13);
		        pager.setBackgroundAsset(R.raw.sanfran);
		        pager.setAdapter(new NonTechnicalViewPageAdapter(fragmentmanager));
		        pager.setCurrentItem(pagetodisplay);

			return rootView;
			
		}

		@Override
		public void onSaveInstanceState(Bundle outState) {
	        super.onSaveInstanceState(outState);
	        outState.putInt("num_pages", 13);
	        final ViewPagerParallax pager = (ViewPagerParallax) rootView.findViewById(R.id.nontech_pager);
	        outState.putInt("current_page", pager.getCurrentItem());
	    }

		@Override
		public void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			
		}

		
		
	}

	class NonTechnicalViewPageAdapter extends FragmentStatePagerAdapter{

		public NonTechnicalViewPageAdapter(android.support.v4.app.FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int position) {
			// TODO Auto-generated method stub
			Fragment fragment=null;
			switch(position){
			
			case 0:
				fragment = new FragmentGeneralQuiz();
				break;
			case 1:
				fragment = new FragmentOnlinePhotography();
				
				break;
			case 2:
				fragment = new FragmentInstantPhotography();
				break;
			case 3:
				fragment = new FragmentGameZone();
				break;
			case 4:
				fragment = new FragmentSpiderWeb();
				break;
			case 5:
				fragment = new FragmentFunZone();
				break;
			case 6:
				fragment = new FragmentDigitalDaVinci();
				break;
			case 7:
				fragment = new FragmentCSI();
				break;
			case 8:
				fragment = new FragmentBestManager();
				break;
			case 9:
				fragment = new FragmentShortFilm();
				break;
			case 10:
				fragment = new FragmentTreasureHunt();
				break;
			case 11:
				fragment = new FragmentKluge();
				break;
			case 12:
				fragment = new FragmentTikiTaka();
				break;
				
			}
			return fragment;
		}
		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			switch(position){
			case 0:
				return "General Quiz";
				
			case 1:
				return "Online Photography";
			
			case 2:
				return "Instant Photography";
			
			case 3:
				return "Game Zone";
		
			case 4:
				return "Spider Web";
			case 5:
				return "Fun Zone";
				
			case 6:
				return "Digital Da Vinci";
			
			case 7:
				return "CSI";
			
			case 8:
				return "Best Manager";
		
			case 9:
				return "Short Film";
			case 10:
				return "Treasure Hunt";
			
			case 11:
				return "Kludge";
		
			case 12:
				return "TikiTaka";
			}
			return null;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 13;
		}
}
