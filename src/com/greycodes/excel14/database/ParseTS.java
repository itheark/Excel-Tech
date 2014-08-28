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

import com.greycodes.excel14.database.ParseSchedule.parseschedule;

import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.IBinder;
import android.widget.Toast;

public class ParseTS extends Service {

	String results,url;

	

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		new flagCheck().execute("url");
	}

	public class parsets extends AsyncTask<String, String, String>  {

		@Override
		protected String doInBackground(String... params)  {
			// TODO Auto-generated method stub
			
			DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
			HttpPost httppost = new HttpPost("http://excelapi.net84.net/conference.json");
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
				stopSelf();
			}finally{
				try{
					if(inputstream!=null)
						inputstream.close();
				}catch(Exception e){
					stopSelf();
				}
				
			}
			return results;
			
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			try{
				JSONObject jsonObject;
				jsonObject = new JSONObject(results);
				String[] talks = new String[4];
				 talks[0] = jsonObject.getString("seminars");
				 talks[1] = jsonObject.getString("exhibitions");
				 talks[2] = jsonObject.getString("workshops");
				 talks[3] = jsonObject.getString("devcon");
				
				ExcelDataBase excelDataBase = new ExcelDataBase(getApplicationContext());
				SQLiteDatabase sqLiteDatabase = excelDataBase.getSQLiteDataBase();
				ContentValues contentValues = new ContentValues();
				try {
					
						for(int i=0;i<4;i++){
							contentValues.put("TID", i);
							contentValues.put("INTRO", talks[i]);
							sqLiteDatabase.insert("TALKSERIES", null, contentValues);
						}
						
						stopSelf();

			}catch(Exception e){
				Toast.makeText(getApplicationContext(), "No Internet Connectivity", Toast.LENGTH_LONG).show();
				stopSelf();
				
			}
			
			
			//	Toast.makeText(context, "Account not activated", Toast.LENGTH_LONG).show();
			stopSelf();
		}catch(Exception e){
			stopSelf();
			Toast.makeText(getApplicationContext(), "No Internet Connectivity", Toast.LENGTH_LONG).show();
		}

		
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
		  		try {
		  			JSONObject jsonObject = new JSONObject(results);
		  		int	event_flag = jsonObject.getInt("events");
		  			
		  			//SharedPreferences   sharedPreferences = getActivity().getSharedPreferences("flag", Context.MODE_PRIVATE);
		  			if(event_flag==1){
		  				//Toast.makeText(getApplicationContext(), "Schedule not released", Toast.LENGTH_LONG).show();
		  				stopSelf();
		  			
		  			}else{
		  			new parsets().execute("http://excelapi.net84.net/conference.json");
		  			}
		  		} catch (JSONException e) {
		  			// TODO Auto-generated catch block
		  			e.printStackTrace();
		  			stopSelf();
		  			Toast.makeText(getApplicationContext(), "No Internet Connectivity", Toast.LENGTH_LONG).show();
		  		} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					stopSelf();
					Toast.makeText(getApplicationContext(), "No Internet Connectivity", Toast.LENGTH_LONG).show();
				} 
		  		
		  		
		  		
		  	}
		  	  
		    }	
	
}