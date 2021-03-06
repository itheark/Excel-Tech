package com.greycodes.excel14.login;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
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
import com.greycodes.excel14.ConnectionDetector;
import com.greycodes.excel14.R;
import com.greycodes.excel14.database.ParseSignup;

public class LoginActivity extends SherlockActivity implements OnClickListener  {
	
public static Context context;

	private ArrayAdapter<String> adapter;
	String item[]={
	        "Yes", "No"
	      };
	SharedPreferences sp,sharedPreferences;
	Facebook fb;
	TextView Registered;
	String fname,lname,uname,password,email,college,department,phone,accomodation;
	 ConnectionDetector connectionDetector;
	 EditText etemail,etpassword,etphone,etfname,etlname;
	public static Bitmap bmp;
	 AutoCompleteTextView etcollege,etsemester,etaccomodation,etdepartment;
	boolean flag =false;
	CircularImageView pic ;
	ImageView connectfb,signup;
	SharedPreferences.Editor editor;
	Handler h;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_login_activity);
	ActionBar bar = getSupportActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0e1215")));
        bar.setTitle(Html.fromHtml("<font color=\"#e6f3ea\">" + getString(R.string.app_name) + "</font>"));
		connectfb= (ImageView)findViewById(R.id.fbconnect);
		signup= (ImageView)findViewById(R.id.signup);
		Registered = (TextView) findViewById(R.id.clickhere);
		etfname = (EditText) findViewById(R.id.registration_name);
		etlname = (EditText) findViewById(R.id.registration_lastname);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, item);
		etpassword = (EditText) findViewById(R.id.registration_password);
		etemail = (EditText) findViewById(R.id.registration_email);
		etcollege = (AutoCompleteTextView) findViewById(R.id.registration_college);
		etdepartment = (AutoCompleteTextView) findViewById(R.id.registration_collegedept);
		context = LoginActivity.this;
		etphone = (EditText) findViewById(R.id.registration_phone);
		etaccomodation = (AutoCompleteTextView) findViewById(R.id.registration_accomodate);
		etaccomodation.setAdapter(adapter);
		etaccomodation.setThreshold(0);
		connectionDetector = new ConnectionDetector(this);
		pic =(CircularImageView)findViewById(R.id.propic);
		getWindow().setBackgroundDrawableResource(R.drawable.loginback);
		sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
		 editor = sharedPreferences.edit();
		 
		 new AlertDialog.Builder(this)
		    .setTitle("Excel")
		    .setMessage("Signup with Facebook to utilize this App completely :)")
		    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) { 
		            // continue with delete
		        	LoginActivity.this.onClick(connectfb);
		        }
		     })
		    .setNegativeButton("Not now", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) { 
		            // do nothing
		        }
		     })
		    .setIcon(android.R.drawable.ic_dialog_alert)
		     .show();
		 
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
					uname =obj.optString("id");
					fname =obj.optString("first_name");
					email =obj.optString("email");
					lname =obj.optString("last_name");
					etemail.setText(email);
					etfname.setText(fname);
					etlname.setText(lname);
					
					img_url =new URL("https://graph.facebook.com/"+uname+"/picture?type=normal");
					 bmp =BitmapFactory.decodeStream(img_url.openConnection().getInputStream());
					
				//	bmp= getCroppedBitmap(bmp);
					
					pic.setImageBitmap(bmp);
					
					
					
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
		            params.putString("name", "Excel 2014"); 
						params.putString("caption", " Nucleus Properties presents Excel 2014"); 
						params.putString("description", "Conceived by the students of Govt. Model Engineering College,Kochi, Excel promotes interaction within the engineering community.#excelmec ");
						params.putString("link", "https://play.google.com/store/apps/details?id=com.greycodes.excel14");
						params.putString("picture", "http://greycodes.com/image_icons/qrcode.png"); 

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
			fname = etfname.getText().toString();
			
			lname = etlname.getText().toString();
			
			password = etpassword.getText().toString();
			email = etemail.getText().toString();
			college = etcollege.getText().toString();
			department = etdepartment.getText().toString();
			
			phone= etphone.getText().toString();
			accomodation = etaccomodation.getText().toString();
		//	!android.util.Patterns.EMAIL_ADDRESS.matcher(validEmail).matches()
			
			if(fname.length()<3&&lname.length()==0){
				alertshow("Plese enter a valid name");
			}else if(password.length()<8){
				alertshow("Password must contain minimum 8 characters");
			}else if(email.length()==0){
				alertshow("Please enter your email id");
			}else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
				alertshow("Please enter a valid email id");
			}else if(college.length()<1){
				alertshow("Please enter your college name");
			}else if(department.length()<2){
				alertshow("Please enter your departmant");
			}else if(phone.length()<10){
				alertshow("Please enter a valid mobile number");
			}else if(accomodation.length()==0){
			alertshow("Please specify wheather you need accomodation");
			}else{

				fname = fname.replaceAll("\\s","");
				
				lname = lname.replaceAll("\\s","");
				
				password = password.replaceAll("\\s","");
				email = email.replaceAll("\\s","");
				college = college.replaceAll("\\s","");
				department = department.replaceAll("\\s","");
				
				phone= phone.replaceAll("\\s","");
				accomodation = accomodation.replaceAll("\\s","");
				
			
		                     //  ParseSignup parseSignup = new ParseSignup(LoginActivity.this, fname, lname, uname, password, email, college, department, phone, accomodation,bmp,flag);
		               	 
		              // startService(new Intent(LoginActivity.this, ParseSignup.class));
		      Intent service = new Intent(LoginActivity.this, ParseSignup.class);
		      service.putExtra("fname", fname);
		      service.putExtra("lname", fname);
		      service.putExtra("uname", uname);
		      service.putExtra("pass", password);
		      service.putExtra("email", email);
		      service.putExtra("college", college);
		      service.putExtra("dept", department);
		      service.putExtra("phone", phone);
		      service.putExtra("acc", accomodation);
		      service.putExtra("fb", flag);
		      
		      startService(service);
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
		
	
	