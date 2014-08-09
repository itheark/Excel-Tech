package com.greycodes.excel14.competition;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.greycodes.excel14.Misc;
import com.greycodes.excel14.R;
import com.greycodes.excel14.csevents.FragmentAlgorithms;
import com.greycodes.excel14.csevents.FragmentFourOneTwenty;
import com.greycodes.excel14.csevents.FragmentHackMaster;
import com.greycodes.excel14.csevents.FragmentHashInclude;
import com.greycodes.excel14.csevents.FragmentLOC;
import com.greycodes.excel14.csevents.FragmentSoYouThink;
import com.greycodes.excel14.csevents.FragmentWebBots;
import com.greycodes.excel14.database.ExcelDataBase;
import com.greycodes.excel14.database.InsertParticipant;
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

public class NonTechViewPager extends Fragment implements OnClickListener,OnLongClickListener {
	ViewPager view=null;
	public static int  pagetodisplay=0;
	android.support.v4.app.FragmentManager fragmentmanager;
	View rootView;
	ImageView call,result,participate;
	Misc  misc;
	ExcelDataBase excelDataBase;
	 ViewPagerParallax pager;
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			rootView =	 inflater.inflate(R.layout.fragment_nontech_viewpager, container, false);

			 final ViewPagerParallax pager = (ViewPagerParallax) rootView.findViewById(R.id.nontech_pager);
			 fragmentmanager=  getChildFragmentManager();
		        pager.set_max_pages(5);
		        pager.setBackgroundAsset(R.raw.sanfran);
		        pager.setAdapter(new NonTechnicalViewPageAdapter(fragmentmanager));
		        pager.setCurrentItem(pagetodisplay);
		        excelDataBase = new ExcelDataBase(getActivity());
		         misc = new Misc(getActivity());
			return rootView;
			
		}

		@Override
		public void onSaveInstanceState(Bundle outState) {
	        super.onSaveInstanceState(outState);
	        outState.putInt("num_pages", 5);
	        final ViewPagerParallax pager = (ViewPagerParallax) rootView.findViewById(R.id.nontech_pager);
	        outState.putInt("current_page", pager.getCurrentItem());
	    }

		@Override
		public void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			
		}

		@Override
		public boolean onLongClick(View arg0) {
			// TODO Auto-generated method stub
			switch(pager.getCurrentItem()){
			case 0:
				misc.call("09020404022");		
				break;
			case 1:
				misc.call("09020404022");		
				
				break;
			case 2:
				misc.call("09020404022");
				break;
			case 3:
				misc.call("09020404022");
				break;
			case 4:
				misc.call("09020404022");
				break;
			
			
			}
			return true;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			switch(v.getId()){
			case R.id.imageView3:
				InsertParticipant insertParticipant = new InsertParticipant(getActivity());
				
				switch(pager.getCurrentItem()){
				case 0:
					if(excelDataBase.Isregistered(1)){
						if(insertParticipant.insert(060, "Best Manager")){
							Toast.makeText(getActivity(), "Added", Toast.LENGTH_SHORT).show();
			}else{
							Toast.makeText(getActivity(), "Already Registered", Toast.LENGTH_SHORT).show();
						}
					}
					
				break;
				case 1:
					if(excelDataBase.Isregistered(1)){
						if(insertParticipant.insert(061, "CSI")){
							Toast.makeText(getActivity(), "Added", Toast.LENGTH_SHORT).show();
			}else{
							Toast.makeText(getActivity(), "Already Registered", Toast.LENGTH_SHORT).show();
						}
					}
					break;
				case 2:
					if(excelDataBase.Isregistered(1)){
						if(insertParticipant.insert(062, "Game Zone")){
							Toast.makeText(getActivity(), "Added", Toast.LENGTH_SHORT).show();
			}else{
							Toast.makeText(getActivity(), "Already Registered", Toast.LENGTH_SHORT).show();
						}
					}
					break;
				case 3:
					if(excelDataBase.Isregistered(1)){
						if(insertParticipant.insert(063, "Spider Web")){
							Toast.makeText(getActivity(), "Added", Toast.LENGTH_SHORT).show();
			}else{
							Toast.makeText(getActivity(), "Already Registered", Toast.LENGTH_SHORT).show();
						}
					}
					break;
				case 4:
					if(excelDataBase.Isregistered(1)){
						if(insertParticipant.insert(064, "Instant Photography")){
							Toast.makeText(getActivity(), "Added", Toast.LENGTH_SHORT).show();
			}else{
							Toast.makeText(getActivity(), "Already Registered", Toast.LENGTH_SHORT).show();
						}
					}
					break;
			
				
				}
				break;
			case R.id.imageView2:
				switch(pager.getCurrentItem()){
				case 0:
					
				break;
				case 1:
					
					break;
				case 2:
					
					break;
				case 3:
					
					break;
				case 4:
					
					break;
				
				
				}
				break;
			case R.id.imageView4:
				switch(pager.getCurrentItem()){
				case 0:
					FragmentBestManager.tv.setText("Cordinator");
				break;
				case 1:
					FragmentCSI.tv.setText("Cordinator");
					break;
				case 2:
					FragmentGameZone.tv.setText("Cordinator");
					break;
				case 3:
					FragmentSpiderWeb.tv.setText("Cordinator");
					break;
				case 4:
					FragmentInstantPhotography.tv.setText("Cordinator");
					break;
				
				
				}
				break;
			}
			
			
		
			
			
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
			
				
			}
			return fragment;
		}
		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			switch(position){
			case 0:
				return "Best Manager";
				
			case 1:
				return "CSI";
			
			case 2:
				return "Game Zone";
			
			case 3:
				return "Spider Web";
		
			case 4:
				return "Instant Photography";
			
			}
			return null;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 5;
		}
}
