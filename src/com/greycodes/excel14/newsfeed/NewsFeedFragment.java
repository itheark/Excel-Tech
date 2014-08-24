package com.greycodes.excel14.newsfeed;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.support.v4.app.ListFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.greycodes.excel14.ConnectionDetector;
import com.greycodes.excel14.R;
import com.greycodes.excel14.database.ExcelDataBase;










public class NewsFeedFragment extends ListFragment implements OnRefreshListener{

	int count;
	String[] subject,message,columns;
	int[] pcode,cat;
	ConnectionDetector connectionDetector;
	Handler h;
	private	PullToRefreshLayout mPullToRefreshLayout;
	int n,i;
static  String url = "http://www.excelapi.net84.net/newsfeed.json";
NewsFeedArrayAdapter newsFeedArrayAdapter;
	static  String[] description ;
	
		int[] nid;
		String results = null;
		JSONArray jsonarray;
	ExcelDataBase excelDataBase;
	int newsfeed_flag;
	 DownloadManager checkflg,newsfeed;
	 private long checkflagReference;
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		ViewGroup viewGroup = (ViewGroup)  view;
		
		IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
		  getActivity().registerReceiver(CheckFlag, filter);
		  
		  
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
		 
		 columns = new String[]{"SUBJECT","MESSAGE","PCODE"};
		  ExcelDataBase excelDataBase = new ExcelDataBase(getActivity());
	SQLiteDatabase sqLiteDatabase=	  excelDataBase.getSQLiteDataBase();
	Cursor cursor=	sqLiteDatabase.query("NEWSFEED", columns, null, null, null, null, "NID DESC");
	
