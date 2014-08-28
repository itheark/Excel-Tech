package com.greycodes.excel14.login;

import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.facebook.android.Util;
import com.greycodes.excel14.R;
import com.greycodes.excel14.database.SigninParse;

public class SigninActivity extends SherlockActivity implements OnClickListener {
	
	
	Facebook fb;
	
	SharedPreferences sp;
	int id=0;
	ImageView connectfb,signin;
	TextView tvemail,tvpass;
	String email,pass;
	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	 
		ActionBar bar = getSupportActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0e1215")));
        bar.setTitle(Html.fromHtml("<font color=\"#e6f3ea\">" + getString(R.string.app_name) + "</font>"));
		setContentView(R.layout.user_registration_activity);
		connectfb= (ImageView)findViewById(R.id.fbconnect);
		signin= (ImageView)findViewById(R.id.signup);
		getWindow().setBackgroundDrawableResource(R.drawable.loginback);
		String APP_ID ="1404508176479531";
		fb =new Facebook(APP_ID);
		sp =getPreferences(MODE_PRIVATE);
		tvemail = (TextView) findViewById(R.id.registration_email);
		tvpass = (TextView) findViewById(R.id.registration_password);
		
		String access_token = sp.getString("access_token", null);
		long expires =sp.getLong("access_expires",0);
		signin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				email  = tvemail.getText().toString();
				pass = tvpass.getText().toString();
				Intent intent = new Intent(SigninActivity.this, SigninParse.class);
				intent.putExtra("email", email);
				intent.putExtra("pass", pass);
				intent.putExtra("fb", id);
				startService(intent);
				
				
			}
		});
		if (access_token!=null)
		{
			fb.setAccessToken(access_token);
			
		}
		if (expires!=0)
		{
			fb.setAccessExpires(expires);
		}
		
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		StrictMode.setThreadPolicy(policy);
		connectfb.setOnClickListener(this);
		update();	
		
		
	}
	
	

	private void update() {
		// TODO Auto-generated method stub
		
		if(fb.isSessionValid())
		{
			
			JSONObject obj=null;
			
		
			
				
				try {
					String jsonUser =fb.request("me");
					obj = Util.parseJson(jsonUser);
					 id =Integer.parseInt(obj.optString("id"));
					Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
				
					
				} catch (FacebookError e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			
		
		
		}
		
		
		
		
		
	}



	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		stopService(new Intent(SigninActivity.this, SigninParse.class) );
		
		}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
	if(v.equals(connectfb)){	
		if (fb.isSessionValid())
		{
		}
		else 
		{
			fb.authorize(SigninActivity.this, new String[] {"email"} ,Facebook.FORCE_DIALOG_AUTH,new DialogListener() {
				
				@Override
				public void onFacebookError(FacebookError e) {
					// TODO Auto-generated method stub
					Toast.makeText(SigninActivity.this,"FBerror",Toast.LENGTH_SHORT).show();
				}
				
				@Override
				public void onError(DialogError e) {
					// TODO Auto-generated method stub
					Toast.makeText(SigninActivity.this,"Error",Toast.LENGTH_SHORT).show();
				}
				
				@Override
				public void onComplete(Bundle values) {
					// TODO Auto-generated method stub
					
					update();
					Toast.makeText(SigninActivity.this,"Logged IN",Toast.LENGTH_LONG).show();
					
							
					
					
					
					
				}
				
				@Override
				public void onCancel() {
					// TODO Auto-generated method stub
					Toast.makeText(SigninActivity.this,"Canceled",Toast.LENGTH_SHORT).show();
				}
			});
		}
		
	}
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
