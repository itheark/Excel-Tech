package com.greycodes.excel14.info;

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

import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;

import com.greycodes.excel14.ConnectionDetector;
import com.greycodes.excel14.R;
import com.greycodes.excel14.database.ExcelDataBase;
import com.greycodes.excel14.database.ImageDownloader;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class SponsorFragment extends ListFragment implements OnRefreshListener {
	private	PullToRefreshLayout mPullToRefreshLayout;
	SponsorList sponsorList;
	Handler h;
	String url,results;
	JSONArray jsonarray;
	String[] imageurl,companyurl;
	int[] sid,pcode;
	int n;
	byte[][] imagebyte,bs;
	 int sponsor_flag;
	ExcelDataBase excelDataBase;
	ImageDownloader imageDownloader;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.info_sponsor, container, false);
		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		ViewGroup viewGroup = (ViewGroup)  view;
		 mPullToRefreshLayout = new PullToRefreshLayout(viewGroup.getContext());
		 
		 ActionBarPullToRefresh.from(getActivity())
        // We need to insert the PullToRefreshLayout into the Fragment's ViewGroup
        .insertLayoutInto(viewGroup)
        // Here we mark just the ListView and it's Empty View as pullable
        .theseChildrenArePullable(android.R.id.list, android.R.id.empty)
        .listener(this)
        .setup(mPullToRefreshLayout);
		 mPullToRefreshLayout.setRefreshing(true);
		 onRefreshStarted(viewGroup);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		assign();
		sponsorList = new SponsorList(getActivity(),bs);
		setListAdapter(sponsorList);
	}

	@Override
	public void onRefreshStarted(View view) {
		// TODO Auto-generated method stub
		 h = new Handler() {
	            @Override
	            public void handleMessage(Message msg) {

	                if (msg.what != 1) { // code if not connected
	                
	                	
	               Toast.makeText(getActivity(), "No Connection", Toast.LENGTH_LONG).show();
	                	
	                	mPullToRefreshLayout.setRefreshComplete();
	            				
	                } else { // code if connected
	                
		           
	                	   Toast.makeText(getActivity(), "Connection", Toast.LENGTH_LONG).show();
	                	try {
							Object object = new flagCheck().execute("http://excelapi.net84.net/flag.json").get();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ExecutionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		                	
		           
	                	
	                	
	               	 
	                }   
	            }
	        };
	        
	            
	        ConnectionDetector.isNetworkAvailable(h,2000);
	}
	
	
	
	public class ParseSponsorImageInfo extends AsyncTask<String, String, String>  {

		@Override
		protected String doInBackground(String... params)  {
			// TODO Auto-generated method stub
			
			DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
			HttpPost httppost = new HttpPost("http://excelapi.net84.net/sponsor.json");
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
			
			excelDataBase = new ExcelDataBase(getActivity());
			SQLiteDatabase sqLiteDatabase = excelDataBase.getSQLiteDataBase();
			ContentValues contentValues = new ContentValues();
			//SID  ,PCODE INT NOT NULL, IMAGE ,URL VARCHAR(30)
			
				
				for(int i=0;i<n;i++){
					contentValues.put("SID", sid[i]);
					contentValues.put("PCODE", pcode[i]);
					contentValues.put("IMAGE", imagebyte[i]);
					contentValues.put("URL", companyurl[i]);
					sqLiteDatabase.insert("SPONSOR", null, contentValues);
					Toast.makeText(getActivity(), "Sponsor Inserted", Toast.LENGTH_LONG).show();
				}
				SharedPreferences   sharedPreferences = getActivity().getSharedPreferences("flag", Context.MODE_PRIVATE);
				SharedPreferences.Editor editor = sharedPreferences.edit();
				editor.putInt("sponsor", sponsor_flag);
				editor.commit();
				mPullToRefreshLayout.setRefreshComplete();
				assign();
				sponsorList = new SponsorList(getActivity(),bs);
				setListAdapter(sponsorList);
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
		  			sponsor_flag = jsonObject.getInt("sponsor");
		  			
		  			SharedPreferences   sharedPreferences = getActivity().getSharedPreferences("flag", Context.MODE_PRIVATE);
		  			if(sponsor_flag==sharedPreferences.getInt("sponsor", 1)){
		  				Toast.makeText(getActivity(), "No updates", Toast.LENGTH_LONG).show();
		  				mPullToRefreshLayout.setRefreshComplete();
		  			
		  			}else{
		  				
		  				Object   nfeed = new ParseSponsorImageInfo().execute("http://excelapi.net84.net/sponsor.json").get();
		  			
		  			}
		  		} catch (JSONException e) {
		  			// TODO Auto-generated catch block
		  			e.printStackTrace();
		  		} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		  		
		  		
		  		
		  	}
		  	  
		    }


	public void assign(){
		ExcelDataBase excelDataBase= new ExcelDataBase(getActivity());
		SQLiteDatabase sqLiteDatabase=	  excelDataBase.getSQLiteDataBase();
		String[] columns = new String[]{"PCODE","IMAGE","URL"};
		Cursor cursor=	sqLiteDatabase.query("SPONSOR", columns, null, null, null, null, "PCODE DESC");
		cursor.moveToFirst();
		bs = new byte[cursor.getCount()][];
		for(int i=0;i<cursor.getCount();i++,cursor.moveToNext()){
			
			bs[i]=cursor.getBlob(cursor.getColumnIndex("IMAGE"));
		}
	}


}
