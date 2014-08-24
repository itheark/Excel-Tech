package com.greycodes.excel14;


import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import android.widget.Toast;
import android.graphics.PorterDuff.Mode;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.commonsware.cwac.merge.MergeAdapter;
import com.greycodes.excel14.conference.ConferenceViewPager;
import com.greycodes.excel14.database.ExcelDataBase;
import com.greycodes.excel14.database.ParseActivate;
import com.greycodes.excel14.database.ParseQuickOpen;
import com.greycodes.excel14.excelgallery.GalleryListActivity;
import com.greycodes.excel14.login.AccountFragment;
import com.greycodes.excel14.login.LoginActivity;
import com.greycodes.excel14.newsfeed.NewsFeedFragment;
import com.greycodes.excel14.newsfeed.NewsFeedService;
import com.greycodes.excel14.talkseries.TalkSeriesViewPager;
import com.parse.ParseAnalytics;
import com.parse.PushService;



public class HomeNDActivity extends SherlockFragmentActivity {
//variable declaration
	

static public QuickOpenAdapter adapter;
	
  
	DrawerLayout hDrawerLayout;
ListView hDrawerList;
ActionBarDrawerToggle hDrawerToggle;
HomeMenuListAdapter hMenuAdapter;
int[] homeoptions;
Intent homeIntent;
UserArrayAdapter userDetails;
public static String[] name = new String[1];
static String[] username = new String[1];
public static Bitmap image;
Fragment f;
public static boolean fbflag=false;
FragmentManager fragmentManager ;
FragmentTransaction transaction;
ProgressDialog pd;
SharedPreferences sharedPreferences;
ExcelDataBase  excelDataBase;
Cursor cursor;
ConnectionDetector connectionDetector;
Handler h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_nd);
        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0e1215")));
        bar.setTitle(Html.fromHtml("<font color=\"#e6f3ea\">" + getString(R.string.app_name) + "</font>"));
    	ParseAnalytics.trackAppOpened(getIntent());   	          	    
		name[0] = "Name";
		username[0]= " ";
		  fragmentManager = getSupportFragmentManager();
		 transaction=fragmentManager.beginTransaction();
		 
		 pd= new ProgressDialog(HomeNDActivity.this);
		 image = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.user_image);
			
		 
		
		MergeAdapter mergeadapter = null;
		
			sharedPreferences = getSharedPreferences("login",Context.MODE_PRIVATE);
			if (sharedPreferences.getBoolean("registered", false)) {
				
				String[] columns = { "FNAME", "PID", "PICTURE" };
				excelDataBase = new ExcelDataBase(getApplicationContext());
				SQLiteDatabase sqLiteDatabase = excelDataBase.getSQLiteDataBase();
				 cursor = sqLiteDatabase.query("USER", columns, null,null, null, null, null);
				cursor.moveToFirst();
				name[0] = cursor.getString(cursor.getColumnIndex("FNAME"));
				//username[0]="PID: ";
				//username[0]+= cursor.getString(cursor.getColumnIndex("PID"));
				username[0]=" ";
				

				if (sharedPreferences.getBoolean("fb", false)) {
					fbflag=true;
					byte[] bs;
					bs = cursor.getBlob(cursor.getColumnIndex("PICTURE"));
					image = BitmapFactory.decodeByteArray(bs, 0, bs.length);
					
				}
				
				if(sharedPreferences.getBoolean("active",false)){
					
					h = new Handler() {
			            @Override
			            public void handleMessage(Message msg) {

			                if (msg.what != 1) { // code if not connected
			                
			             //   Toast.makeText(getApplicationContext(), "No Connection", Toast.LENGTH_LONG).show();
			              
			                	
			                	
			            				
			                } else { // code if connected
			                		      new ParseActivate(getApplicationContext(), 123);
			               	 
			                }   
			            }
			        };
			        
			            
			        ConnectionDetector.isNetworkAvailable(h,3000);
					
				}
			}
		
