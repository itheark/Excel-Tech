package com.greycodes.excel14.competition;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import com.greycodes.excel14.ConnectionDetector;
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
import com.greycodes.excel14.database.ParseResult;
import com.greycodes.excel14.nontechnical.AlleyDunk;
import com.greycodes.excel14.nontechnical.ByzantineFragment;
import com.greycodes.excel14.nontechnical.DefactoFragment;
import com.greycodes.excel14.nontechnical.FragmentBestManager;
import com.greycodes.excel14.nontechnical.FragmentCSI;
import com.greycodes.excel14.nontechnical.FragmentGameZone;
import com.greycodes.excel14.nontechnical.FragmentInstantPhotography;
import com.greycodes.excel14.nontechnical.FragmentSpiderWeb;
import com.greycodes.excel14.nontechnical.FunZoneFragment;
import com.greycodes.excel14.nontechnical.TikiTakaFragment;
import com.greycodes.excel14.nontechnical.TreasureHuntFragment;

public class NonTechViewPager extends Fragment implements OnClickListener,OnLongClickListener {
	ViewPager view=null;
	public static int  pagetodisplay=0;
	android.support.v4.app.FragmentManager fragmentmanager;
	View rootView;
	ImageView call,result,participate;
	Misc  misc;
	int eid=889;
	
	 ViewPagerParallax pager;
	 ConnectionDetector connectionDetector;
	 Handler h ;
	 ExcelDataBase excelDataBase;
	
	
	 String Ename;
	 boolean team;
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			rootView =	 inflater.inflate(R.layout.fragment_nontech_viewpager, container, false);

			  pager = (ViewPagerParallax) rootView.findViewById(R.id.nontech_pager);
			 fragmentmanager=  getChildFragmentManager();

		        pager.setBackgroundAsset(R.raw.collg1);
		        pager.set_max_pages(11);

		        pager.setAdapter(new NonTechnicalViewPageAdapter(fragmentmanager));
		        pager.setCurrentItem(pagetodisplay);
		       
		         excelDataBase = new ExcelDataBase(getActivity());
		         misc = new Misc(getActivity());
		         connectionDetector = new ConnectionDetector(getActivity());
		         
		         call=(ImageView)rootView.findViewById(R.id.imageView4);
			        result=(ImageView)rootView.findViewById(R.id.imageView2);
			        participate=(ImageView)rootView.findViewById(R.id.imageView3);
			        
