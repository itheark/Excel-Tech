package com.greycodes.excel14;

import com.greycodes.excel14.database.ExcelDataBase;
import com.greycodes.excel14.database.ParseCompetition;
import com.greycodes.excel14.database.ParseNewsFeed;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class FirstRunactivity extends Activity {
	ParseCompetition parseCompetition;
	ParseNewsFeed parseNewsFeed;
	ConnectionDetector connectionDetector;
	Intent intent;
	int flag;
	Handler h ;
	int insertedcompetition,insertednewsfeed;
	String[] column;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_runactivity);
		parseCompetition = new ParseCompetition(this);
		parseNewsFeed= new ParseNewsFeed(this);
		connectionDetector = new ConnectionDetector(this);
		
		parseCompetition.executeparse();
        parseNewsFeed.executenewsfeedparse();
    	ExcelDataBase ebs = new ExcelDataBase(getApplicationContext());
    	SQLiteDatabase db = ebs.getSQLiteDataBase();
    	column = new String[]{"EID"};
    Cursor cursor = db.query("COMPETITION", column, null, null, null, null, null);
insertedcompetition=   cursor.getCount();
column = new String[]{"NID"};
cursor = db.query("NEWSFEED", column, null, null, null, null, null);
insertednewsfeed = cursor.getCount();
    	Toast.makeText(getApplicationContext(), "First run activity called", Toast.LENGTH_LONG).show(); 
    	dbcreated();
    	if(insertedcompetition>0||insertednewsfeed>0){
    	dbcreated();
    }else{
    	alertshow();
    }
		/*
		 h = new Handler() {
	            @Override
	            public void handleMessage(Message msg) {

	            	
	            	 
	                
	                if (msg.what != 1) { // code if not connected
	                
	                	alertshow();
	               
	                	
	                	
	            				
	                } else { // code if connected
		                parseCompetition.executeparse();
		                parseNewsFeed.executenewsfeedparse();
	                	ExcelDataBase ebs = new ExcelDataBase(getApplicationContext());
	                	SQLiteDatabase db = ebs.getSQLiteDataBase();
	                	column = new String[]{"EID"};
	                Cursor cursor = db.query("COMPETITION", column, null, null, null, null, null);
	        insertedcompetition=   cursor.getCount();
	        column = new String[]{"NID"};
	        cursor = db.query("NEWSFEED", column, null, null, null, null, null);
	        insertednewsfeed = cursor.getCount();
	                	Toast.makeText(getApplicationContext(), "First run activity called", Toast.LENGTH_LONG).show(); 
	                if(insertedcompetition>0||insertednewsfeed>0){
	                	dbcreated();
	                }else{
	                	alertshow();
	                }
	                	
	               	 
	                }   
	            }
	        };
	        
	            
	        ConnectionDetector.isNetworkAvailable(h,2000);
	       */
	        
	       
	     /*  else{
	        	Toast.makeText(this, "Error...exiting app", Toast.LENGTH_LONG).show();
	        	finish();          
	            moveTaskToBack(true);
	       // }
		
				*/
	}

	private void dbcreated() {
		SharedPreferences sharedPreferences = getSharedPreferences("app_config", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putBoolean("DB_Created", true);
			editor.commit();
		intent = new Intent(this, HomeNDActivity.class);
		startActivity(intent);
		finish();
	}
	private void alertshow(){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(FirstRunactivity.this);	                	// Setting Dialog Title
    	alertDialogBuilder.setTitle("Excel");

    	// Setting Dialog Message
    	alertDialogBuilder.setMessage("No data connection");

    	// Setting Icon to Dialog
    	alertDialogBuilder.setIcon(R.drawable.alert);
    	alertDialogBuilder.setPositiveButton("Try again", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				 ConnectionDetector.isNetworkAvailable(h,2000);
			}
		});
    	
    	alertDialogBuilder.setNegativeButton("Exit", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				finish();          
	            moveTaskToBack(true);
			}
		});
    	AlertDialog alertDialog = alertDialogBuilder.create();
    	alertDialog.show();
	}

}

