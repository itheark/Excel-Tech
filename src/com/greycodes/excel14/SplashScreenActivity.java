package com.greycodes.excel14;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.widget.Toast;
import android.widget.VideoView;

import com.greycodes.excel14.database.ExcelDataBase;
import com.parse.ParseACL;
import com.parse.ParseUser;
import com.parse.PushService;

public class SplashScreenActivity extends Activity implements OnCompletionListener{
 
	Intent intent ;

 @Override
 public void onCreate(Bundle icicle) {
     super.onCreate(icicle);
     setContentView(R.layout.activity_splash_screen);
     
 	// Add your initialization code here
	
     PushService.setDefaultPushCallback(this ,HomeNDActivity.class);

		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();
		// If you would like all objects to be private by default, remove this line.
		defaultACL.setPublicReadAccess(true);
		ParseACL.setDefaultACL(defaultACL, true);
		
		
     setContentView(R.layout.activity_splash_screen);
     
     VideoView video = (VideoView) findViewById(R.id.videoView);
     video.setVideoPath("android.resource://" + getPackageName()+"/raw/splash");
     try {
		
		   String[] columns = {"FNAME"};
		  SQLiteDatabase sqLiteDatabase= new ExcelDataBase(getApplicationContext()).getSQLiteDataBase();
		  Cursor cursor=    sqLiteDatabase.query("USER", columns, null, null, null, null, null);
		  cursor.moveToFirst();
		String name =  cursor.getString(cursor.getColumnIndex("FNAME"));
		Toast.makeText(getApplicationContext(), "Welcome "+name, Toast.LENGTH_LONG).show();
		 cursor.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		//Toast.makeText(getApplicationContext(), ""+e, Toast.LENGTH_LONG).show();
	}
     video.start();
     video.setOnCompletionListener(this);
 }
     
   
 
@Override
public void onCompletion(MediaPlayer mp) {
	// TODO Auto-generated method stub
		 intent = new Intent(this, HomeNDActivity.class);
    startActivity(intent);
    finish();
	
}





     }