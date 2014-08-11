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

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


public class ParseQuickOpen {
String url,results;
JSONArray jsonarray;
String[] ename,stime,duration,venue;
int[] eid,hotness,level,cat;
int count,i;

Context context;

	public ParseQuickOpen(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}
	
	public Object parseQO(){
		url = "http://excelapi.net84.net/quickopen.json";
		try {
			return new parsequickopen().execute("http://excelapi.net84.net/quickopen.json").get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	public class parsequickopen extends AsyncTask<String, String, String>  {

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
				}
			}
			return results;
			
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			QuickOpenAdapter adapter = new QuickOpenAdapter(context, ename, level, cat, venue, stime, duration, hotness);
			HomeNDActivity.adapter=adapter;
			Fragment f;
			FragmentManager fragmentManager ;
			FragmentTransaction transaction;
			fragmentManager = ((HomeNDActivity) context).getSupportFragmentManager();
			 transaction=fragmentManager.beginTransaction();
			 f = new QuickOpenFragment();
			 transaction.replace(R.id.home_content_frame,f);
				// Add this transaction to the back stack
               transaction.addToBackStack("detail");
               transaction.commit();
				
		}

		
	}

}