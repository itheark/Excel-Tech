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

import com.greycodes.excel14.ConnectionDetector;
import com.greycodes.excel14.Misc;

import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

public class ParseResult extends Service {
String results,url;
String[] res,name,college;
int eid;
Context context;
boolean flag;
String resfinal;
Misc misc;
	
	public String getFinalResult(){
		return resfinal;
	}
	
	

	public class Result extends AsyncTask<String, String, String>  {

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
				JSONObject event = jsonObject.getJSONObject("event");
				JSONObject eventid = event.getJSONObject(Integer.toString(eid));
				
				if(eventid.getInt("flag")==1){
					JSONArray winners = eventid.getJSONArray("winners");
					int len = winners.length();
					name = new String[len];
					college = new  String[len];
					
					
					for(int i=0;i<len;i++){
					name[i]=	winners.getJSONObject(i).getString("teamname");
						college[i] =winners.getJSONObject(i).getString("college");
					}
					for(int i=0;i<len;i++){
						resfinal = resfinal + "   " + Integer.toString(i+1)+ ". " +name[i]+"\n"+college[i]+"\n";
					}
					
				//	for(int i=0;i<winners.getJSONObject(0).getJSONArray("name").length();i++)
					
				}else
					if(eventid.getInt("flag")==2){
					JSONArray shortlist = eventid.getJSONArray("shortlisted");
					int len = shortlist.length();
					name = new String[len];
					college = new  String[len];
					
					
					for(int i=0;i<len;i++){
					name[i]=	shortlist.getJSONObject(i).getString("teamname");
						college[i] =shortlist.getJSONObject(i).getString("college");
					}
					for(int i=0;i<len;i++){
						resfinal = resfinal + "   " + Integer.toString(i+1)+ ". " +name[i]+"\n"+college[i]+"\n";
					}
					
				}else{
					resfinal = "We are Also Waiting";
				}
				
				
				
				
				try {
					misc.settext(eid, resfinal);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					//stopSelf();
				}
				

			}catch(Exception e){
				Toast.makeText(getApplicationContext(), "No network"+e, Toast.LENGTH_LONG).show();;
			//	stopSelf();
			}
		
			stopSelf();
		}

		
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		 eid =intent.getIntExtra("eid", 0);
		 url = "http://excelmec.org/Login2014/view_result.php?eid="+eid;
misc = new Misc(getApplicationContext());
		 new Result().execute(url);
	}

	
}
