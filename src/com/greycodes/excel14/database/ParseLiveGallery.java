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

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.Toast;

public class ParseLiveGallery {
String url,results;
JSONArray jsonarray;
String[] imageurl,author,desc;
int[] gid;
int n,i;
byte[][] imagebyte;
Context context;
ExcelDataBase excelDataBase;
ImageDownloader imageDownloader;
SharedPreferences sharedPreferences;
	public ParseLiveGallery(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		sharedPreferences = context.getSharedPreferences("Livegallery", Context.MODE_PRIVATE);
	}
	
	public Object parseImage(){
		url = "http://excelapi.net84.net/sponsor.json";
		try {
			Toast.makeText(context, "live gallery parse", Toast.LENGTH_SHORT).show();
			return new ParseImage().execute("http://excelapi.net84.net/livegallery.json").get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	public class ParseImage extends AsyncTask<String, String, String>  {

		@Override
		protected String doInBackground(String... params)  {
			// TODO Auto-generated method stub
			
			DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
			HttpPost httppost = new HttpPost("http://excelapi.net84.net/livegallery.json");
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
				}
			}
			return results;
			
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			imageDownloader = new ImageDownloader();
			for(i=0;i<n;i++){
				if(sharedPreferences.getInt("gid", 99)<gid[i]){
					Toast.makeText(context, "livegallery condition ok", Toast.LENGTH_SHORT).show();
				imagebyte[i] = imageDownloader.Download(imageurl[i]);
				}
				}
			
			excelDataBase = new ExcelDataBase(context);
			SQLiteDatabase sqLiteDatabase = excelDataBase.getSQLiteDataBase();
			ContentValues contentValues = new ContentValues();
			//SID  ,PCODE INT NOT NULL, IMAGE ,URL VARCHAR(30)
			
				
			for( i=0;i<n;i++){
				if(sharedPreferences.getInt("gid", 99)<gid[i]){
				contentValues.put("GID", gid[i]);
				contentValues.put("DESC", desc[i]);
				contentValues.put("IMAGE", imagebyte[i]);
				contentValues.put("AUTHOR", author[i]);
				sqLiteDatabase.insert("GALLERY", null, contentValues);
				Toast.makeText(context, "Sponsor Inserted", Toast.LENGTH_LONG).show();
				}
				}
				Editor editor = sharedPreferences.edit();
				editor.putInt("gid", gid[n-1]);
				editor.commit();
				
				ParseNewsFeed parseNewsFeed = new ParseNewsFeed(context);
				Object nfeed=    parseNewsFeed.executenewsfeedparse();
				
		}

		
	}

}