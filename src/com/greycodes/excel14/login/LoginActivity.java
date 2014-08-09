package com.greycodes.excel14.login;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.facebook.android.Util;
import com.greycodes.excel14.HomeNDActivity;
import com.greycodes.excel14.R;
import com.greycodes.excel14.database.ExcelDataBase;
import com.parse.Parse;
import com.parse.ParseUser;
import com.parse.PushService;

public class LoginActivity extends Activity implements OnClickListener  {
	



	SharedPreferences sp,sharedPreferences;
	Facebook fb;
	TextView Registered;
	String name,password,email,college,semester,phone,lastname;
	 EditText etname,etlastname;
	 EditText etemail,etpassword,etcollege,etsemester,etphone,etid;
	 Bitmap bmp;
	boolean flag =false;
	CircularImageView pic ;
	ImageView connectfb,signup;
	SharedPreferences.Editor editor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_login_activity);
		connectfb= (ImageView)findViewById(R.id.fbconnect);
		signup= (ImageView)findViewById(R.id.signup);
		Registered = (TextView) findViewById(R.id.clickhere);
		etid= (EditText) findViewById(R.id.registration_username);
		etname= (EditText) findViewById(R.id.registration_name);
		etlastname= (EditText) findViewById(R.id.registration_lastname);
		etemail= (EditText) findViewById(R.id.registration_email);
		etpassword = (EditText) findViewById(R.id.registration_password);
		etcollege = (EditText) findViewById(R.id.registration_college);
		etsemester =(EditText) findViewById(R.id.registration_semester);
		etphone = (EditText) findViewById(R.id.registration_phone);
		pic =(CircularImageView)findViewById(R.id.propic);
		sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
		 editor = sharedPreferences.edit();
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
					name =obj.optString("first_name");
					email =obj.optString("email");
					lastname =obj.optString("last_name");
					etemail.setText(email);
					etname.setText(name);
					etlastname.setText(lastname);
					etid.setText(id);
					img_url =new URL("https://graph.facebook.com/"+id+"/picture?type=normal");
					 bmp =BitmapFactory.decodeStream(img_url.openConnection().getInputStream());
					
				//	bmp= getCroppedBitmap(bmp);
					
					pic.setImageBitmap(bmp);
					
					
					editor.putBoolean("fb", true);
					flag=true;
					
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
			name = etname.getText().toString();
			password = etpassword.getText().toString();
			email = etemail.getText().toString();
			college = etcollege.getText().toString();
			semester = etsemester.getText().toString();
			phone= etphone.getText().toString();
		//	!android.util.Patterns.EMAIL_ADDRESS.matcher(validEmail).matches()
			
			if(name.length()<4){
				alertshow("Plese enter a valid name");
			}else if(password.length()<9){
				alertshow("Password must contain minimum 8 characters");
			}else if(email.length()==0){
				alertshow("Please enter your email id");
			}else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
				alertshow("Please enter a valid email id");
			}else if(college.length()<1){
				alertshow("Please enter your college name");
			}else if(semester.length()==0){
				alertshow("Semester Please!!");
			}else if(phone.length()<10){
				alertshow("Please enter a valid mobile number");
			}else{
				ExcelDataBase excelDataBase = new ExcelDataBase(LoginActivity.this);
			SQLiteDatabase sqLiteDatabase=	excelDataBase.getSQLiteDataBase();
			ContentValues contentValues = new ContentValues();
			contentValues.put("PID", 1);
			contentValues.put("NAME", name);
			contentValues.put("PASSWORD", password);
			contentValues.put("EMAIL", email);
			contentValues.put("SEMESTER", semester);
			contentValues.put("PHONE", phone);
			if(flag){
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
				byte[] byteArray = stream.toByteArray();
				contentValues.put("PICTURE",byteArray );
			}
		if(	sqLiteDatabase.insert("USER", null, contentValues)>=0){
			Toast.makeText(getApplicationContext(), "User inserted", Toast.LENGTH_LONG).show();
			editor.putBoolean("registered", true);
			editor.commit();
			String pid = "excel"+etphone.getText().toString();
			//PushService.subscribe(getApplicationContext(), pid, HomeNDActivity.class);
			PushService.subscribe(this, pid, HomeNDActivity.class, R.drawable.ic_launcher);
		}
			
			Intent intent = new Intent(LoginActivity.this, HomeNDActivity.class);
			startActivity(intent);
			finish();
			
			}

		}
		}
	public void alertshow(String message){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);	                	// Setting Dialog Title
    	alertDialogBuilder.setTitle("Excel");

    	// Setting Dialog Message
    	alertDialogBuilder.setMessage(message);

    	// Setting Icon to Dialog
    	alertDialogBuilder.setIcon(R.drawable.alert);
    	
    	alertDialogBuilder.setNeutralButton("OK", null);
    	
    	AlertDialog alertDialog = alertDialogBuilder.create();
    	alertDialog.show();
	}
	
	
	
	
		}
		
	
	