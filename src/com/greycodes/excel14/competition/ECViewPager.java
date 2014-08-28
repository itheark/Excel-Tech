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
import com.greycodes.excel14.ecevents.FragmentDefuse;
import com.greycodes.excel14.ecevents.FragmentExtrinsicity;
import com.greycodes.excel14.ecevents.FragmentCircuim;

public class ECViewPager extends Fragment implements OnClickListener,OnLongClickListener {
	ViewPager view=null;
	public static int  pagetodisplay=0;
	android.support.v4.app.FragmentManager fragmentmanager;
	private int num_pages = 3;
	View rootView;
	ImageView call,result,participate;
	Misc  misc;
	int eid=889;
	ExcelDataBase excelDataBase;
	 ViewPagerParallax pager;
	
	 ConnectionDetector connectionDetector;
	 Handler h ;
	 
	
	 ProgressDialog progressDialog;
	 
	 String Ename;
	 boolean team=false;
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			rootView =	 inflater.inflate(R.layout.fragment_ec_viewpager, container, false);
			
			 pager = (ViewPagerParallax) rootView.findViewById(R.id.ecpager);
			 fragmentmanager=  getChildFragmentManager();
		        pager.set_max_pages(3);
		        pager.setBackgroundAsset(R.raw.ecbg);
		        pager.setAdapter(new ECViewPageAdapter(fragmentmanager));
		        pager.setCurrentItem(pagetodisplay);
		        call=(ImageView)rootView.findViewById(R.id.imageView4);
		        result=(ImageView)rootView.findViewById(R.id.imageView2);
		        participate=(ImageView)rootView.findViewById(R.id.imageView3);
		        connectionDetector = new ConnectionDetector(getActivity());
		        excelDataBase = new ExcelDataBase(getActivity());
		   
		         misc = new Misc(getActivity());
		        call.setOnClickListener(this);
		        
		        
		        participate.setOnClickListener(this);
		        call.setOnLongClickListener(this);
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

		@Override
		public boolean onLongClick(View arg0) {
			// TODO Auto-generated method stub
			switch(pager.getCurrentItem()){
			case 0:
				misc.call("09446543432");		
				break;
			case 1:
				misc.call("08089114556");		
				
				break;
			case 2:
				misc.call("08891124181");
				break;
			
			
			}
			return true;
		}

		  
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			switch(v.getId()){
			case R.id.imageView3:

				
				switch(pager.getCurrentItem()){
				case 0:
					eid =1007;
					team=false;
					Ename="Extrinsicity";
					break;
				case 1:
					eid =1008;
					team=false;
					Ename="Defuse";
					break;
				case 2:
					eid =1009;
					team=false;
					Ename="Circuimstance";
					break;
				
				
				
				}
				
				
				
				if(excelDataBase.Isregistered()){
					Intent service1 = new Intent(getActivity(), InsertParticipant.class);
	    			service1.putExtra("eid", eid);
	    			service1.putExtra("team", team);
	    			service1.putExtra("Ename", Ename);
	    			getActivity().startService(service1);
	    			Toast.makeText(getActivity(), "Please wait...waiting for internet", Toast.LENGTH_LONG).show();    					
    	}else{
    				//	Toast.makeText(getActivity(), "Already Registered", Toast.LENGTH_SHORT).show();
    				}
				
				break;
			case R.id.imageView2:
				switch(pager.getCurrentItem()){
				case 0:
					eid =1007;
				break;
				case 1:
					eid =1008;
					break;
				case 2:
					eid =1009;
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
					FragmentExtrinsicity.tv.setText("Call Anoop George?");
				break;
				case 1:
					FragmentDefuse.tv.setText("Call Muhammed Zeeshan ?");
					break;
				case 2:
					FragmentCircuim.tv.setText("Call Althaf Ameen ?");
					break;
				
				
				}
				break;
			}
			
			
		
			
			
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
				fragment = new FragmentDefuse();
				break;
			case 2:
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
				return "Defuse";
			
			case 2:
				return "Circuimstance";
			
		
		
			}
			return null;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 3;
		}
}