			       // call.setOnClickListener(this);
			        result.setOnClickListener(this);
			        participate.setOnClickListener(this);
			        call.setOnLongClickListener(this);
			      call.setOnClickListener(this);
			return rootView;
			
		}

		@Override
		public void onSaveInstanceState(Bundle outState) {
	        super.onSaveInstanceState(outState);
	        outState.putInt("num_pages", 11);
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
				misc.call("08086085727");		
				break;
			case 1:
				misc.call("09567289920");		
				
				break;
			case 2:
				misc.call("09895598464");
				break;
			case 3:
				misc.call("08547833270");
				break;
			case 4:
				misc.call("09809513994");
				break;
			case 5:
				misc.call("08547232371");
				break;
			case 6:
				misc.call("09746834201");
				break;
			case 7:
				misc.call("08907622242");
				break;
			case 8:
				misc.call("09746210049");
				break;
			case 9:
				misc.call("08943529599");
				break;
			case 10:
				misc.call("09995698700");
				break;
		
			
			
			}
			return true;
		}
		   @Override
			public void onPause() {
				// TODO Auto-generated method stub
				super.onPause();
				try {
					getActivity().stopService(new Intent(getActivity(), InsertParticipant.class));
					getActivity().stopService(new Intent(getActivity(), ParseResult.class));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    }
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			switch(v.getId()){
case R.id.imageView3:
			
	Toast.makeText(getActivity(), "Please register on the spot", Toast.LENGTH_LONG).show();
/*
				switch(pager.getCurrentItem()){
				case 0:
					eid =1019;
					Ename="Best Manager";
					team = false;
					break;
				case 1:
					eid =1020;
					Ename="CSI";
					team = false;
					break;
				case 2:
					eid =1021;
					Ename="Game Zone";
					team = false;
					break;
				case 3:
					eid =1022;
					Ename="CSI";
					team = false;
					break;
				case 4:
					eid =1023;
					Ename="CSI";
					team = false;
					break;

				
				
				}
				
				
				if(excelDataBase.Isregistered()){
					Intent service1 = new Intent(getActivity(), InsertParticipant.class);
	    			service1.putExtra("eid", eid);
	    			service1.putExtra("team", team);
	    			service1.putExtra("Ename", Ename);
	    			getActivity().startService(service1);
	    			Toast.makeText(getActivity(), "Please wait...", Toast.LENGTH_LONG).show();
						
				}else{
    					Toast.makeText(getActivity(), "Already Registered", Toast.LENGTH_SHORT).show();
    				}
				*/
				break;
				
			case R.id.imageView2:
				switch(pager.getCurrentItem()){
				case 0:
					eid =1019;	
					break;
				case 1:
					eid =1020;		
					
					break;
				case 2:
					eid =1021;
					break;
				case 3:
					eid =1022;
					break;
				case 4:
					eid =1023;
					break;
				case 5:
					eid =1024;
					break;
				case 6:
					eid =1025;
					break;
				case 7:
					eid =1026;
					break;
				case 8:
					eid =1027;
					break;
				case 9:
					eid =1028;
					break;
				case 10:
					eid =1029;
					break;
			
				
				
				}
				
				Intent service = new Intent(getActivity(), ParseResult.class);
				service.putExtra("eid", eid);
				getActivity().startService(service);
				Toast.makeText(getActivity(), "Please wait...waiting for internet", Toast.LENGTH_LONG).show();				break;
			case R.id.imageView4:
				Toast.makeText(getActivity(), "Press & Hold to call", Toast.LENGTH_LONG).show();

				switch(pager.getCurrentItem()){
				case 0:
					FragmentBestManager.tv.setText("Call Mariya Mathew ?");
				break;
				case 1:
					FragmentCSI.tv.setText("Call Gokul Hariharan?");
					break;
				case 2:
					FragmentGameZone.tv.setText("Call Ajay Jacob John?");
					break;
				case 3:
					FragmentSpiderWeb.tv.setText("Call Nithin K.M?");
					break;
				case 4:
					FragmentInstantPhotography.tv.setText("Call Hafiz C.B?");
					break;
				
				case 5:
					TikiTakaFragment.tv.setText("Call Mohammed P.M?");
					break;
				case 6:
					DefactoFragment.tv.setText("Call Jetha Ajith?");
					break;
				case 7:
					AlleyDunk.tv.setText("Call Kanakajith N?");
					break;
				case 8:
					ByzantineFragment.tv.setText("Call Ajith G?");
					break;
				case 9:
					TreasureHuntFragment.tv.setText("Call Thrishal S?");
					break;
				case 10:
					FunZoneFragment.tv.setText("Call Thabsheera P.K?");
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
				fragment = new FragmentBestManager();
				break;
			case 1:
				fragment = new FragmentCSI();
				
				break;
			case 2:
				fragment = new FragmentGameZone();
				break;
			case 3:
				fragment = new FragmentSpiderWeb();
				break;
			case 4:
				fragment = new FragmentInstantPhotography();
				break;
			case 5:
				fragment = new TikiTakaFragment();
				break;
			case 6:
				fragment = new DefactoFragment();
				break;
			case 7:
				fragment = new AlleyDunk();
				break;
			case 8:
				fragment = new ByzantineFragment();
				break;
			case 9:
				fragment = new TreasureHuntFragment();
				break;
			case 10:
				fragment = new FunZoneFragment();
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
		
			case 5:
				return "TikiTaka";
			case 6:
				return "Defacto";
			case 7:
				return "Alley Dunk";
			case 8:
				return "kluge";
			case 9:
				return "Treasure Hunt";
			case 10:
				return "Crunchy Zone";
			
			}
			return null;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 11;
		}
}
