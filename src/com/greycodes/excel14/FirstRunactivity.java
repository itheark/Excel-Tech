package com.greycodes.excel14;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.greycodes.excel14.database.ParseCompetition;
import com.greycodes.excel14.database.ParseNewsFeed;

public class FirstRunactivity extends SherlockActivity {
	ParseCompetition parseCompetition;
	ParseNewsFeed parseNewsFeed;
	ConnectionDetector connectionDetector;
	Intent intent;
	int flag;
	Handler h ;
	public boolean compflag,nfeedflag;
	int insertedcompetition,insertednewsfeed;
	String[] column;
	ProgressDialog progressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_runactivity);
		 ActionBar bar = getSupportActionBar();
	        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0e1215")));
	        bar.setTitle(Html.fromHtml("<font color=\"#e6f3ea\">" + getString(R.string.app_name) + "</font>"));
		compflag=nfeedflag=false;
		parseCompetition = new ParseCompetition(this);
		parseNewsFeed= new ParseNewsFeed(this);
		connectionDetector = new ConnectionDetector(this);
		
		 h = new Handler() {
	            @Override
	            public void handleMessage(Message msg) {

	                if (msg.what != 1) { // code if not connected
	                progressDialog.cancel();
	                	alertshow("No Internet Connection");
	               
	                	
	                	
	            				
	                } else { // code if connected
		       
					Object comp=   parseCompetition.executeparse();
		           
					
		           
	                	
	                	
	               	 
	                }   
	            }
	        };
	        
	        progressDialog = ProgressDialog.show(FirstRunactivity.this, "Excel", "Please Wait...");
	        ConnectionDetector.isNetworkAvailable(h,5000);
	       
	        
	       
	     /*  else{
	        	Toast.makeText(this, "Error...exiting app", Toast.LENGTH_LONG).show();
	        	finish();          
	            moveTaskToBack(true);
	       // }
		
				*/
	}
	public void finishFirstrun(){
		finish();
	}
public void setcompflag(){
	compflag=true;
}
public void setnfeedflag(){
	nfeedflag=true;
}


	public void dbcreated() {
		
		intent = new Intent(FirstRunactivity.this, HomeNDActivity.class);
		startActivity(intent);
		finish();
	}
	public void alertshow(String message){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(FirstRunactivity.this);	                	// Setting Dialog Title
    	alertDialogBuilder.setTitle("Excel");

    	// Setting Dialog Message
    	alertDialogBuilder.setMessage(message);

    	// Setting Icon to Dialog
    	alertDialogBuilder.setIcon(R.drawable.alert);
    	alertDialogBuilder.setPositiveButton("Try again", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
		        progressDialog = ProgressDialog.show(FirstRunactivity.this, "Excel", "Please Wait...");

				 ConnectionDetector.isNetworkAvailable(h,4000);
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