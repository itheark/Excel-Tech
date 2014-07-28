package com.greycodes.excel14;


import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
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
import com.actionbarsherlock.view.MenuItem;
import com.commonsware.cwac.merge.MergeAdapter;
import com.greycodes.excel14.excelgallery.GalleryListActivity;
import com.greycodes.excel14.login.LoginActivity;
import com.greycodes.excel14.newsfeed.NewsFeedFragment;
import com.parse.ParseAnalytics;
import com.parse.PushService;



public class HomeNDActivity extends SherlockFragmentActivity {
//variable declaration
	

	
	
  
	DrawerLayout hDrawerLayout;
ListView hDrawerList;
ActionBarDrawerToggle hDrawerToggle;
HomeMenuListAdapter hMenuAdapter;
int[] homeoptions;
Intent homeIntent;
UserArrayAdapter userDetails;
static String[] name = new String[1];
static String[] username = new String[1];
int[] image = {R.drawable.user_image};
Fragment f;
FragmentManager fragmentManager ;
FragmentTransaction transaction;
ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_nd);
        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0e1215")));
        bar.setTitle(Html.fromHtml("<font color=\"#e6f3ea\">" + getString(R.string.app_name) + "</font>"));
    	ParseAnalytics.trackAppOpened(getIntent());   	          	    
		name[0] = "Name";
		username[0]= "email";
		  fragmentManager = getSupportFragmentManager();
		 transaction=fragmentManager.beginTransaction();
		 
		 pd= new ProgressDialog(HomeNDActivity.this);
		 
		MergeAdapter mergeadapter = null;
userDetails = new UserArrayAdapter(getApplicationContext(), name, username, image);
homeoptions=new int[] {R.drawable.home_nd,R.drawable.competition_nd,
        R.drawable.talkseries_nd,R.drawable.proshow_nd,R.drawable.initiatives_nd,
        R.drawable.excelpro_nd,R.drawable.live_excel_gallery_nd,R.drawable.info_nd,R.drawable.newsfeeds};
        
        hDrawerLayout =(DrawerLayout) findViewById(R.id.drawer_layout_home);
        hDrawerList = (ListView) findViewById(R.id.listview_drawer_home);
        hDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,GravityCompat.START);
        hMenuAdapter= new HomeMenuListAdapter(HomeNDActivity.this, homeoptions);
 mergeadapter = new MergeAdapter();
        mergeadapter.addAdapter(userDetails);
 mergeadapter.addAdapter(hMenuAdapter);
 
        
        hDrawerList.setAdapter(mergeadapter);
      

        hDrawerList.setOnItemClickListener(new DrawerItemClickListener());
         getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        hDrawerToggle= new ActionBarDrawerToggle(this, hDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close)
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
        
        hDrawerLayout.setDrawerListener(hDrawerToggle);
        
        if(savedInstanceState==null)
        {
        	f = new HomeFragment();
        	transaction.replace(R.id.home_content_frame,f);
			// Add this transaction to the back stack
           transaction.commit();
        }
        
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 10);
       
        Intent intent = new Intent(this, PushService.class);

        PendingIntent pintent = PendingIntent.getService(this, 0, intent, 0);
       
        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //for 30 mint 60*60*1000
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                     5*60*1000, pintent);
        
        
        
    }
    
    
   
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if(item.getItemId()==android.R.id.home)
		{
			if(hDrawerLayout.isDrawerOpen(hDrawerList))
			{	hDrawerLayout.closeDrawer(hDrawerList);
			}else{
				hDrawerLayout.openDrawer(hDrawerList);
			}
				
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
	case 0:
		
		

		pd.setMessage("Please Wait..");
		pd.show();
		
		Intent intent = new Intent(HomeNDActivity.this,LoginActivity.class);
		startActivity(intent);
			
		
		break;
	case 1: f = new HomeFragment();
			break;
	case 2:
		homeIntent = new Intent(HomeNDActivity.this,CompetitionNDActivity.class);
		hDrawerLayout.closeDrawer(hDrawerList);
		startActivity(homeIntent);
		overridePendingTransition(R.anim.fadeinright,R.anim.fadeoutleft);		
	break;
	case 3:
		f= new TalkSeriesFragment();
	break;
	case 4:
		f= new ProShowFragment();
		break;
	case 5:
		f= new InitiativesViewPager();
	break;
	case 6:
		f= new ExcelProViewPager();
		break;
		
	case 7:
		 homeIntent = new Intent(HomeNDActivity.this,GalleryListActivity.class);
			hDrawerLayout.closeDrawer(hDrawerList);
			startActivity(homeIntent);
			overridePendingTransition(R.anim.fadeinright,R.anim.fadeoutleft);

		
		
		break;
	case 8:
		 homeIntent = new Intent(HomeNDActivity.this,InfoNDActivity.class);
		hDrawerLayout.closeDrawer(hDrawerList);
		
		startActivity(homeIntent);
		
			
		
		overridePendingTransition(R.anim.fadeinright,R.anim.fadeoutleft);
		break;
	case 9:
		f= new NewsFeedFragment();
		break;
	}
	

	
		if (f != null) {
				transaction.setCustomAnimations(R.anim.fadeinright, R.anim.fadeoutleft, R.anim.fadeinleft, R.anim.fadeoutright);
				transaction.replace(R.id.home_content_frame,f);
				// Add this transaction to the back stack
               transaction.addToBackStack("detail");
               transaction.commit();
               
		};
               
		                
	hDrawerLayout.closeDrawer(hDrawerList);
	
}


@Override
public void onConfigurationChanged(Configuration newConfig) {
	// TODO Auto-generated method stub
	super.onConfigurationChanged(newConfig);
	hDrawerToggle.onConfigurationChanged(newConfig);
}


@Override
protected void onPostCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onPostCreate(savedInstanceState);
	hDrawerToggle.syncState();
}


@Override
protected void onResume() {
	// TODO Auto-generated method stub
	overridePendingTransition(R.anim.fadeinleft,R.anim.fadeoutright);
	super.onResume();
	pd.dismiss();
	
}


    
}
