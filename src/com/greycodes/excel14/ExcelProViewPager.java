package com.greycodes.excel14;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.greycodes.excel14.competition.ViewPagerParallax;
import com.greycodes.excel14.database.ExcelDataBase;
import com.greycodes.excel14.database.InsertParticipant;
import com.greycodes.excel14.database.ParseResult;
import com.greycodes.excel14.excelpro.MarketManiaFragment;
import com.greycodes.excel14.excelpro.TagURItFragment;
import com.greycodes.excel14.robotics.FragmentRobowars;
import com.greycodes.excel14.robotics.FragmentTerrainMaster;

public class ExcelProViewPager extends Fragment implements OnLongClickListener,OnClickListener {
	ViewPagerParallax pager;
	public static int  tspagetoset=0;
	//Button call;
	private int num_pages = 2;
	android.support.v4.app.FragmentManager fragmentmanager;
	View rootView;
	 ConnectionDetector connectionDetector;
	 Handler h ;
	 int eid=902;
	 ParseResult parseResult;
	 ProgressDialog progressDialog;
	 ImageView call,result,participate;
		Misc  misc;
		ExcelDataBase excelDataBase;
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			rootView =	 inflater.inflate(R.layout.excelpro_viewpager, container, false);
			  pager = (ViewPagerParallax) rootView.findViewById(R.id.excelpropager);
			 fragmentmanager=  getChildFragmentManager();
		        pager.set_max_pages(2);
		        
		        pager.setBackgroundAsset(R.raw.noweee);
		        pager.setAdapter(new ExcelProViewPageAdapter(fragmentmanager));
		        pager.setCurrentItem(tspagetoset);
		      
		        if (savedInstanceState!=null) {
		            num_pages = savedInstanceState.getInt("num_pages");
		            pager.setCurrentItem(savedInstanceState.getInt("current_page"), false);
		        }
		        
		        excelDataBase = new ExcelDataBase(getActivity());
		         misc = new Misc(getActivity());
		         
		         connectionDetector = new ConnectionDetector(getActivity());
		         call=(ImageView)rootView.findViewById(R.id.imageView4);
			        result=(ImageView)rootView.findViewById(R.id.imageView2);
			        participate=(ImageView)rootView.findViewById(R.id.imageView3);
			        parseResult = new ParseResult(getActivity());
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



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId()){
		case R.id.imageView3:
			InsertParticipant insertParticipant = new InsertParticipant(getActivity());
			
			switch(pager.getCurrentItem()){
			case 0:
				if(excelDataBase.Isregistered()){
					if(insertParticipant.insert(922, "Market Mania")){
						Toast.makeText(getActivity(), "Added", Toast.LENGTH_SHORT).show();
		}else{
						Toast.makeText(getActivity(), "Already Registered", Toast.LENGTH_SHORT).show();
					}
				}
				
			break;
			case 1:
				if(excelDataBase.Isregistered()){
					if(insertParticipant.insert(923, "Tag You're It")){
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
				Toast.makeText(getActivity(), "result", Toast.LENGTH_SHORT).show();
		       eid=922;
			break;
			case 1:
				Toast.makeText(getActivity(), "result", Toast.LENGTH_SHORT).show();
				 eid=922+1;
				break;
		
			
			}
			
			h = new Handler() {
	            @Override
	            public void handleMessage(Message msg) {

	                if (msg.what != 1) { // code if not connected
	                progressDialog.cancel();
	                	connectionDetector.noNetworkAlert();;
	               
	                	
	                	
	            				
	                } else { // code if connected
		       
					
		           parseResult.result(eid);
	                	 progressDialog.dismiss();          	
	               	 
	                }   
	            }
	        };
	        
	        progressDialog = ProgressDialog.show(getActivity(), "Excel", "Please Wait...");
	        connectionDetector.isNetworkAvailable(h,5000);
			break;
		case R.id.imageView4:
			switch(pager.getCurrentItem()){
			case 0:
				MarketManiaFragment.tv.setText("Cordinator");
			break;
			case 1:
				TagURItFragment.tv.setText("Cordinator");
				break;
			
			
			
			}
			break;
		}
		
		
	
		
		
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
	
		
		
		}
		return true;
	}
}
