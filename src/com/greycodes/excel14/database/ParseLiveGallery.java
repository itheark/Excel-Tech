package com.greycodes.excel14.database;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

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
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.IBinder;
import android.widget.Toast;

public class ParseLiveGallery extends Service {
String url,results;
JSONArray jsonarray;
String[] imageurl,author,desc;
int[] gid;
int n,i;
byte[][] imagebyte;

ExcelDataBase excelDataBase;
ImageDownloader imageDownloader;
SharedPreferences sharedPreferences;
	
	

			//return new ParseImage().execute("http://excelapi.net84.net/livegallery.json").get();
		

	
	public class ParseImage extends AsyncTask<String, String, String>  {

		@Override
		protected String doInBackground(String... params)  {
			// TODO Auto-generated method stub
			
			DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
			HttpPost httppost = new HttpPost("http://excelmec.org/Login2014/lg_printjson.php");
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
			 jsonarray = jsonObject.getJSONArray("livegallery");
				
				 imageurl = new String[jsonarray.length()];
				 desc = new String[jsonarray.length()];
				 author = new String[jsonarray.length()];
				 gid = new int[jsonarray.length()];
				 imagebyte = new byte[jsonarray.length()][];
				  n = jsonarray.length();
				
				for(int i=0;i<n;i++){
					gid[i]= jsonarray.getJSONObject(i).getInt("gid");
					imageurl[i]= jsonarray.getJSONObject(i).getString("url");
					desc[i]= jsonarray.getJSONObject(i).getString("desc");
					author[i]= jsonarray.getJSONObject(i).getString("author");
					
					
					
				}
				

			}catch(JSONException e){
				e.printStackTrace();
			}catch(Exception e){
				Toast.makeText(getApplicationContext(),"Internet connection", Toast.LENGTH_LONG).show();
			}
			imageDownloader = new ImageDownloader();
			for(i=0;i<n;i++){
				if(sharedPreferences.getInt("gid", 0)<gid[i]){
					
				imagebyte[i] = imageDownloader.Download(imageurl[i]);
				}
				}
			
			try {
				excelDataBase = new ExcelDataBase(getApplicationContext());
				SQLiteDatabase sqLiteDatabase = excelDataBase.getSQLiteDataBase();
				ContentValues contentValues = new ContentValues();
				//SID  ,PCODE INT NOT NULL, IMAGE ,URL VARCHAR(30)
				
					
				for( i=0;i<n;i++){
					if(sharedPreferences.getInt("gid", 0)<gid[i]){
					contentValues.put("GID", gid[i]);
					contentValues.put("DESC", desc[i]);
					contentValues.put("IMAGE", imagebyte[i]);
					contentValues.put("AUTHOR", author[i]);
					sqLiteDatabase.insert("GALLERY", null, contentValues);
					}
					}
					Editor editor = sharedPreferences.edit();
					editor.putInt("gid", gid[n-1]);
					editor.commit();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
				stopSelf();
				
		}

		
	}


	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		sharedPreferences = getSharedPreferences("Livegallery", Context.MODE_PRIVATE);

	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		new ParseImage().execute("http://excelmec.org/Login2014/lg_printjson.php");
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}