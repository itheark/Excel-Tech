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

import android.app.ProgressDialog;
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

public class ParseSponsor extends Service{

JSONArray jsonarray;
String[] imageurl,companyurl;
int[] sid,pcode;
int n;
byte[][] imagebyte;
int sponsor_flag;
ExcelDataBase excelDataBase;
ImageDownloader imageDownloader;

	

	
	public class ParseSponsorImage extends AsyncTask<String, String, String>  {
String results=null;
		@Override
		protected String doInBackground(String... params)  {
			// TODO Auto-generated method stub
			
			DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
			HttpPost httppost = new HttpPost("http://excelmec.org/Login2014/sponsor.php");
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
				 jsonarray = jsonObject.getJSONArray("sponsor");
					
					 imageurl = new String[jsonarray.length()];
				 companyurl = new String[jsonarray.length()];
					 sid = new int[jsonarray.length()];
					 pcode = new int[jsonarray.length()];
					 imagebyte = new byte[jsonarray.length()][];
					  n = jsonarray.length();
					
					for(int i=0;i<n;i++){
						imageurl[i]= jsonarray.getJSONObject(i).getString("image");
						companyurl[i]= jsonarray.getJSONObject(i).getString("url");
						pcode[i]= jsonarray.getJSONObject(i).getInt("pcode");
						sid[i]= jsonarray.getJSONObject(i).getInt("sid");
						
						
						
						
					}
					

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
		
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			imageDownloader = new ImageDownloader();
			for(int i=0;i<n;i++){
				imagebyte[i] = imageDownloader.Download(imageurl[i]);
			}
			
			excelDataBase = new ExcelDataBase(getApplicationContext());
			SQLiteDatabase sqLiteDatabase = excelDataBase.getSQLiteDataBase();
			ContentValues contentValues = new ContentValues();
			//SID  ,PCODE INT NOT NULL, IMAGE ,URL VARCHAR(30)
			
				
				for(int i=0;i<n;i++){
					contentValues.put("SID", sid[i]);
					contentValues.put("PCODE", pcode[i]);
					contentValues.put("IMAGE", imagebyte[i]);
					contentValues.put("URL", companyurl[i]);
					sqLiteDatabase.insert("SPONSOR", null, contentValues);
				}
				
				SharedPreferences   sharedPreferences = getSharedPreferences("flag", Context.MODE_PRIVATE);
				Editor editor = sharedPreferences.edit();
				editor.putInt("sponsor", sponsor_flag);
				editor.commit();
				
				stopSelf();
		}

		
	}


	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}


	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}


	@Override
	public void onStart(Intent intent, int startId) {
	//	new flagCheck().execute("http://excelapi.net84.net/flag.json");
		stopSelf();
			}

	
	
	
	private class flagCheck extends AsyncTask<String, String, String>{
		  String results=null;
		 
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
		  			sponsor_flag = jsonObject.getInt("sponsor");
		  			
		  			SharedPreferences   sharedPreferences = getSharedPreferences("flag", Context.MODE_PRIVATE);
		  			if(sponsor_flag==sharedPreferences.getInt("sponsor", 1)){
		  				Toast.makeText(getApplicationContext(), "No updates", Toast.LENGTH_LONG).show();
		  			stopSelf();
		  			}else{
		  				
		  				new ParseSponsorImage().execute("http://excelmec.org/Login2014/sponsor.php");		  			
		  			}
		  		} catch (JSONException e) {
		  			// TODO Auto-generated catch block
		  			e.printStackTrace();
		  		}catch (Exception e) {
					// TODO: handle exception
				}
		  		
		  		
		  		
		  	}
		  	  
		    }
}
