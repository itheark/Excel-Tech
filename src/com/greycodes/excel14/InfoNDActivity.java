package com.greycodes.excel14;


import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.greycodes.excel14.info.ContactsFragment;
import com.greycodes.excel14.info.FloorMapFragment;
import com.greycodes.excel14.info.HospitalityFragment;
import com.greycodes.excel14.info.MapFragment;
import com.greycodes.excel14.info.ScheduleViewPager;

public class InfoNDActivity extends SherlockFragmentActivity {
	DrawerLayout iDrawerLayout;
	ListView iDrawerList;
	ActionBarDrawerToggle iDrawerToggle;
	HomeMenuListAdapter iMenuAdapter;
	int[] ioptions;
	Fragment f;
	FragmentManager fragmentManager ;
	 FragmentTransaction transaction;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getSupportMenuInflater().inflate(R.menu.actionbar_icons, menu);
		
		
		return super.onCreateOptionsMenu(menu);
	}


	Intent iIntent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info_nd);
		
 fragmentManager = getSupportFragmentManager();
	 transaction=fragmentManager.beginTransaction();
		
		ioptions=new int[] {R.drawable.importantcontacts_nd,R.drawable.floormap_nd,R.drawable.schedule_nd,R.drawable.hospitality_nd,R.drawable.map_nd};
		        
		        iDrawerLayout =(DrawerLayout) findViewById(R.id.drawer_layout_info);
		        iDrawerList = (ListView) findViewById(R.id.listview_drawer_info);
		        iDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,GravityCompat.START);
		        iMenuAdapter= new HomeMenuListAdapter(InfoNDActivity.this, ioptions);
		        iDrawerList.setAdapter(iMenuAdapter);
		        iDrawerList.setOnItemClickListener(new DrawerItemClickListener());
		        ActionBar bar = getSupportActionBar();
		        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0e1215")));
		        bar.setTitle(Html.fromHtml("<font color=\"#e6f3ea\">" + getString(R.string.app_name) + "</font>"));
		        getSupportActionBar().setHomeButtonEnabled(true);
		        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		        iDrawerToggle= new ActionBarDrawerToggle(this, iDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close)
		        {
		        	public void onDrawerClosed(View view) {
		                // TODO Auto-generated method stub
		                super.onDrawerClosed(view);
		            }
		 
		            public void onDrawerOpened(View drawerView) {
		                // TODO Auto-generated method stub
		                // Set the title on the action when drawer open
		                super.onDrawerOpened(drawerView);
		            }
		        };
		        
		        iDrawerLayout.setDrawerListener(iDrawerToggle);
		        
		        if(savedInstanceState==null)
		        {
		        	f = new ContactsFragment();
		        	iDrawerLayout.openDrawer(iDrawerList);
		        	transaction.setCustomAnimations(R.anim.fadeinright, R.anim.fadeoutleft, R.anim.fadeinleft, R.anim.fadeoutright);
		        	transaction.replace(R.id.info_content_frame,f);
					// Add this transaction to the back stack
                   transaction.commit();
                   
		        	
		        }
		        
		        
		        
		        
		        
		    }


			@Override
			public boolean onOptionsItemSelected(MenuItem item) {
				// TODO Auto-generated method stub
				if(item.getItemId()==android.R.id.home)
				{
					if(iDrawerLayout.isDrawerOpen(iDrawerList))
					{	iDrawerLayout.closeDrawer(iDrawerList);
					}else{
						iDrawerLayout.openDrawer(iDrawerList);
					}
						
				}
				if(item.getItemId()==R.id.action_home){
					Intent home = new Intent(InfoNDActivity.this,HomeNDActivity.class);
					startActivity(home);
				}
				return super.onOptionsItemSelected(item);
			}
			
		    
		private class DrawerItemClickListener implements ListView.OnItemClickListener {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				selectItem(position);
			}
			
		}
		
		private void  selectItem(int position) {
			Fragment f;
			FragmentManager fragmentManager ;
			 FragmentTransaction transaction;
			 fragmentManager = getSupportFragmentManager();
			 transaction=fragmentManager.beginTransaction();
		 f =null;
						
			switch(position)
			{
			case 0: try{
				f = new ContactsFragment();
			}catch (Exception e) {
				// TODO: handle exception
			}
					break;
			case 1:
					f= new FloorMapFragment();
			break;
			case 2:
				f= new ScheduleViewPager();
			break;
			case 3:
				f= new HospitalityFragment();
				break;
			case 4:
				f= new MapFragment();
				
			break;
		
			}
		
			
				if (f != null) {
					transaction.setCustomAnimations(R.anim.fadeinright, R.anim.fadeoutleft, R.anim.fadeinleft, R.anim.fadeoutright);
						transaction.replace(R.id.info_content_frame,f);
						// Add this transaction to the back stack
	                   transaction.addToBackStack("infoND");
	                   transaction.commit();
	                   
				};
	                   
				                
			iDrawerLayout.closeDrawer(iDrawerList);
			
		}


		@Override
		public void onConfigurationChanged(Configuration newConfig) {
			// TODO Auto-generated method stub
			super.onConfigurationChanged(newConfig);
			iDrawerToggle.onConfigurationChanged(newConfig);
		}


		@Override
		protected void onPostCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onPostCreate(savedInstanceState);
			iDrawerToggle.syncState();
		}
	
	
}
