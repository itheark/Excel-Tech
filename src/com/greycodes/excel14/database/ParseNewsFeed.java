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

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.Toast;

import com.greycodes.excel14.ConnectionDetector;
import com.greycodes.excel14.HomeNDActivity;
import com.greycodes.excel14.R;



public class ParseNewsFeed {

static  String url = "http://www.excelapi.net84.net/newsfeed.json";
	
	static  String[] description ;
	
	 String[] subject;
		String[] message;
		int[] pcode,nid;
		String results = null;
		JSONArray jsonarray;
	ExcelDataBase excelDataBase;
	Context context;
	int n,i;
		public ParseNewsFeed(Context context) {
		
		// TODO Auto-generated constructor stub
		this.context = context;
	}
	
	public Object executenewsfeedparse() {
	Object nfeed = null;
	try {
		nfeed = new PNewsFeed().execute(url).get();
		return nfeed;
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return nfeed;
	}
	
	private class PNewsFeed extends AsyncTask<String, String, String>{

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
				 jsonarray = jsonObject.getJSONArray("News");
					
					 subject = new String[jsonarray.length()];
				 message = new String[jsonarray.length()];
					 pcode = new int[jsonarray.length()];
					 nid = new int[jsonarray.length()];
					 n = jsonarray.length();
					
					for(int i=0;i<n;i++){
						subject[i]= jsonarray.getJSONObject(i).getString("subject");
						message[i]= jsonarray.getJSONObject(i).getString("message");
						pcode[i]= jsonarray.getJSONObject(i).getInt("pcode");
						nid[i]= jsonarray.getJSONObject(i).getInt("nid");
						
						
						
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
			excelDataBase = new ExcelDataBase(context);
		SQLiteDatabase sqLiteDatabase = excelDataBase.getSQLiteDataBase();
		ContentValues contentValues = new ContentValues();
		for(i=0;i<n;i++){
			contentValues.put("NID", nid[i]);
			contentValues.put("SUBJECT", subject[i]);
			contentValues.put("MESSAGE", message[i]);
			contentValues.put("PCODE", pcode[i]);
			sqLiteDatabase.insert("NEWSFEED", null, contentValues);
			Toast.makeText(context, "Newsfeed Inserted", Toast.LENGTH_LONG).show();
		}
		
		
		ExcelDataBase ebs = new ExcelDataBase(context);
    	SQLiteDatabase db = ebs.getSQLiteDataBase();
  //  String[]	column = new String[]{"EID"};
  //  Cursor cursor = db.query("COMPETITION", column, null, null, null, null, null);
//int insertedcompetition=   cursor.getCount();
String[] column = new String[]{"NID"};
Cursor cursor = db.query("NEWSFEED", column, null, null, null, null, null);
int insertednewsfeed = cursor.getCount();
column = new String[]{"SID"};
 cursor = db.query("SPONSOR", column, null, null, null, null, null);

int insertedsponsor = cursor.getCount();
    	Toast.makeText(context, "news feed postexecute", Toast.LENGTH_LONG).show(); 
    
   // if(insertedcompetition>0&&insertednewsfeed>0&&insertedsponsor>0){
        if(insertednewsfeed>0&&insertedsponsor>0){

		
    	SharedPreferences sharedPreferences = context.getSharedPreferences("app_config", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putBoolean("DB_Created", true);
			editor.commit();
			Intent intent = new Intent(context, HomeNDActivity.class);
			context.startActivity(intent);
			((Activity)context).finish();
			//firstRunactivity.dbcreated();
	
    }else{
    	
    		
    	
    }

		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
		}
		
	}
}
