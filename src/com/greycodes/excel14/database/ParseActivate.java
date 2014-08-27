package com.greycodes.excel14.database;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Currency;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.IBinder;
import android.widget.Toast;

public class ParseActivate extends Service{
Context context;
String results,url;
int activate;
SharedPreferences sharedPreferences;

	public class ParseAsync extends AsyncTask<String, String, String>  {

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
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			JSONObject jsonObject;
			try{
				jsonObject = new JSONObject(results);
				activate = jsonObject.getInt("activate")	; 
				

			}catch(JSONException e){
				e.printStackTrace();
				
			}
			
			if(activate==2){
				sharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE);
			Editor editor = sharedPreferences.edit();
			editor.putBoolean("active", true);
			editor.commit();
			}
			//	Toast.makeText(context, "Account not activated", Toast.LENGTH_LONG).show();
			
		}

		
	}
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		String[] columns = {"PID" };
	ExcelDataBase	excelDataBase = new ExcelDataBase(getApplicationContext());
		SQLiteDatabase sqLiteDatabase = excelDataBase.getSQLiteDataBase();
		Cursor cursor = sqLiteDatabase.query("USER", columns, null,null, null, null, null);
		cursor.moveToFirst();	if(cursor.getCount()>0){
			int pid =cursor.getInt(cursor.getColumnIndex("PID"));
			url ="http://excelmec.org/Login2014/check_active.php?pid="+pid;
		}
		
		new ParseAsync().execute(url);
		
		}
	
}
