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

import com.greycodes.excel14.HomeNDActivity;
import com.greycodes.excel14.QuickOpenAdapter;
import com.greycodes.excel14.QuickOpenFragment;
import com.greycodes.excel14.R;
import com.greycodes.excel14.login.AccountFragment;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;


public class ParseQuickOpen extends Service{
String url,results;
JSONArray jsonarray;
String[] ename,stime,duration,venue;
int[] eid,hotness,level,cat;
int count,i;


	
	

	
	public class parsequickopen extends AsyncTask<String, String, String>  {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			Toast.makeText(getApplicationContext(), "preexecute", Toast.LENGTH_LONG).show();

		}

		@Override
		protected String doInBackground(String... params)  {
			// TODO Auto-generated method stub
			
			DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
			HttpPost httppost = new HttpPost("http://excelapi.net84.net/quickopen.json");
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
				jsonarray = jsonObject.getJSONArray("quickopen");
				count = jsonarray.length();
				ename = new String[count];
				cat = new int[count];
				stime = new String[count];
				duration = new String[count];
				venue = new String[count];
				eid = new int[count];
				hotness = new int[count];
				level = new int[count];
				
			 for(i=0;i<count;i++){
				 eid[i] = jsonarray.getJSONObject(i).getInt("eid");
				 ename[i] = jsonarray.getJSONObject(i).getString("ename");
				 cat[i] = jsonarray.getJSONObject(i).getInt("cat");
				 hotness[i] = jsonarray.getJSONObject(i).getInt("hotness");
				 level[i] = jsonarray.getJSONObject(i).getInt("level");
				 stime[i] = jsonarray.getJSONObject(i).getString("starttime");
				 duration[i] = jsonarray.getJSONObject(i).getString("durations");
				 venue[i]= jsonarray.getJSONObject(i).getString("venue");
			 }
				
				 
				

			}catch(JSONException e){
				e.printStackTrace();
			}catch(Exception e){
				Toast.makeText(getApplicationContext(), "No Internet Connectivity", Toast.LENGTH_LONG).show();
			stopSelf();
			}
			
			try {
				if (results.length()>10) {

					QuickOpenAdapter adapter = new QuickOpenAdapter(
							getApplicationContext(), ename, level, cat, venue,
							stime, duration, hotness);
					HomeNDActivity.adapter = adapter;

					Fragment f;
					//FragmentManager fragmentManager;
					FragmentTransaction transaction;
					//fragmentManager = HomeNDActivity.
					//fragmentManager =  getApplicationContext().getSupportFragmentManager();
					Toast.makeText(getApplicationContext(), "FragmentManager SET", Toast.LENGTH_LONG).show();

					transaction = HomeNDActivity.fragmentManager.beginTransaction();
					f = new QuickOpenFragment();
					transaction.replace(R.id.home_content_frame, f);
					
					// Add this transaction to the back stack
					transaction.addToBackStack("detail");
					transaction.commit();

				}else{
					Toast.makeText(getApplicationContext(), "No Internet Connectivity", Toast.LENGTH_LONG).show();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		Toast.makeText(getApplicationContext(), "start", Toast.LENGTH_LONG).show();
		 new parsequickopen().execute("http://excelapi.net84.net/quickopen.json");
	}

}