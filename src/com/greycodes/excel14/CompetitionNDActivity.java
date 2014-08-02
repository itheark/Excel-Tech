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
import com.greycodes.excel14.biomedevents.FragmentLifeLine;
import com.greycodes.excel14.competition.CSFragment;
import com.greycodes.excel14.competition.ECFragment;
import com.greycodes.excel14.competition.EEEViewPager;
import com.greycodes.excel14.competition.GeneralViewPager;
import com.greycodes.excel14.competition.NonTechFragment;


public class CompetitionNDActivity extends SherlockFragmentActivity{
	DrawerLayout cDrawerLayout;
	ListView cDrawerList;
	ActionBarDrawerToggle cDrawerToggle;
	HomeMenuListAdapter cMenuAdapter;
	int[] coptions;
	Intent cIntent;
	Fragment f;
	 FragmentManager fragmentManager ;
	 FragmentTransaction transaction;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_competition_nd);
		 FragmentManager fragmentManager = getSupportFragmentManager();
		 FragmentTransaction transaction=fragmentManager.beginTransaction();
		coptions=new int[] {R.drawable.computer_nd,R.drawable.electronics_nd,R.drawable.electrical_nd,R.drawable.biomedical_nd,R.drawable.general_nd,R.drawable.non_technical_nd};
		        
		        cDrawerLayout =(DrawerLayout) findViewById(R.id.drawer_layout_competition);
		        cDrawerList = (ListView) findViewById(R.id.listview_drawer_competition);
		        cDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,GravityCompat.START);
		        cMenuAdapter= new HomeMenuListAdapter(CompetitionNDActivity.this, coptions);
		        cDrawerList.setAdapter(cMenuAdapter);
		        cDrawerList.setOnItemClickListener(new DrawerItemClickListener());
		        ActionBar bar = getSupportActionBar();
		        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0e1215")));
		        bar.setTitle(Html.fromHtml("<font color=\"#e6f3ea\">" + getString(R.string.app_name) + "</font>"));
		        getSupportActionBar().setHomeButtonEnabled(true);
		        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		        cDrawerToggle= new ActionBarDrawerToggle(this, cDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close)
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
		        
		        cDrawerLayout.setDrawerListener(cDrawerToggle);
		        
		        if(savedInstanceState==null)
		        {
		        	cDrawerLayout.openDrawer(cDrawerList);
		        	
		        }
		        
		        
		        if(savedInstanceState==null)
		        {
		        	f = new   CSFragment();
		        	transaction.setCustomAnimations(R.anim.fadeinright, R.anim.fadeoutleft, R.anim.fadeinleft, R.anim.fadeoutright);
		        	transaction.replace(R.id.competition_content_frame,f);
					// Add this transaction to the back stack
                  
                   transaction.commit();
		        }
		        
		        
		    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getSupportMenuInflater().inflate(R.menu.actionbar_icons, menu);
		
		
		return super.onCreateOptionsMenu(menu);
	}

			@Override
			public boolean onOptionsItemSelected(MenuItem item) {
				// TODO Auto-generated method stub
				if(item.getItemId()==android.R.id.home)
				{
					if(cDrawerLayout.isDrawerOpen(cDrawerList))
					{	cDrawerLayout.closeDrawer(cDrawerList);
					}else{
						cDrawerLayout.openDrawer(cDrawerList);
					}
						
				}
				if(item.getItemId()==R.id.action_home){
					Intent home = new Intent(CompetitionNDActivity.this,HomeNDActivity.class);
					fragmentManager.popBackStack();
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
			 
			f =null;
						
			switch(position)
			{
			case 0: f = new CSFragment();
					break;
			case 1:
				f= new ECFragment();		
			break;
			case 2:
				f= new EEEViewPager();
				break;
			case 3:
				f= new FragmentLifeLine();
				break;
			case 4:
				f= new GeneralViewPager();
			break;
			case 5:
				f= new NonTechFragment();
				break;
			}
			
			FragmentManager fragmentManager = getSupportFragmentManager();
			 FragmentTransaction transaction=fragmentManager.beginTransaction();
			
				if (f != null) {
						transaction.setCustomAnimations(R.anim.fadeinright, R.anim.fadeoutleft, R.anim.fadeinleft, R.anim.fadeoutright);
						transaction.replace(R.id.competition_content_frame,f);
						// Add this transaction to the back stack
	                   transaction.addToBackStack("detail");
	                   transaction.commit();
	                   
				};
				
				                
			cDrawerLayout.closeDrawer(cDrawerList);
			
		}


		@Override
		public void onConfigurationChanged(Configuration newConfig) {
			// TODO Auto-generated method stub
			super.onConfigurationChanged(newConfig);
			cDrawerToggle.onConfigurationChanged(newConfig);
		}


		@Override
		protected void onPostCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onPostCreate(savedInstanceState);
			cDrawerToggle.syncState();
		}
	

}
