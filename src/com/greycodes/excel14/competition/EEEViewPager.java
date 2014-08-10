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
import com.greycodes.excel14.database.ExcelDataBase;
import com.greycodes.excel14.database.InsertParticipant;
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
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			rootView =	 inflater.inflate(R.layout.fragment_eee_viewpager, container, false);

			  pager = (ViewPagerParallax) rootView.findViewById(R.id.eeepager);
			 fragmentmanager=  getChildFragmentManager();
			 pager.set_max_pages(3);
		        pager.setBackgroundAsset(R.raw.noweeee);
		        pager.setAdapter(new EEEViewPageAdapter(fragmentmanager));
		        pager.setCurrentItem(0);
		        excelDataBase = new ExcelDataBase(getActivity());
		         misc = new Misc(getActivity());
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
				InsertParticipant insertParticipant = new InsertParticipant(getActivity());
				
				switch(pager.getCurrentItem()){
				case 0:
					if(excelDataBase.Isregistered()){
						if(insertParticipant.insert(020, "Lumiere")){
							Toast.makeText(getActivity(), "Added", Toast.LENGTH_SHORT).show();
			}else{
							Toast.makeText(getActivity(), "Already Registered", Toast.LENGTH_SHORT).show();
						}
					}
					
				break;
				case 1:
					if(excelDataBase.Isregistered()){
						if(insertParticipant.insert(021, "Extundo Prodigo")){
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
				
				
				}
				break;
			case R.id.imageView4:
				switch(pager.getCurrentItem()){
				case 0:
					FragmentLumiere.tv.setText("Cordinator");
				break;
				case 1:
					FragmentExtundoprodigo.tv.setText("Cordinator");
					break;
				case 2:
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
