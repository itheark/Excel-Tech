package com.greycodes.excel14.newsfeed;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class Checkflag extends Service{
	String results=null;
	int newsfeed_flag;
	
	
	

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		Toast.makeText(getApplicationContext(), "onStart", Toast.LENGTH_LONG).show();
		
		Thread thread= new Thread(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
			
			
		};
  		thread.start();
		
		
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		onStart(new Intent(),5);
	}
	
	
	
}
