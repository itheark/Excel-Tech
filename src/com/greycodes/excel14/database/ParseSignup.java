package com.greycodes.excel14.database;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.IBinder;
import android.widget.Toast;

import com.greycodes.excel14.HomeNDActivity;
import com.greycodes.excel14.R;
import com.greycodes.excel14.login.LoginActivity;
import com.parse.PushService;

public class ParseSignup extends Service {

SharedPreferences sharedPreferences;
String fname,lname,uname,phone,pass,email,college,dept,acc,results,url;
boolean fb;
Bitmap propic;

Editor editor;
//ProgressDialog progressDialog;
int username_flag,email_flag,success,pid;
/*	public ParseSignup(Context context,String fname,String lname,String uname,String pass,String email,String college,String dept,String phone,String acc,Bitmap propic,boolean fb){
		// TODO Auto-generated constructor stub
		this.context =context;
		this.fname = fname;
		this.lname = lname;
		this.uname = uname;
		this.pass = pass;
		this.email = email;
		this.college = college;
		this.dept = dept;
		
		this.phone = phone;
		this.acc = acc;
		this.fb=  fb;
		if(fb)
			this.propic= propic;
		if(fb){
			this.propic= propic;
			url= "http://excelmec.org/Login2014/signup.php?firstname="+fname+"&lastname="+lname+"&phone="+phone+"&college="+college+"&dept="+dept+"&email="+email+"&password="+pass+"&accom="+acc+"&fbid="+uname;
		}else{
url= "http://excelmec.org/Login2014/signup.php?firstname="+fname+"&lastname="+lname+"&phone="+phone+"&college="+college+"&dept="+dept+"&email="+email+"&password="+pass+"&accom="+acc;

		}
		Toast.makeText(context, "Construcotr", Toast.LENGTH_LONG).show();
	}
	*/
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(), "Please wait...", Toast.LENGTH_LONG).show();
			//new SignupAsync().execute(url);
		fname =intent.getStringExtra("fname");
		lname=intent.getStringExtra("lname");
		uname=intent.getStringExtra("uname");
		pass=intent.getStringExtra("pass");
		email=intent.getStringExtra("email");
		college=intent.getStringExtra("college");
		dept=intent.getStringExtra("dept");
		phone=intent.getStringExtra("phone");
		acc=intent.getStringExtra("acc");
		fb=intent.getBooleanExtra("fb", false);
		
		if(fb){
			propic= LoginActivity.bmp;
			url= "http://excelmec.org/Login2014/signup.php?firstname="+fname+"&lastname="+lname+"&phone="+phone+"&college="+college+"&dept="+dept+"&email="+email+"&password="+pass+"&accom="+acc+"&fbid="+uname;
		}else{
			url= "http://excelmec.org/Login2014/signup.php?firstname="+fname+"&lastname="+lname+"&phone="+phone+"&college="+college+"&dept="+dept+"&email="+email+"&password="+pass+"&accom="+acc;
		}
		
			//Toast.makeText(getApplicationContext(), url, Toast.LENGTH_LONG).show();
		new SignupAsync().execute(url);
	}
	private void insert(){
	
		try {
			ExcelDataBase excelDataBase = new ExcelDataBase(getApplicationContext());
			SQLiteDatabase sqLiteDatabase=	excelDataBase.getSQLiteDataBase();
			sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
			 editor = sharedPreferences.edit();
			ContentValues contentValues = new ContentValues();
			
			contentValues.put("PID", pid);
			contentValues.put("FNAME", fname);
			contentValues.put("LNAME", lname);
			
			contentValues.put("PASSWORD", pass);
			contentValues.put("EMAIL", email);
			contentValues.put("COLLEGE", college);
			contentValues.put("DEPT", dept);
			
			contentValues.put("PHONE", phone);
			if(fb){
				
				
				try {
					ByteArrayOutputStream stream = new ByteArrayOutputStream();
					propic.compress(Bitmap.CompressFormat.JPEG, 100, stream);
					byte[] byteArray = stream.toByteArray();
					contentValues.put("PICTURE",byteArray );
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			if(	sqLiteDatabase.insert("USER", null, contentValues)>=0){
				editor.putBoolean("registered", true);
				if(fb){
					editor.putBoolean("active",true);
					editor.putBoolean("fb", true);
				}
				
				editor.commit();
				
				//PushService.subscribe(getApplicationContext(), pid, HomeNDActivity.class);
				PushService.subscribe(getApplicationContext(),"user"+pid, HomeNDActivity.class,R.drawable.excel_logo);
			//	Toast.makeText(context, "Acoount Created.Please check your mail to activate the account", Toast.LENGTH_LONG).show();
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.context);	                	// Setting Dialog Title
			alertDialogBuilder.setTitle("Excel");
			alertDialogBuilder.setCancelable(false);

			// Setting Dialog Message
			if(fb){
				alertDialogBuilder.setMessage("Account Created Successfully");
			}else{
				alertDialogBuilder.setMessage("Account Created.Please check your mail to activate the account");
			}
			

			// Setting Icon to Dialog
			alertDialogBuilder.setIcon(R.drawable.alert);
			
			alertDialogBuilder.setNeutralButton("OK", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					Intent intent = new Intent(LoginActivity.context, HomeNDActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); 
					startActivity(intent);
					
				}
			});
			
			AlertDialog alertDialog = alertDialogBuilder.create();
			alertDialog.show();
			
			}
		
		} catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		stopSelf();
		
			
		
		
	}
	public class SignupAsync extends AsyncTask<String, String, String>  {

		@Override
		protected String doInBackground(String... params)  {
			// TODO Auto-generated method stub
			
			DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
			HttpPost httppost = new HttpPost(url);
			httppost.setHeader("Content-type","application/json");
			InputStream inputstream = null;
			try{
				org.apache.http.HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity =  response.getEntity();
				inputstream = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream,"UTF-8"),8);
				StringBuilder theStringBuilder = new StringBuilder();
				String line = null;
				while((line= reader.readLine())!=null){
					theStringBuilder.append(line+ '\n');
					
				}
				results = theStringBuilder.toString();
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try{
					if(inputstream!=null)
						inputstream.close();
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
			return results;
			
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			Toast.makeText(getApplicationContext(), "Registering...", Toast.LENGTH_LONG).show();

		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			JSONObject jsonObject;
			try{
				jsonObject = new JSONObject(results);
				pid = jsonObject.getInt("pid");
				success = jsonObject.getInt("success");
				
				
						 
				

			}catch(JSONException e){
				e.printStackTrace();
				stopSelf();
				Toast.makeText(getApplicationContext(), "No network connection", Toast.LENGTH_LONG).show();
			}catch (Exception e) {
				
				Toast.makeText(getApplicationContext(), "No network connection", Toast.LENGTH_LONG).show();			}
			if(success==2){
				insert();
			}else
				if(success==1){
					
					
						alertshow("Email id  already registered with us ");
					stopSelf();
			
				
				
				
			
		}
			
		}
		
	}
	public void alertshow(String message){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.context);	                	// Setting Dialog Title
    	alertDialogBuilder.setTitle("Excel");

    	// Setting Dialog Message
    	alertDialogBuilder.setMessage(message);

    	// Setting Icon to Dialog
    	alertDialogBuilder.setIcon(R.drawable.alert);
    	alertDialogBuilder.setCancelable(false);
    	alertDialogBuilder.setNeutralButton("OK", null);
    	
    	AlertDialog alertDialog = alertDialogBuilder.create();
    	alertDialog.show();
	}
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
