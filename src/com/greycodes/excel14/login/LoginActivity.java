package com.greycodes.excel14.login;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.SyncStateContract.Constants;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.facebook.android.Util;
import com.greycodes.excel14.HomeNDActivity;
import com.greycodes.excel14.R;

public class LoginActivity extends Activity implements OnClickListener  {
	



	SharedPreferences sp;
	Facebook fb;
	TextView Registered;
	
	 EditText username;
	 EditText useremail;
	public   String name ="Hello";
	public   String email= "Click Here to Begin";
	CircularImageView pic ;
	ImageView connectfb,signup;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_login_activity);
		android.app.ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0e1215")));
        bar.setTitle(Html.fromHtml("<font color=\"#e6f3ea\">" + getString(R.string.app_name) + "</font>"));
		connectfb= (ImageView)findViewById(R.id.fbconnect);
		signup= (ImageView)findViewById(R.id.signup);
		Registered = (TextView) findViewById(R.id.clickhere);
		
		username= (EditText) findViewById(R.id.name);
		useremail= (EditText) findViewById(R.id.email);
		pic =(CircularImageView)findViewById(R.id.propic);
		Registered.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				Intent intent=  new Intent(LoginActivity.this,SigninActivity.class);
				startActivity(intent);
			
			}
		});
		
		String APP_ID ="1404508176479531";
		fb =new Facebook(APP_ID);
		sp =getPreferences(MODE_PRIVATE);
		
		String access_token = sp.getString("access_token", null);
		long expires =sp.getLong("access_expires",0);
		
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
		
		signup.setOnClickListener(this);
		connectfb.setOnClickListener(this);
		update();	
		
		
		
		
		
		
		
		
}
	
	
	
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		overridePendingTransition(R.anim.fadeinright,R.anim.fadeoutleft);
		
	}
	
	
	
	
	
	

	private void update() {
		// TODO Auto-generated method stub
		
		if(fb.isSessionValid())
		{
			
			JSONObject obj=null;
			pic.setVisibility(ImageView.VISIBLE);
			URL img_url =null;
		
			
				
				try {
					String jsonUser =fb.request("me");
					obj = Util.parseJson(jsonUser);
					String id =obj.optString("id");
					name =obj.optString("name");
					email =obj.optString("email");
					
					useremail.setText(email);
					username.setText(name);
									
					img_url =new URL("https://graph.facebook.com/"+id+"/picture?type=normal");
					Bitmap bmp =BitmapFactory.decodeStream(img_url.openConnection().getInputStream());
					
					bmp= getCroppedBitmap(bmp);
					pic.setImageBitmap(bmp);
					
					
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
		else 
		{
			
			pic.setVisibility(ImageView.INVISIBLE);
		}
		
		
		
		
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
	
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	if(v.equals(connectfb)){	
		if (fb.isSessionValid())
		{
		}
		else 
		{
			fb.authorize(LoginActivity.this, new String[] {"email","publish_checkins"} ,Facebook.FORCE_DIALOG_AUTH,new DialogListener() {
				
				@Override
				public void onFacebookError(FacebookError e) {
					// TODO Auto-generated method stub
					Toast.makeText(LoginActivity.this,"FBerror",Toast.LENGTH_SHORT).show();
				}
				
				@Override
				public void onError(DialogError e) {
					// TODO Auto-generated method stub
					Toast.makeText(LoginActivity.this,"Error",Toast.LENGTH_SHORT).show();
				}
				
				@Override
				public void onComplete(Bundle values) {
					// TODO Auto-generated method stub
					Editor editor=sp.edit();
					editor.putString("access_token", fb.getAccessToken());
					editor.putLong("access_expires", fb.getAccessExpires());
					editor.commit();
					update();
					Toast.makeText(LoginActivity.this,"Logged IN",Toast.LENGTH_LONG).show();
					
					
					
					
					Bundle params = new Bundle();
		            params.putString("place", "104014799634228");  // YOUR PLACE ID
		            params.putString("Message","Excel");
		            params.putString("name", "Excel 14"); 
						params.putString("caption", "Excel Android App"); 
						params.putString("description", "Check out the new Excel App");
						params.putString("link", "http://www.excelmec.org/excel2014/");
						params.putString("picture", "http://www.excelmec.org/excel2014/col_logo.png"); 

		            JSONObject coordinates = new JSONObject();
		            try {
		               coordinates.put("latitude",10.028484); // The place Coordinates
		                coordinates.put("longitude",76.328749);
		           } catch (JSONException e) {
		               // TODO Auto-generated catch block
		               e.printStackTrace();
		           }
		            params.putString("coordinates",coordinates.toString());

		            try {
						fb.request("me/feed", params, "POST");
					} catch (FileNotFoundException e) {
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
				
				@Override
				public void onCancel() {
					// TODO Auto-generated method stub
					Toast.makeText(LoginActivity.this,"Canceled",Toast.LENGTH_SHORT).show();
				}
			});
		}
		
	}
		if(v.equals(signup))
		{
			
		

		}
		}
		
		}
		
	
	