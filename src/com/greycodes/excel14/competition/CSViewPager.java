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
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.greycodes.excel14.ConnectionDetector;
import com.greycodes.excel14.FirstRunactivity;
import com.greycodes.excel14.Misc;
import com.greycodes.excel14.R;
import com.greycodes.excel14.csevents.FragmentSoYouThink;
import com.greycodes.excel14.csevents.FragmentFourOneTwenty;
import com.greycodes.excel14.csevents.FragmentHackMaster;
import com.greycodes.excel14.csevents.FragmentHashInclude;
import com.greycodes.excel14.csevents.FragmentLOC;
import com.greycodes.excel14.csevents.FragmentWebBots;
import com.greycodes.excel14.csevents.FragmentAlgorithms;
import com.greycodes.excel14.database.ExcelDataBase;
import com.greycodes.excel14.database.InsertParticipant;
import com.greycodes.excel14.database.ParseResult;


public class CSViewPager extends Fragment implements OnClickListener,OnLongClickListener {
ViewPager view=null;
ImageView call,result,participate;

ViewPagerParallax pager;
public static int  pagetodisplay=0;
int eid=889;
//Button call;
private int num_pages = 7;
android.support.v4.app.FragmentManager fragmentmanager;
View rootView;
Misc  misc;
ConnectionDetector connectionDetector;
Handler h ;
ExcelDataBase excelDataBase;
ParseResult parseResult;
String Ename;
boolean team=false;
int tid =0;
ProgressDialog progressDialog;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootView =	 inflater.inflate(R.layout.fragment_cs_viewpager, container, false);
		  pager = (ViewPagerParallax) rootView.findViewById(R.id.pager);
		 fragmentmanager=  getChildFragmentManager();
	        pager.set_max_pages(7);
	        pager.setBackgroundAsset(R.raw.collg1);
	        pager.setAdapter(new CSViewPageAdapter(fragmentmanager));
	        call=(ImageView)rootView.findViewById(R.id.imageView4);
	        result=(ImageView)rootView.findViewById(R.id.imageView2);
	        participate=(ImageView)rootView.findViewById(R.id.imageView3);
	        pager.setCurrentItem(pagetodisplay);
	      
	        excelDataBase = new ExcelDataBase(getActivity());
	         misc = new Misc(getActivity());
	         connectionDetector = new ConnectionDetector(getActivity());
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
	        outState.putInt("num_pages", num_pages);
	        final ViewPagerParallax pager = (ViewPagerParallax) rootView.findViewById(R.id.pager);
	        outState.putInt("current_page", pager.getCurrentItem());
	    }

		
	

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId()){
		case R.id.imageView3:
			Toast.makeText(getActivity(), "Please wait", Toast.LENGTH_LONG).show();
						
			switch(pager.getCurrentItem()){
			case 0:
				eid =889;
				Ename="#include";
				team = true;
				break;
			case 1:
				eid =889+1;
				Ename="#include";
				break;
			case 2:
				eid =889+2;
				Ename="#include";
				break;
			case 3:
				eid =889+3;
				Ename="#include";
				break;
			case 4:
				eid =889+4;
				Ename="#include";
				break;
			case 5:
				eid =889+5;
				Ename="#include";
				break;
			case 6:
				eid =889+6;
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
				break;
			case 5:
				 eid=889+5;
				break;
			case 6:
				 eid=889+6;
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
				FragmentHashInclude.tv.setText("Cordinator");
			break;
			case 1:
				FragmentWebBots.tv.setText("Cordinator");
				break;
			case 2:
				FragmentLOC.tv.setText("Cordinator");
				break;
			case 3:
				FragmentHackMaster.tv.setText("Cordinator");
				break;
			case 4:
				FragmentFourOneTwenty.tv.setText("Cordinator");
				break;
			case 5:
				FragmentAlgorithms.tv.setText("Cordinator");
				break;
			case 6:
				FragmentSoYouThink.tv.setText("Cordinator");
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
		case 2:
			misc.call("09020404022");
			break;
		case 3:
			misc.call("09020404022");
			break;
		case 4:
			misc.call("09020404022");
			break;
		case 5:
			misc.call("09020404022");
			break;
		case 6:
			misc.call("09020404022");
			break;
		
		}
		return true;
	}

	
	
}

class CSViewPageAdapter extends FragmentStatePagerAdapter{

	public CSViewPageAdapter(android.support.v4.app.FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		Fragment fragment=null;
		switch(position){
		
		case 0:
			fragment = new FragmentHashInclude();
			break;
		case 1:
			fragment = new FragmentWebBots();
			break;
		case 2:
			fragment = new FragmentLOC();
			break;
		case 3:
			fragment = new FragmentHackMaster();
			break;
		case 4:
			fragment = new FragmentFourOneTwenty();
			break;
		case 5:
			fragment = new FragmentAlgorithms();
			break;
		case 6:
			fragment = new FragmentSoYouThink();
			break;
			
		}
		return fragment;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		switch(position){
		case 0:
			return "#Include";
			
		case 1:
			return "Web Bots";
		
		case 2:
			return "Lord Of Code";
		
		case 3:
			return "Hackmaster";
	
		case 4:
			return "4*120";
		case 5:
			return "Algorithms";
		case 6:
			return "SYTYCC";
		}
		return null;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 7;
	}
	
}
