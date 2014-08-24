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
import android.widget.Toast;

import com.greycodes.excel14.HomeNDActivity;
import com.greycodes.excel14.R;
import com.parse.PushService;

public class ParseSignup {
Context context;
SharedPreferences sharedPreferences;
String fname,lname,uname,phone,pass,email,college,dept,sem,acc,results,url;
boolean fb;
Bitmap propic;
Editor editor;
ProgressDialog progressDialog;
int username_flag,email_flag,success,pid;
	public ParseSignup(Context context,String fname,String lname,String uname,String pass,String email,String college,String dept,String sem,String phone,String acc,Bitmap propic,boolean fb){
		// TODO Auto-generated constructor stub
		this.context =context;
		this.fname = fname;
		this.lname = lname;
		this.uname = uname;
		this.pass = pass;
		this.email = email;
		this.college = college;
		this.dept = dept;
		this.sem = sem;
		this.phone = phone;
		this.acc = acc;
		this.fb=  fb;
		if(fb)
			this.propic= propic;
		url= "http://excelapi.net84.net/signup.json";
		try {
			new SignupAsync().execute(url).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void insert(){
		
		ExcelDataBase excelDataBase = new ExcelDataBase(context);
		SQLiteDatabase sqLiteDatabase=	excelDataBase.getSQLiteDataBase();
		sharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE);
		 editor = sharedPreferences.edit();
		ContentValues contentValues = new ContentValues();
		contentValues.put("PID", pid);
		contentValues.put("FNAME", fname);
		contentValues.put("LNAME", lname);
		contentValues.put("UNAME", uname);
		contentValues.put("PASSWORD", pass);
		contentValues.put("EMAIL", email);
		contentValues.put("COLLEGE", college);
		contentValues.put("DEPT", dept);
		contentValues.put("SEMESTER", sem);
		contentValues.put("PHONE", phone);
		if(fb){
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			propic.compress(Bitmap.CompressFormat.JPEG, 100, stream);
			byte[] byteArray = stream.toByteArray();
			contentValues.put("PICTURE",byteArray );
		}
		if(	sqLiteDatabase.insert("USER", null, contentValues)>=0){
			Toast.makeText(context, "User inserted", Toast.LENGTH_LONG).show();
			editor.putBoolean("registered", true);
			if(fb){
				Toast.makeText(context, "FB TRUE", Toast.LENGTH_LONG).show();
				editor.putBoolean("active",true);
				editor.putBoolean("fb", true);
			}
			
			editor.commit();
			
			//PushService.subscribe(getApplicationContext(), pid, HomeNDActivity.class);
			PushService.subscribe(context,"user"+pid, HomeNDActivity.class,R.drawable.excel_logo);
		//	Toast.makeText(context, "Acoount Created.Please check your mail to activate the account", Toast.LENGTH_LONG).show();
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);	                	// Setting Dialog Title
    	alertDialogBuilder.setTitle("Excel");

    	// Setting Dialog Message
    	alertDialogBuilder.setMessage("Acoount Created.Please check your mail to activate the account");

    	// Setting Icon to Dialog
    	alertDialogBuilder.setIcon(R.drawable.alert);
    	
    	alertDialogBuilder.setNeutralButton("OK", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				Intent intent = new Intent(context, HomeNDActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
				context.startActivity(intent);
				
			}
		});
    	
    	AlertDialog alertDialog = alertDialogBuilder.create();
    	alertDialog.show();
		
		}
		
		
		
		
		
		
			
		
		
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
				JSONObject jsonObject;
				try{
					jsonObject = new JSONObject(results);
					pid = jsonObject.getInt("pid");
					success = jsonObject.getInt("success");
					username_flag = jsonObject.getInt("username");
					email_flag = jsonObject.getInt("email");
							 
					

				}catch(JSONException e){
					e.printStackTrace();
				}
			}
			return results;
			
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			   progressDialog = ProgressDialog.show(context, "Excel", "Please Wait...");
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(success==1){
				insert();
			}else
				if(success==2){
					if(username_flag==2){
						alertshow("Username already taken :(");
					}else if(email_flag==2){
						alertshow("Your email id is already registered with us");
					}
				}
				
				
				
			
		}

		
	}
	public void alertshow(String message){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);	                	// Setting Dialog Title
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
