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
import com.greycodes.excel14.database.ExcelDataBase;
import com.greycodes.excel14.database.InsertParticipant;
import com.greycodes.excel14.database.ParseResult;
import com.greycodes.excel14.ecevents.FragmentCircuim;
import com.greycodes.excel14.ecevents.FragmentDefuse;
import com.greycodes.excel14.ecevents.FragmentExtrinsicity;
import com.greycodes.excel14.general.FragmentDalalBull;
import com.greycodes.excel14.general.FragmentPapyrusOfAni;
import com.greycodes.excel14.general.Fragmentkryptos;

public class GeneralViewPager extends Fragment implements OnClickListener,OnLongClickListener {
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
	
	 
	 String Ename;
	 boolean team;
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			rootView =	 inflater.inflate(R.layout.fragment_general_viewpager, container, false);

			  pager = (ViewPagerParallax) rootView.findViewById(R.id.generalpager);
			 fragmentmanager=  getChildFragmentManager();
		        pager.set_max_pages(3);
		        pager.setBackgroundAsset(R.raw.nowgenral);
		        pager.setAdapter(new GeneralViewPageAdapter(fragmentmanager));
		        pager.setCurrentItem(0);
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
	        outState.putInt("num_pages", 3);
	        final ViewPagerParallax pager = (ViewPagerParallax) rootView.findViewById(R.id.generalpager);
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
				misc.call("09745845449");		
				break;
			case 1:
				misc.call("09495467809");		
				
				break;
			case 2:
				misc.call("09497283109");
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
				

				switch(pager.getCurrentItem()){
				case 0:
					eid =1016;
					Ename="Kryptos";
					team = true;
					break;
				case 1:
					eid =1017;
					Ename="Dalal Bull";
					break;
				case 2:
					eid =1018;
					Ename="Papyrus of Ani";
					team=true;
					break;
				
				
				
				}
				
				
				
				if(excelDataBase.Isregistered()){
					
					Intent service1 = new Intent(getActivity(), InsertParticipant.class);
	    			service1.putExtra("eid", eid);
	    			service1.putExtra("team", team);
	    			service1.putExtra("Ename", Ename);
	    			getActivity().startService(service1);
	    			//Toast.makeText(getActivity(), "Please wait...waiting for internet", Toast.LENGTH_LONG).show();						
				}else{
    				//	Toast.makeText(getActivity(), "Already Registered", Toast.LENGTH_SHORT).show();
    				}
				
				break;
			case R.id.imageView2:
				switch(pager.getCurrentItem()){
				case 0:
					eid =1016;
				break;
				case 1:
					eid =1017;
					break;
				case 2:
					eid =1018;
					break;
				
				
				}
				
				Intent service = new Intent(getActivity(), ParseResult.class);
				service.putExtra("eid", eid);
				getActivity().startService(service);
				//Toast.makeText(getActivity(), "Please wait...waiting for internet", Toast.LENGTH_LONG).show();				break;
			break;
			case R.id.imageView4:
				Toast.makeText(getActivity(), "Press & Hold to call", Toast.LENGTH_LONG).show();

				switch(pager.getCurrentItem()){
				case 0:
					Fragmentkryptos.tv.setText("Call Sanjay Thomas?");
				break;
				case 1:
					FragmentDalalBull.tv.setText("Call Pranav?");
					break;
				case 2:
					FragmentPapyrusOfAni.tv.setText("Call Athira Menon?");
					break;
				
				
				}
				break;
			}
			
			
		
			
			
		}

		
		
	}

	class GeneralViewPageAdapter extends FragmentStatePagerAdapter{

		public GeneralViewPageAdapter(android.support.v4.app.FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int position) {
			// TODO Auto-generated method stub
			Fragment fragment=null;
			switch(position){
			
			case 0:
				fragment = new Fragmentkryptos();
				break;
			case 1:
				fragment = new FragmentDalalBull();
				
				break;
			case 2:
				fragment = new FragmentPapyrusOfAni();
						
				
			}
			return fragment;
		}
		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			switch(position){
			case 0:
				return "Kryptos";
				
			case 1:
				return "DalalBull";
			
			case 2:
				return "Papyrus Of Ani";
			
			
		
			}
			return null;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 3;
		}
}
