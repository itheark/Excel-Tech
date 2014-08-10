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


import android.content.Context;

import android.os.AsyncTask;


public class ParseSchedule {
String url,results;
JSONArray jsonarray;
String[] ename,cat,stime,duration,venue;
int[] eid,sid,level,day;
int count,i;

Context context;

	public ParseSchedule(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}
	
	public Object parseSchedule(){
		url = "http://excelapi.net84.net/schedule.json";
		try {
			return new parseschedule().execute("http://excelapi.net84.net/schedule.json").get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	public class parseschedule extends AsyncTask<String, String, String>  {

		@Override
		protected String doInBackground(String... params)  {
			// TODO Auto-generated method stub
			
			DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
			HttpPost httppost = new HttpPost("http://excelapi.net84.net/schedule.json");
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
					jsonarray = jsonObject.getJSONArray("schedule");
					count = jsonarray.length();
					ename = new String[count];
					cat = new String[count];
					stime = new String[count];
					duration = new String[count];
					venue = new String[count];

					eid = new int[count];
					sid = new int[count];
					level = new int[count];
					day = new int[count];
				 for(i=0;i<count;i++){
					 eid[i] = jsonarray.getJSONObject(i).getInt("eid");
					 eid[i] = jsonarray.getJSONObject(i).getInt("sid");
					 ename[i] = jsonarray.getJSONObject(i).getString("name");
					 cat[i] = jsonarray.getJSONObject(i).getString("cat");
					 day[i] = jsonarray.getJSONObject(i).getInt("day");
					 level[i] = jsonarray.getJSONObject(i).getInt("level");
					 venue[i] = jsonarray.getJSONObject(i).getString("venue");
					 stime[i] = jsonarray.getJSONObject(i).getString("starttime");
					 duration[i] = jsonarray.getJSONObject(i).getString("durations");
					 
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
			
				
		}

		
	}

}