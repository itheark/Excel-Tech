package com.greycodes.excel14.database;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import com.greycodes.excel14.HomeNDActivity;
import com.greycodes.excel14.R;
import com.parse.PushService;

import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.IBinder;
import android.widget.Toast;

public class SigninParse extends Service {

String results,url;
int success,active,pid,fb;
JSONObject user;
String fname,lname,phone,college,dept,email,acc,pass;
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		email = intent.getStringExtra("email");
		pass = intent.getStringExtra("pass");
		fb = intent.getIntExtra("fb", 0);
Toast.makeText(getApplicationContext(), "waiting for internet", Toast.LENGTH_LONG).show();
		if(fb==0){
			url = "http://excelmec.org/Login2014/signin.php?email="+email+"&pass="+pass;	
		}else
		{
			url = "http://excelmec.org/Login2014/signin.php?fbid="+fb;
		}
		
	new Signinparse().execute(url);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	public class Signinparse extends AsyncTask<String, String, String>  {

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
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			JSONObject jsonObject;
			try{
				jsonObject = new JSONObject(results);
				 user = jsonObject.getJSONObject("user");
				success = user.getInt("success");
						 
				if(success==1){
					Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_LONG).show();
				}else
					if(success==2){

					fname=	user.getString("fname");
					lname=	user.getString("lname");
					phone=	user.getString("phone");
					college=	user.getString("college");
						dept=user.getString("dept");
					email=	user.getString("email");
					acc=	user.getString("accom");
						
				active=		user.getInt("active");
				pid =		user.getInt("pid");	
							
				ExcelDataBase excelDataBase = new ExcelDataBase(getApplicationContext());
				SQLiteDatabase sqLiteDatabase=	excelDataBase.getSQLiteDataBase();
			SharedPreferences	sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
				Editor editor = sharedPreferences.edit();
				ContentValues contentValues = new ContentValues();
				contentValues.put("PID", pid);
				contentValues.put("FNAME", fname);
				contentValues.put("LNAME", lname);
				
				contentValues.put("PASSWORD", pass);
				contentValues.put("EMAIL", email);
				contentValues.put("COLLEGE", college);
				contentValues.put("DEPT", dept);
				
				contentValues.put("PHONE", phone);
				if(	sqLiteDatabase.insert("USER", null, contentValues)>0){
					editor.putBoolean("registered", true);
					if(active==2){
						editor.putBoolean("active",true);
					}
					editor.commit();
					PushService.subscribe(getApplicationContext(),"user"+pid, HomeNDActivity.class,R.drawable.excel_logo);
				Toast.makeText(getApplicationContext(), "Signin successful", Toast.LENGTH_LONG).show();
			Intent intent = new Intent(getApplicationContext(), HomeNDActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
			startActivity(intent);
			stopSelf();
				}else{
					stopSelf();
					Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
				}
					}

			}catch(JSONException e){
				e.printStackTrace();
			}catch (Exception e) {
				Toast.makeText(getApplicationContext(), "No internet "+e, Toast.LENGTH_LONG).show();			}
			
			stopSelf();
				
				
			
		}

		
	}
	
	
	
}
