package com.greycodes.excel14.newsfeed;

import org.json.JSONArray;

import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockListFragment;
import com.greycodes.excel14.R;
import com.greycodes.excel14.database.ExcelDataBase;










public class NewsFeedFragment extends SherlockListFragment implements OnRefreshListener{

	int count;
	String[] subject,message,columns;
	int[] pcode,cat;
	
	static public	PullToRefreshLayout mPullToRefreshLayout;
	int n,i;
static  String url = "http://www.excelapi.net84.net/newsfeed.json";
NewsFeedArrayAdapter newsFeedArrayAdapter;
	static  String[] description ;
	
		@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		getActivity().stopService(new  Intent(getActivity(), Checkflag.class));
	}



		int[] nid;
		String results = null;
		JSONArray jsonarray;
	ExcelDataBase excelDataBase;
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
		 
		 columns = new String[]{"SUBJECT","MESSAGE","PCODE","CAT"};
		  ExcelDataBase excelDataBase = new ExcelDataBase(getActivity());
	SQLiteDatabase sqLiteDatabase=	  excelDataBase.getSQLiteDataBase();
	Cursor cursor=	sqLiteDatabase.query("NEWSFEED", columns, null, null, null, null, "NID DESC");
	
	cursor.moveToFirst();
	count = cursor.getCount();
	Toast.makeText(getActivity(), ""+count, Toast.LENGTH_LONG).show();
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

	
		 
	newsFeedArrayAdapter = new NewsFeedArrayAdapter(getActivity(), subject, message, pcode,cat);
	
	
	 setListAdapter(newsFeedArrayAdapter); 
		 
		
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		
		 
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
	getActivity().startService(new  Intent(getActivity(), Checkflag.class));
		
	}
	
	
	
	public  void setadapter(){
		newsFeedArrayAdapter = new NewsFeedArrayAdapter(getActivity(), subject, message, pcode,cat);
		
		
		 setListAdapter(newsFeedArrayAdapter);
		 try {
			if(mPullToRefreshLayout.isRefreshing()){
				 mPullToRefreshLayout.setRefreshComplete();
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	  	
	
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