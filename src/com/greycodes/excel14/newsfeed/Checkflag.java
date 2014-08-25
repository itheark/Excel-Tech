package com.greycodes.excel14.newsfeed;

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

import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.IBinder;
import android.widget.Toast;

import com.greycodes.excel14.database.ExcelDataBase;

public class Checkflag extends Service{

	int newsfeed_flag=0;
	JSONArray jsonarray;
	String[] subject,message,columns;
	int[] pcode,cat;
	int[] nid;
	int n,i;
	ExcelDataBase excelDataBase;
	

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		Toast.makeText(getApplicationContext(), "onStart", Toast.LENGTH_LONG).show();
		
		new flagCheck().execute("http://excelapi.net84.net/flag.json");
		
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
	}
	
	private class flagCheck extends AsyncTask<String, String, String>{
		  String results;
		  
		  	@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
	  		Toast.makeText(getApplicationContext(), "Preexecute", Toast.LENGTH_LONG).show();

		}

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
		  			Toast.makeText(getApplicationContext(), results, Toast.LENGTH_LONG).show();
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
		  		Toast.makeText(getApplicationContext(), results, Toast.LENGTH_LONG).show();
		  		if(results==null){
		  		
		  			try {
						NewsFeedFragment.mPullToRefreshLayout.setRefreshComplete();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
		  			onDestroy();
		  		}else{
		  			try {
						JSONObject jsonObject = new JSONObject(results);
						newsfeed_flag = jsonObject.getInt("newsfeed");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		  			
		  			SharedPreferences   sharedPreferences = getApplicationContext().getSharedPreferences("flag", Context.MODE_PRIVATE);
		  			if(newsfeed_flag==sharedPreferences.getInt("newsfeed", 1)){
		  				Toast.makeText(getApplicationContext(), "No updates", Toast.LENGTH_LONG).show();
		  			try {
						if(NewsFeedFragment.mPullToRefreshLayout.isRefreshing()){
							NewsFeedFragment.mPullToRefreshLayout.setRefreshComplete();
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		  				
		  			
		  			}else{
		  				Toast.makeText(getApplicationContext(), "PNEWSFEED", Toast.LENGTH_LONG).show();
		  				new PNewsFeed().execute("http://excelapi.net84.net/newsfeed.json");
		  				
		  			}
		  		}
		  		
		  		
		  		
		  		
		  		
		  	}
		  	  
		    }
	
	
	private class PNewsFeed extends AsyncTask<String, String, String>{
String results;
		@Override
		protected String doInBackground(String... params)  {
			// TODO Auto-generated method stub
			
			DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
			HttpPost httppost = new HttpPost("http://excelapi.net84.net/newsfeed.json");
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
					
					
					if (results.length()>10) {
						jsonObject = new JSONObject(results);
						jsonarray = jsonObject.getJSONArray("News");
						subject = new String[jsonarray.length()];
						message = new String[jsonarray.length()];
						pcode = new int[jsonarray.length()];
						cat = new int[jsonarray.length()];
						nid = new int[jsonarray.length()];
						n = jsonarray.length();
						for (int i = 0; i < n; i++) {
							subject[i] = jsonarray.getJSONObject(i).getString(
									"subject");
							message[i] = jsonarray.getJSONObject(i).getString(
									"message");
							pcode[i] = jsonarray.getJSONObject(i).getInt(
									"pcode");
							nid[i] = jsonarray.getJSONObject(i).getInt("nid");
							cat[i] = jsonarray.getJSONObject(i).getInt("cat");

						}
					}
					

				}catch(JSONException e){
					e.printStackTrace();
				}
			}
			return results;
			
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (results.length()>10) {
				excelDataBase = new ExcelDataBase(getApplicationContext());
				SQLiteDatabase sqLiteDatabase = excelDataBase
						.getSQLiteDataBase();
				ContentValues contentValues = new ContentValues();
				for (i = 0; i < n; i++) {
					contentValues.put("NID", nid[i]);
					contentValues.put("SUBJECT", subject[i]);
					contentValues.put("MESSAGE", message[i]);
					contentValues.put("PCODE", pcode[i]);
					contentValues.put("CAT", cat[i]);
					sqLiteDatabase.insert("NEWSFEED", null, contentValues);
					Toast.makeText(getApplicationContext(),
							"Newsfeed Inserted", Toast.LENGTH_LONG).show();
				}
				SharedPreferences sharedPreferences = getApplicationContext()
						.getSharedPreferences("flag", Context.MODE_PRIVATE);
				SharedPreferences.Editor editor = sharedPreferences.edit();
				editor.putInt("newsfeed", newsfeed_flag);
				editor.commit();
			}
		try {
			if(NewsFeedFragment.mPullToRefreshLayout.isRefreshing()){
				NewsFeedFragment.mPullToRefreshLayout.setRefreshComplete();
				NewsFeedFragment newsFeedFragment = new NewsFeedFragment();
				newsFeedFragment.setadapter();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			onDestroy();

		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
		}
		
	}
	
	
	
}
