package com.greycodes.excel14;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.VideoView;

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
    
     video.start();
     video.setOnCompletionListener(this);
 }
     
   
 
@Override
public void onCompletion(MediaPlayer mp) {
	// TODO Auto-generated method stub
	SharedPreferences sharedPreferences = getSharedPreferences("app_config", Context.MODE_PRIVATE);
	if(sharedPreferences.getBoolean("DB_Created", false)){
		 intent = new Intent(this, HomeNDActivity.class);
			Toast.makeText(getApplicationContext(), Boolean.toString(sharedPreferences.getBoolean("DB_Created", false)), Toast.LENGTH_LONG).show();	


	}else{
		intent	= new Intent(this, FirstRunactivity.class);

	}
    startActivity(intent);
    finish();
	
}

     }