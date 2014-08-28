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
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.greycodes.excel14.InfoNDActivity;
import com.greycodes.excel14.R;
import com.greycodes.excel14.info.ScheduleViewPager;


public class ParseSchedule extends Service {
String url,results;
JSONArray jsonarray;
String[] ename,stime,duration,venue,time;
int[] eid,sid,level,day,cat;
int count,i;
int schedule_flag;

ExcelDataBase excelDataBase;

	
	


	
	public class parseschedule extends AsyncTask<String, String, String>  {

		@Override
		protected String doInBackground(String... params)  {
			// TODO Auto-generated method stub
			
			DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
			HttpPost httppost = new HttpPost("http://excelmec.org/Login2014/schedule.php");
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
			JSONObject jsonObject;
			try{
				jsonObject = new JSONObject(results);
				jsonarray = jsonObject.getJSONArray("schedule");
				count = jsonarray.length();
				ename = new String[count];
				cat = new int[count];
				stime = new String[count];
				duration = new String[count];
				venue = new String[count];
				time = new String[count];
				
				eid = new int[count];
				sid = new int[count];
				level = new int[count];
				day = new int[count];
				
			 for(i=0;i<count;i++){
				 
				 sid[i] = jsonarray.getJSONObject(i).getInt("sid");
				 eid[i] = jsonarray.getJSONObject(i).getInt("eid");
				 ename[i] = jsonarray.getJSONObject(i).getString("name");
				 level[i] = jsonarray.getJSONObject(i).getInt("level");
				 day[i] = jsonarray.getJSONObject(i).getInt("day");
				 cat[i] = jsonarray.getJSONObject(i).getInt("cat");
				 venue[i] = jsonarray.getJSONObject(i).getString("venue");
				 stime[i] = jsonarray.getJSONObject(i).getString("stime");
				 duration[i] = jsonarray.getJSONObject(i).getString("duration");
				 time[i] = jsonarray.getJSONObject(i).getString("time");
			 }
				
				 
				

			}catch(JSONException e){
				Toast.makeText(getApplicationContext(), "No internet Connectivity", Toast.LENGTH_LONG).show();
			}
			super.onPostExecute(result);
			excelDataBase = new ExcelDataBase(getApplicationContext());
			SQLiteDatabase sqLiteDatabase = excelDataBase.getSQLiteDataBase();
			ContentValues contentValues = new ContentValues();
			try {
				for(i=0;i<count;i++){
					contentValues.put("SID", sid[i]);
					contentValues.put("EID", eid[i]);
					contentValues.put("ENAME", ename[i]);
					contentValues.put("LEVEL", level[i]);
					contentValues.put("DAY", day[i]);
					contentValues.put("CAT", cat[i]);
					contentValues.put("VENUE", venue[i]);
					contentValues.put("STIME", stime[i]);
					contentValues.put("DURATION", duration[i]);
					contentValues.put("TIME", time[i]);
					sqLiteDatabase.insert("SCHEDULE", null, contentValues);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		String[]	columns = new String[]{"SID"};
		 
		Cursor cursor=	sqLiteDatabase.query("SCHEDULE", columns, null, null, null, null, null);
		
		cursor.moveToFirst();
		 		
			if(cursor.getCount()>0){
				SharedPreferences   sharedPreferences = getSharedPreferences("flag", Context.MODE_PRIVATE);
				Editor editor = sharedPreferences.edit();
				editor.putInt("schedule",2);
				editor.commit();
				Toast.makeText(getApplicationContext(), "Schedule updated", Toast.LENGTH_LONG).show();
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
		  		try {
		  			JSONObject jsonObject = new JSONObject(results);
		  			schedule_flag = jsonObject.getInt("schedule");
		  			
		  			//SharedPreferences   sharedPreferences = getActivity().getSharedPreferences("flag", Context.MODE_PRIVATE);
		  			if(schedule_flag==1){
		  				Toast.makeText(getApplicationContext(), "Schedule not released", Toast.LENGTH_LONG).show();
		  				stopSelf();
		  			
		  			}else{
		  			new parseschedule().execute("http://excelmec.org/Login2014/schedule.php");
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


	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}


	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		new flagCheck().execute("http://excelapi.net84.net/flag.json");
	}


	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}