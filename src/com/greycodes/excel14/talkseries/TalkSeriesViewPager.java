package com.greycodes.excel14.talkseries;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.greycodes.excel14.R;
import com.greycodes.excel14.competition.ViewPagerParallax;
import com.greycodes.excel14.database.ExcelDataBase;
import com.greycodes.excel14.database.ParseSpeaker;

public class TalkSeriesViewPager extends Fragment  {
	ViewPagerParallax pager;
	ImageView ispeakers;
	String[] columns,condition;
	public static int  tspagetoset=0;
	Cursor cursor;
	SQLiteDatabase sqLiteDatabase;
	//Button call;
	private int num_pages = 2;
	android.support.v4.app.FragmentManager fragmentmanager;
	View rootView;
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			rootView =	 inflater.inflate(R.layout.talkseries_viewpager, container, false);
			  pager = (ViewPagerParallax) rootView.findViewById(R.id.talkseriespager);
			 fragmentmanager=  getChildFragmentManager();
		        pager.set_max_pages(2);
		        ispeakers = (ImageView) rootView.findViewById(R.id.imageView3);
		        ispeakers.setOnClickListener(new OnClickListener() {
		        	
					
					@Override
					public void onClick(View v) {
						
						columns = new String[]{"SID"};
						
			        	ExcelDataBase excelDataBase = new ExcelDataBase(getActivity());
			        	 sqLiteDatabase=	excelDataBase.getSQLiteDataBase();
			         cursor=	sqLiteDatabase.query("SPEAKERS", columns, null, null, null, null, null);
			        	cursor.moveToFirst();
			        	if(cursor.getCount()==0){
			        		Toast.makeText(getActivity(), "Will be updated soon", Toast.LENGTH_LONG).show();
			        	getActivity().startService(new Intent(getActivity(), ParseSpeaker.class));
			        	}else{
			        		columns = new String[]{"SPEAKER","EVENT"};
			        		excelDataBase = new ExcelDataBase(getActivity());
			        		sqLiteDatabase=	excelDataBase.getSQLiteDataBase();
			        		
			        		switch (pager.getCurrentItem()) {
							case 0:
								condition = new String[]{"dotissue"};
								cursor=	sqLiteDatabase.query("SPEAKERS", columns,"EVENT=?", condition, null, null,null);
				        		cursor.moveToFirst();
			
				        		DotIssueFragment.tv.setText("\n"+cursor.getString(cursor.getColumnIndex("SPEAKER")));
				        		while(cursor.moveToNext()){
					        		DotIssueFragment.tv.append("\n"+cursor.getString(cursor.getColumnIndex("SPEAKER")));

				        		}
				        		DotIssueFragment.tv.append("\n\n");
				        		try {
									cursor.close();
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								break;
							case 1:
								condition = new String[]{"tedxmec"};
								cursor=	sqLiteDatabase.query("SPEAKERS", columns,"EVENT=?", condition, null, null,null);
				        		cursor.moveToFirst();
			
				        		TedXMecFragment.tv.setText("\n"+cursor.getString(cursor.getColumnIndex("SPEAKER")));
				        		while(cursor.moveToNext()){
					        		TedXMecFragment.tv.append("\n"+cursor.getString(cursor.getColumnIndex("SPEAKER")));

				        		}
				        		TedXMecFragment.tv.append("\n\n");
				        		try {
									cursor.close();
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								break;
							
							}
			        		

			        	}
												
					}
				});
		        pager.setBackgroundAsset(R.raw.initiativesbg);
		        pager.setAdapter(new TalkSeriesViewPageAdapter(fragmentmanager));
		        pager.setCurrentItem(tspagetoset);
		      
		        if (savedInstanceState!=null) {
		            num_pages = savedInstanceState.getInt("num_pages");
		            pager.setCurrentItem(savedInstanceState.getInt("current_page"), false);
		        }
		        
		        
		        
				return rootView;

		    }

		    @Override
			public void onSaveInstanceState(Bundle outState) {
		        super.onSaveInstanceState(outState);
		        outState.putInt("num_pages", num_pages);
		        final ViewPagerParallax pager = (ViewPagerParallax) rootView.findViewById(R.id.talkseriespager);
		        outState.putInt("current_page", pager.getCurrentItem());
		    }

			
	
	class TalkSeriesViewPageAdapter extends FragmentStatePagerAdapter{

		public TalkSeriesViewPageAdapter(android.support.v4.app.FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int position) {
			// TODO Auto-generated method stub
			Fragment fragment=null;
			switch(position){
			
			case 0:
				fragment = new DotIssueFragment();
				break;
			case 1:
				fragment = new TedXMecFragment();
				break;
			
				
			}
			return fragment;
		}
		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			switch(position){
			case 0:
				return ".Issue!";
				
			case 1:
				return "TedxMEC";
			
			
		
			}
			return null;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 2;
		}
	}



	




	
}
