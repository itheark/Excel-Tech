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
import com.greycodes.excel14.ecevents.FragmentCircuim;
import com.greycodes.excel14.ecevents.FragmentDefuse;
import com.greycodes.excel14.ecevents.FragmentExtrinsicity;
import com.greycodes.excel14.eeeevents.EaventuraFragment;
import com.greycodes.excel14.eeeevents.FragmentExtundoprodigo;
import com.greycodes.excel14.eeeevents.FragmentLumiere;

public class EEEViewPager extends Fragment implements OnClickListener,OnLongClickListener {
	ViewPager view=null;
	android.support.v4.app.FragmentManager fragmentmanager;
	View rootView;
	ImageView call,result,participate;
	Misc  misc;
	ExcelDataBase excelDataBase;
	 ViewPagerParallax pager;
	 ConnectionDetector connectionDetector;
	 Handler h ;
	 int eid=889;
	 ParseResult parseResult;
	 ProgressDialog progressDialog;
	 String Ename;
	 boolean team;
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			rootView =	 inflater.inflate(R.layout.fragment_eee_viewpager, container, false);

			  pager = (ViewPagerParallax) rootView.findViewById(R.id.eeepager);
			 fragmentmanager=  getChildFragmentManager();
			 pager.set_max_pages(3);
		        pager.setBackgroundAsset(R.raw.cityjpg);
		        pager.setAdapter(new EEEViewPageAdapter(fragmentmanager));
		        pager.setCurrentItem(0);
		        excelDataBase = new ExcelDataBase(getActivity());
		         misc = new Misc(getActivity());
		         connectionDetector = new ConnectionDetector(getActivity());
		         call=(ImageView)rootView.findViewById(R.id.imageView4);
			        result=(ImageView)rootView.findViewById(R.id.imageView2);
			        participate=(ImageView)rootView.findViewById(R.id.imageView3);
			        parseResult = new ParseResult(getActivity());
			        call.setOnClickListener(this);
			        result.setOnClickListener(this);
			        participate.setOnClickListener(this);
			        call.setOnLongClickListener(this);
			        
			        
			        
			return rootView;
			
		}
		@Override
		public void onSaveInstanceState(Bundle outState) {
	        super.onSaveInstanceState(outState);
	        outState.putInt("num_pages", 3);
	        final ViewPagerParallax pager = (ViewPagerParallax) rootView.findViewById(R.id.eeepager);
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
				misc.call("09605746681");		
				break;
			case 1:
				misc.call("09633825522");		
				
				break;
			case 2:
				misc.call("09497323463");
			
			
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
					eid =896;
					Ename="#include";
					team = true;
					break;
				case 1:
					eid =896+1;
					Ename="#include";
					break;
				case 2:
					eid =896+2;
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
			       eid=899;
				break;
				case 1:
					Toast.makeText(getActivity(), "result", Toast.LENGTH_SHORT).show();
					 eid=899+1;
					break;
				case 2:
					 eid=899+2;
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
		        connectionDetector.isNetworkAvailable(h,3500);
				break;
			case R.id.imageView4:
				Toast.makeText(getActivity(), "Hold to call", Toast.LENGTH_LONG).show();

				switch(pager.getCurrentItem()){
				case 0:
					FragmentLumiere.tv.setText("Call Asiya ?");
				break;
				case 1:
					FragmentExtundoprodigo.tv.setText("Call Ashwin ?");
					break;
				case 2:
					EaventuraFragment.tv.setText("Call Alex Joy ?");
					break;
				
				
				}
				break;
			}
			
			
		
			
			
		}

		
		
	}

	class EEEViewPageAdapter extends FragmentStatePagerAdapter{

		public EEEViewPageAdapter(android.support.v4.app.FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int position) {
			// TODO Auto-generated method stub
			Fragment fragment=null;
			switch(position){
			
			case 0:
				fragment = new FragmentLumiere();
				break;
			case 1:
				fragment = new FragmentExtundoprodigo();
				
				break;
			case 2:
				fragment = new EaventuraFragment();
				break;
				
			}
			return fragment;
		}
		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			switch(position){
			case 0:
				return "Lumiere";
				
			case 1:
				return "Extundo Prodigo";
			case 2:
				return "E-aventura";
		
			}
			return null;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 3;
		}
}
