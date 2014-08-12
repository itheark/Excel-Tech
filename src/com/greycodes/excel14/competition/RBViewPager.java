package com.greycodes.excel14.competition;

import android.app.ProgressDialog;
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
import com.greycodes.excel14.database.ExcelDataBase;
import com.greycodes.excel14.database.InsertParticipant;
import com.greycodes.excel14.database.ParseResult;
import com.greycodes.excel14.eeeevents.FragmentExtundoprodigo;
import com.greycodes.excel14.eeeevents.FragmentLumiere;
import com.greycodes.excel14.robotics.FragmentRobowars;
import com.greycodes.excel14.robotics.FragmentTerrainMaster;

public class RBViewPager extends Fragment implements OnClickListener,OnLongClickListener {
	ViewPager view=null;
	android.support.v4.app.FragmentManager fragmentmanager;
	View rootView;
	ImageView call,result,participate;
	Misc  misc;
	ExcelDataBase excelDataBase;
	 ViewPagerParallax pager;
	 ConnectionDetector connectionDetector;
	 Handler h ;
	 int eid=902;
	 ParseResult parseResult;
	 ProgressDialog progressDialog;
	 String Ename;
	 boolean team;
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			rootView =	 inflater.inflate(R.layout.roboticsviewpager, container, false);

			  pager = (ViewPagerParallax) rootView.findViewById(R.id.roboticspager);
			 fragmentmanager=  getChildFragmentManager();
			 pager.set_max_pages(2);
		        pager.setBackgroundAsset(R.raw.night);
		        pager.setAdapter(new RBViewPageAdapter(fragmentmanager));
		        pager.setCurrentItem(0);
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
	        outState.putInt("num_pages", 2);
	        final ViewPagerParallax pager = (ViewPagerParallax) rootView.findViewById(R.id.roboticspager);
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
		
			
			
			}
			return true;
		}
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			switch(v.getId()){
case R.id.imageView3:
	Toast.makeText(getActivity(), "Please wait", Toast.LENGTH_LONG).show();
		
				
				switch(pager.getCurrentItem()){
				case 0:
					eid =902;
					Ename="#include";
					team = true;
					break;
				case 1:
					eid =902+1;
					Ename="#include";
					break;
				
				
				
				}
				
				
				
				h = new Handler() {
		            @Override
		            public void handleMessage(Message msg) {

		                if (msg.what != 1) { // code if not connected
		               
		                	connectionDetector.noNetworkAlert();;
		               
		                	
		                	
		            				
		                } else { // code if connected
			       
						
		                	if(excelDataBase.Isregistered()){
		                		InsertParticipant insertParticipant = new InsertParticipant(getActivity());

		        				insertParticipant.PInsert(eid, Ename, team);
		        					
		        	}else{
		        					Toast.makeText(getActivity(), "Already Registered", Toast.LENGTH_SHORT).show();
		        				}       	
		               	 
		                }   
		            }
		        };
		        
		        

		        connectionDetector.isNetworkAvailable(h,5000);
				
				break;
			case R.id.imageView2:
				switch(pager.getCurrentItem()){
				case 0:
					Toast.makeText(getActivity(), "result", Toast.LENGTH_SHORT).show();
			       eid=902;
				break;
				case 1:
					Toast.makeText(getActivity(), "result", Toast.LENGTH_SHORT).show();
					 eid=902+1;
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
				Toast.makeText(getActivity(), "Hold to call", Toast.LENGTH_LONG).show();

				switch(pager.getCurrentItem()){
				case 0:
					FragmentRobowars.tv.setText("Cordinator");
				break;
				case 1:
					FragmentTerrainMaster.tv.setText("Cordinator");
					break;
				
				
				
				}
				break;
			}
			
			
		
			
			
		}


		
		
	}

	class RBViewPageAdapter extends FragmentStatePagerAdapter{

		public RBViewPageAdapter(android.support.v4.app.FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int position) {
			// TODO Auto-generated method stub
			Fragment fragment=null;
			switch(position){
			
			case 0:
				fragment = new FragmentRobowars();
				break;
			case 1:
				fragment = new FragmentTerrainMaster();
				
				break;
						
				
			}
			return fragment;
		}
		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			switch(position){
			case 0:
				return "Robowars";
				
			case 1:
				return "TerrainMaster";
			
		
			}
			return null;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 2;
		}
}