	cursor.moveToFirst();
	count = cursor.getCount();
	if(count==0){
		subject = new String[]{"SUB1","SUB2","SUB3","SUB4"};
		message = new String[]{"MESSAGE1","MESSAGE2","MESSAGE3","MESSAGE4"};
		pcode = new int[]{1,2,3,2};
		cat = new int[]{1,2,3,2};
	}else{
	subject = new String[count];
	message = new String[count];
	pcode = new int[count];
	cat = new int[count];
	for(i=0;i<count;i++,cursor.moveToNext()){
		subject[i] = cursor.getString(cursor.getColumnIndex("SUBJECT"));
		message[i] = cursor.getString(cursor.getColumnIndex("MESSAGE"));
		pcode[i] = cursor.getInt(cursor.getColumnIndex("PCODE"));
		cat[i] = cursor.getInt(cursor.getColumnIndex("CAT"));

	}
	} 

	
		 
		 
		 
		
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		
		 newsFeedArrayAdapter = new NewsFeedArrayAdapter(getActivity(), subject, message, pcode,cat);
		
			
			 setListAdapter(newsFeedArrayAdapter);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.homend_newsfeed, container, false);
		
		 
		 
		 
		 
		 return rootView;
	}

	@Override
	public void onRefreshStarted(View view) { 
		// TODO Auto-generated method stub
	/*
		 h = new Handler() {
	            @Override
	            public void handleMessage(Message msg) {

	                if (msg.what != 1) { // code if not connected
	                
	                	
	               Toast.makeText(getActivity(), "No Connection", Toast.LENGTH_LONG).show();
	                	
	                	mPullToRefreshLayout.setRefreshComplete();
	            				
	                } else { // code if connected
	                
		           
	                	   Toast.makeText(getActivity(), "Connection", Toast.LENGTH_LONG).show();
	              checkflg = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
	              Uri uri  = Uri.parse("http://www.excelapi.net84.net/newsfeed.json");
	              DownloadManager.Request request = new DownloadManager.Request(uri); 	 
	              request.setTitle("Excel");
	              request.setDescription("Data for excel");
	              request.setDestinationInExternalFilesDir(getActivity(),Environment.DIRECTORY_DOWNLOADS,"abcd.json");
	              if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.HONEYCOMB_MR2)
	              {
	           	   request.setNotificationVisibility(Request.VISIBILITY_HIDDEN);
	              }
	              checkflagReference = checkflg.enqueue(request);
	                	  // 	try {
					//		Object object = new flagCheck().execute("http://excelapi.net84.net/flag.json").get();
					//	} catch (InterruptedException e) {
					//		// TODO Auto-generated catch block
						//	e.printStackTrace();
						//} catch (ExecutionException e) {
					//		// TODO Auto-generated catch block
					//		e.printStackTrace();
					//	}
		                	
		           
	                	
	                
	               	 
	                }   
	            }
	        };
	        
	            
	        ConnectionDetector.isNetworkAvailable(h,2000);
	*/
		checkflg = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri  = Uri.parse("http://www.excelapi.net84.net/newsfeed.json");
        DownloadManager.Request request = new DownloadManager.Request(uri); 	 
        request.setTitle("Excel");
        request.setDescription("Data for excel");
        request.setDestinationInExternalFilesDir(getActivity(),Environment.DIRECTORY_DOWNLOADS,"CountryList.json");
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.HONEYCOMB_MR2)
        {
     	   request.setNotificationVisibility(Request.VISIBILITY_HIDDEN);
        }
        checkflagReference = checkflg.enqueue(request);
		
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
			excelDataBase = new ExcelDataBase(getActivity());
		SQLiteDatabase sqLiteDatabase = excelDataBase.getSQLiteDataBase();
		ContentValues contentValues = new ContentValues();
		for(i=0;i<n;i++){
			contentValues.put("NID", nid[i]);
			contentValues.put("SUBJECT", subject[i]);
			contentValues.put("MESSAGE", message[i]);
			contentValues.put("PCODE", pcode[i]);
			sqLiteDatabase.insert("NEWSFEED", null, contentValues);
			Toast.makeText(getActivity(), "Newsfeed Inserted", Toast.LENGTH_LONG).show();
		}
		
		
    	Toast.makeText(getActivity(), "news feed postexecute", Toast.LENGTH_LONG).show(); 
    	 newsFeedArrayAdapter = new NewsFeedArrayAdapter(getActivity(), subject, message, pcode,cat);
		
    	 SharedPreferences   sharedPreferences = getActivity().getSharedPreferences("flag", Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = sharedPreferences.edit();
			editor.putInt("newsfeed", newsfeed_flag);
			editor.commit();
		 setListAdapter(newsFeedArrayAdapter);
    	mPullToRefreshLayout.setRefreshComplete();

		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
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
		  			newsfeed_flag = jsonObject.getInt("newsfeed");
		  			
		  			SharedPreferences   sharedPreferences = getActivity().getSharedPreferences("flag", Context.MODE_PRIVATE);
		  			if(newsfeed_flag==sharedPreferences.getInt("newsfeed", 1)){
		  				Toast.makeText(getActivity(), "No updates", Toast.LENGTH_LONG).show();
		  				mPullToRefreshLayout.setRefreshComplete();
		  			
		  			}else{
		  				Object   nfeed = new PNewsFeed().execute(url).get();
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
	  BroadcastReceiver CheckFlag = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			long referenceId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
			   if(checkflagReference == referenceId){
			     
			   
			     
			    int ch;
			    ParcelFileDescriptor file;
			    StringBuffer strContent = new StringBuffer("");
			    StringBuffer countryData = new StringBuffer("");
			     
			    //parse the JSON data and display on the screen
			    try {
			    	
			     file = checkflg.openDownloadedFile(checkflagReference);
			     FileInputStream fileInputStream
			     = new ParcelFileDescriptor.AutoCloseInputStream(file);
			 
			     while( (ch = fileInputStream.read()) != -1)
			      strContent.append((char)ch);
			      
			     try {
			  			JSONObject jsonObject = new JSONObject(strContent.toString());
			  			newsfeed_flag = jsonObject.getInt("newsfeed");
			  			
			  			SharedPreferences   sharedPreferences = getActivity().getSharedPreferences("flag", Context.MODE_PRIVATE);
			  			if(newsfeed_flag==sharedPreferences.getInt("newsfeed", 1)){
			  				Toast.makeText(getActivity(), "No updates", Toast.LENGTH_LONG).show();
			  				mPullToRefreshLayout.setRefreshComplete();
			  			
			  			}else{
			  				Toast.makeText(getActivity(), "No updates", Toast.LENGTH_LONG).show();
			  			}
			  		} catch (JSONException e) {
			  			// TODO Auto-generated catch block
			  			e.printStackTrace();
			  		} 
			      
			     
			     
			    	
			   	//  new File(Environment.DIRECTORY_DOWNLOADS, "CountryList.json").list();

		//	int i=	checkflg.remove(checkflagReference);
			     Toast toast = Toast.makeText(getActivity(),
			       "Downloading of data just finished "+newsfeed_flag, Toast.LENGTH_LONG);
			     toast.setGravity(Gravity.TOP, 25, 400);
			     toast.show();
			      
			    } catch (FileNotFoundException e) {
			     e.printStackTrace();
			    } catch (IOException e) {
			     e.printStackTrace();
			    }
			 
			   }
		}
	};
	  
	
	
	
	
}
/*
package com.greycodes.excel14.newsfeed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.greycodes.excel14.ConnectionDetector;
import com.greycodes.excel14.R;

public class NewsFeedFragment extends Fragment {
	
	static  String url = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20rss%20where%20url%3D'http%3A%2F%2Fwww.aljazeera.com%2FServices%2FRss%2F%3FPostingId%3D2007731105943979989'&format=json&callback=";
	
	static  String[] description ;
	ProgressDialog progDialog;
	 View rootView ;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		  rootView = inflater.inflate(R.layout.homend_newsfeed, container, false);
			
		 ConnectionDetector cd = new ConnectionDetector(getActivity().getApplicationContext());
		 if(isNet()){
				new MyAsyncTask().execute(url); 

			}else
			{
				cd.noNetworkAlert(getActivity());
			}
	
	

		 return rootView;
	}
	private class MyAsyncTask extends AsyncTask<String, String, String>{

		@Override
		protected String doInBackground(String... params)  {
			// TODO Auto-generated method stub
			
			DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
			HttpPost httppost = new HttpPost(url);
			httppost.setHeader("Content-type","application/json");
			InputStream inputstream = null;
			String reults = null;
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
				reults = theStringBuilder.toString();
				
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
					jsonObject = new JSONObject(reults);
					JSONObject queryJSONObject = jsonObject.getJSONObject("query");
					JSONObject resultsJSONObject = queryJSONObject.getJSONObject("results");
					JSONArray items = resultsJSONObject.getJSONArray("item");
					
					description = new String[items.length()];
				
					for(int i=0;i<items.length();i++)
					{
						JSONObject item = items.getJSONObject(i);
						description[i] = item.getString("description");
						
					}

				}catch(JSONException e){
					e.printStackTrace();
				}
			}
			
			return reults;
			
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			progDialog.dismiss();
		try{
			
			ListView newsFeedListView = (ListView) rootView.findViewById(R.id.newsfeed_listview);

			ArrayAdapter<String> listAdapter =new ArrayAdapter<String>(getActivity(),R.layout.newsfeed_listview_items,R.id.newsfeed_description,description);
			newsFeedListView.setAdapter(listAdapter);
			Toast.makeText(getActivity(), description[3], Toast.LENGTH_SHORT).show();
		}catch(Exception e){
			
		}
			
			

		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			 progDialog = ProgressDialog.show(getActivity(), "Excel", "Feeds are being loaded from aljazeera.com");
		}
		
	}
	
	public boolean isNet()
	{
	 boolean status=false;
	 String line;
	 try
	 {
	     URL url = new URL("http://www.google.com");
	     BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
	     while(( line = reader.readLine()) != null) 
	     {
	     }
	     status=true;
	}
	catch (IOException ex)
	{
	    if(ex.toString().equals("java.net.UnknownHostException: www.google.com"))
	        status=false;
	}
	catch(Exception e)
	{

	}
	return status;
	}
	 
	
}
*/