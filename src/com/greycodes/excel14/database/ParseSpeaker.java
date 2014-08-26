package com.greycodes.excel14.database;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.greycodes.excel14.database.ParseSchedule.parseschedule;

import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.IBinder;
import android.widget.Toast;

public class ParseSpeaker extends Service {
	String results;
	JSONArray jsonarray;
	String[] event,speaker;
	int[] sid;
	int count,i;
	int speaker_flag;

	ExcelDataBase excelDataBase;


	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Toast.makeText(getApplicationContext(), "oncrate", Toast.LENGTH_LONG).show();

	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(), "started", Toast.LENGTH_LONG).show();

		new flagCheck().execute("http://excelapi.net84.net/flag.json");
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	 class Parsespeaker extends AsyncTask<String, String, String>  {

		@Override
		protected String doInBackground(String... params)  {
			// TODO Auto-generated method stub
			
			DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
			HttpPost httppost = new HttpPost("http://excelapi.net84.net/speaker.json");
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
			Toast.makeText(getApplicationContext(), results, Toast.LENGTH_LONG).show();
			JSONObject jsonObject;
			try{
				jsonObject = new JSONObject(results);
				jsonarray = jsonObject.getJSONArray("talkseries");
				count = jsonarray.length();
				event = new String[count];
				speaker = new String[count];
						
				sid = new int[count];
				
				
			 for(i=0;i<count;i++){
				 
				 sid[i] = jsonarray.getJSONObject(i).getInt("sid");
				
				 speaker[i] = jsonarray.getJSONObject(i).getString("speaker");
				
				 event[i] = jsonarray.getJSONObject(i).getString("event");
				
			 }
				
				 
				

			}catch(JSONException e){
				Toast.makeText(getApplicationContext(), "No internet Connectivity"+e, Toast.LENGTH_LONG).show();
			}
			super.onPostExecute(result);
			excelDataBase = new ExcelDataBase(getApplicationContext());
			SQLiteDatabase sqLiteDatabase = excelDataBase.getSQLiteDataBase();
			ContentValues contentValues = new ContentValues();
			try {
				for(i=0;i<count;i++){
					contentValues.put("SID", sid[i]);
					contentValues.put("SPEAKER", speaker[i]);
					contentValues.put("EVENT", event[i]);
					
					sqLiteDatabase.insert("SPEAKERS", null, contentValues);
					Toast.makeText(getApplicationContext(), "speaker Inserted", Toast.LENGTH_LONG).show();
				}
				Toast.makeText(getApplicationContext(), "Talkseries Updated", Toast.LENGTH_LONG).show();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			
           
           stopSelf();
		}

		
	}
	
	
	private class flagCheck extends AsyncTask<String, String, String>{
		  String results;
		  
		  	@Override
		  	protected String doInBackground(String... params) {			
		  			// TODO Auto-generated method stub
		  		
		  		DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
		  		HttpPost httppost = new HttpPost("http://excelapi.net84.net/flag.json");
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
		  		return null;
		  	}

		  	@Override
		  	protected void onPostExecute(String result) {
		  		// TODO Auto-generated method stub
		  		super.onPostExecute(result);
				Toast.makeText(getApplicationContext(), "flagcheck", Toast.LENGTH_LONG).show();

		  		try {
		  			JSONObject jsonObject = new JSONObject(results);
		  			speaker_flag = jsonObject.getInt("speaker");
		  			
		  			//SharedPreferences   sharedPreferences = getActivity().getSharedPreferences("flag", Context.MODE_PRIVATE);
		  			if(speaker_flag==1){
		  				Toast.makeText(getApplicationContext(), "Schedule not released", Toast.LENGTH_LONG).show();
		  				stopSelf();
		  			
		  			}else{
		  				Toast.makeText(getApplicationContext(), "else", Toast.LENGTH_LONG).show();

		  			new Parsespeaker().execute("http://excelapi.net84.net/speaker.json");
		  			}
		  		} catch (JSONException e) {
		  			// TODO Auto-generated catch block
		  			e.printStackTrace();
		  		} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		  		
		  		
		  		
		  	}
		  	  
		    }

}