userDetails = new UserArrayAdapter(getApplicationContext(), name, username, image);
homeoptions=new int[] {R.drawable.home_nd,R.drawable.competition_nd,
        R.drawable.talkseries_nd,R.drawable.conference,R.drawable.proshow_nd,R.drawable.initiatives_nd,
        R.drawable.excelpro_nd,R.drawable.quickopen,R.drawable.live_excel_gallery_nd,R.drawable.info_nd,R.drawable.newsfeeds};
        
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

		
		if(sharedPreferences.getBoolean("registered",false)){
				
			if(sharedPreferences.getBoolean("active",false)){
				 f = new AccountFragment();
			}else{
				h = new Handler() {
		            @Override
		            public void handleMessage(Message msg) {

		                if (msg.what != 1) { // code if not connected
		                
		                Toast.makeText(getApplicationContext(), "No Connection", Toast.LENGTH_LONG).show();
		              
		                	
		                	
		            				
		                } else { // code if connected
		                		      new ParseActivate(getApplicationContext(), 123);
		               	 
		                }   
		            }
		        };
		        
		            
		        ConnectionDetector.isNetworkAvailable(h,3000);
			}

		
		
		}else{
			Intent intent = new Intent(HomeNDActivity.this,LoginActivity.class);
			startActivity(intent);
		}
			
		
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
		f= new TalkSeriesViewPager();
	break;
	case 4:
		f= new ConferenceViewPager();
	break;
	case 5:
		f= new ProShowFragment();
		break;
	case 6:
		f= new InitiativesViewPager();
	break;
	case 7:
		f= new ExcelProViewPager();
		break;
	case 8:
		Toast.makeText(getApplicationContext(), "Please wait..Checking for update", Toast.LENGTH_LONG).show();
		h = new Handler() {
	            @Override
	            public void handleMessage(Message msg) {

	                if (msg.what != 1) { // code if not connected
	                
	                Toast.makeText(getApplicationContext(), "No Connection", Toast.LENGTH_LONG).show();
	              
	                	
	                	
	            				
	                } else { // code if connected
	                		       Toast.makeText(getApplicationContext(), "opening", Toast.LENGTH_SHORT).show();                
	                        ParseQuickOpen quickOpen = new ParseQuickOpen(HomeNDActivity.this);
	                        quickOpen.parseQO();
	               	 
	                }   
	            }
	        };
	        
	            
	        ConnectionDetector.isNetworkAvailable(h,3000);
		break;
	case 9:
		 homeIntent = new Intent(HomeNDActivity.this,GalleryListActivity.class);
			hDrawerLayout.closeDrawer(hDrawerList);
			startActivity(homeIntent);
			overridePendingTransition(R.anim.fadeinright,R.anim.fadeoutleft);

		
		
		break;
	case 10:
		 homeIntent = new Intent(HomeNDActivity.this,InfoNDActivity.class);
		hDrawerLayout.closeDrawer(hDrawerList);
		
		startActivity(homeIntent);
		
			
		
		overridePendingTransition(R.anim.fadeinright,R.anim.fadeoutleft);
		break;
	case 11:
	//	f= new NewsFeedFragment();
		f= new NewsFeedService();
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

public Bitmap getCroppedBitmap(Bitmap bitmap) {
    Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
            bitmap.getHeight(), Config.ARGB_8888);
    Canvas canvas = new Canvas(output);

    final int color = 0xff424242;
    final Paint paint = new Paint();
    final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

    paint.setAntiAlias(true);
    canvas.drawARGB(0, 0, 0, 0);
    paint.setColor(color);
    // canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
    canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
            bitmap.getWidth() / 2, paint);
    paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
    canvas.drawBitmap(bitmap, rect, rect, paint);
    //Bitmap _bmp = Bitmap.createScaledBitmap(output, 60, 60, false);
    //return _bmp;
    return output;
}
    
}
