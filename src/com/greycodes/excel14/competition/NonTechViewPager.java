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
import com.greycodes.excel14.nontechnical.KapothalonFragment;
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
	 ParseResult parseResult;
	 ProgressDialog progressDialog;
	 String Ename;
	 boolean team;
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			rootView =	 inflater.inflate(R.layout.fragment_nontech_viewpager, container, false);

			  pager = (ViewPagerParallax) rootView.findViewById(R.id.nontech_pager);
			 fragmentmanager=  getChildFragmentManager();
		        pager.set_max_pages(12);
		        pager.setBackgroundAsset(R.raw.blue001);
		        pager.setAdapter(new NonTechnicalViewPageAdapter(fragmentmanager));
		        pager.setCurrentItem(pagetodisplay);
		       
		         excelDataBase = new ExcelDataBase(getActivity());
		         misc = new Misc(getActivity());
		         connectionDetector = new ConnectionDetector(getActivity());
		         parseResult = new ParseResult(getActivity());
		         
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
	        outState.putInt("num_pages", 12);
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
				
	Toast.makeText(getActivity(), "Please wait", Toast.LENGTH_LONG).show();

				switch(pager.getCurrentItem()){
				case 0:
					eid =908;
					Ename="#include";
					team = true;
					break;
				case 1:
					eid =908+1;
					Ename="#include";
					break;
				case 2:
					eid =908+2;
					Ename="#include";
					break;
				case 3:
					eid =908+2;
					Ename="#include";
					break;
				case 4:
					eid =908+2;
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
			       eid=889;
				break;
				case 1:
					Toast.makeText(getActivity(), "result", Toast.LENGTH_SHORT).show();
					 eid=889+1;
					break;
				case 2:
					 eid=889+2;
					break;
				case 3:
					 eid=889+3;
					break;
				case 4:
					 eid=889+4;
				
				
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
				fragment = new KapothalonFragment();
				break;
			case 6:
				fragment = new TikiTakaFragment();
				
				break;
			case 7:
				fragment = new DefactoFragment();
				break;
			case 8:
				fragment = new AlleyDunk();
				break;
			case 9:
				fragment = new ByzantineFragment();
				break;
			case 10:
				fragment = new TreasureHuntFragment();
				break;
			case 11:
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
				return "Kapathalon";
			case 6:
				return "TikiTaka";
			case 7:
				return "Defacto";
			case 8:
				return "Alley Dunk";
			case 9:
				return "Byzantine";
			case 10:
				return "Treasure Hunt";
			case 11:
				return "Fun Zone";
			
			}
			return null;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 12;
		}
}
