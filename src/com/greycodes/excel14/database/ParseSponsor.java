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
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.Toast;

public class ParseSponsor {
String url,results;
JSONArray jsonarray;
String[] imageurl,companyurl;
int[] sid,pcode;
int n;
byte[][] imagebyte;
Context context;
ExcelDataBase excelDataBase;
ImageDownloader imageDownloader;

	public ParseSponsor(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}
	
	public Object parseSponsorImage(){
		url = "http://excelapi.net84.net/sponsor.json";
		try {
			return new ParseSponsorImage().execute(url).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	public class ParseSponsorImage extends AsyncTask<String, String, String>  {

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
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			imageDownloader = new ImageDownloader();
			for(int i=0;i<n;i++){
				imagebyte[i] = imageDownloader.Download(imageurl[i]);
			}
			
			excelDataBase = new ExcelDataBase(context);
			SQLiteDatabase sqLiteDatabase = excelDataBase.getSQLiteDataBase();
			ContentValues contentValues = new ContentValues();
			//SID  ,PCODE INT NOT NULL, IMAGE ,URL VARCHAR(30)
			
				
				for(int i=0;i<n;i++){
					contentValues.put("SID", sid[i]);
					contentValues.put("PCODE", pcode[i]);
					contentValues.put("IMAGE", imagebyte[i]);
					contentValues.put("URL", companyurl[i]);
					sqLiteDatabase.insert("SPONSOR", null, contentValues);
					Toast.makeText(context, "Sponsor Inserted", Toast.LENGTH_LONG).show();
				}
				
				ParseNewsFeed parseNewsFeed = new ParseNewsFeed(context);
				Object nfeed=    parseNewsFeed.executenewsfeedparse();
		}

		
	}

}
