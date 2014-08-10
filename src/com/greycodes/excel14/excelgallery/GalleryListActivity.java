package com.greycodes.excel14.excelgallery;

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
import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.greycodes.excel14.ConnectionDetector;
import com.greycodes.excel14.HomeNDActivity;
import com.greycodes.excel14.InfoNDActivity;
import com.greycodes.excel14.R;
import com.greycodes.excel14.database.ExcelDataBase;
import com.greycodes.excel14.database.ImageDownloader;



public class GalleryListActivity extends ListActivity  {
	



	LiveGalleryAdapter adapter;
	String[] desc,author,columns;
	byte[][] bs;
	String[] imageurl;
	Cursor cursor;
	ExcelDataBase excelDataBase;
	private	PullToRefreshLayout mPullToRefreshLayout;
	ImageDownloader imageDownloader;
	String url,results;
	JSONArray jsonarray;
	Handler h;
	int[] gid;
	int n,i;
	byte[][] imagebyte;
	SharedPreferences sharedPreferences;
	Editor editor;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.excellive_activity);
		android.app.ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0e1215")));
        bar.setTitle(Html.fromHtml("<font color=\"#e6f3ea\">" + getString(R.string.app_name) + "</font>"));
      /*  Fragment f;
		FragmentManager fragmentManager ;
		FragmentTransaction transaction;
		fragmentManager = getSupportFragmentManager();
		 transaction=fragmentManager.beginTransaction();
		 f = new LiveGalleryList();
		 transaction.replace(R.id.excellive_content_frame,f);
			// Add this transaction to the back stack
           
           transaction.commit();*/
       
        sharedPreferences = getSharedPreferences("Livegallery", Context.MODE_PRIVATE);        excelDataBase = new ExcelDataBase(this);
    	columns = new String[]{"DESC","IMAGE","AUTHOR"};
    	 
    	SQLiteDatabase sqLiteDatabase=	  excelDataBase.getSQLiteDataBase();
    	 cursor=	sqLiteDatabase.query("GALLERY", columns, null, null, null, null, "GID DESC");
    	 cursor.moveToFirst();
    	 
    author = new String[cursor.getCount()];
    desc = new String[cursor.getCount()];
    bs = new byte[cursor.getCount()][];
    for(int i=0;i<cursor.getCount();i++,cursor.moveToNext()){
    	desc[i]= cursor.getString(cursor.getColumnIndex("AUTHOR"));
    	desc[i]= desc[i]+" : "+ cursor.getString(cursor.getColumnIndex("DESC"));
    	bs[i]=cursor.getBlob(cursor.getColumnIndex("IMAGE"));
    }
    	
    	adapter = new LiveGalleryAdapter(this,desc,bs);
    	setListAdapter(adapter);

		// Subclass of ParseQueryAdapter
		
		
		

		
		// Default view is all meals
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_meal_list, menu);
		return true;
	}

	/*
	 * Posting meals and refreshing the list will be controlled from the Action
	 * Bar.
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.action_refresh: 
			 h = new Handler() {
		            @Override
		            public void handleMessage(Message msg) {

		                if (msg.what != 1) { // code if not connected
		                
		                	
		               Toast.makeText(getApplicationContext(), "No Connection", Toast.LENGTH_LONG).show();
		                	
		                
		            				
		                } else { // code if connected
		                
			           
		                	   Toast.makeText(getApplicationContext(), "Connection", Toast.LENGTH_LONG).show();
		                new ParseImage().execute("http://excelapi.net84.net/livegallery.json");
		                	   
		                	   
		                	   /*	try {
								Object object = new ParseImage().execute("http://excelapi.net84.net/livegallery.json").get();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ExecutionException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			                	
			           */
		                	
		                	
		               	 
		                }   
		            }
		        };
		        
		            
		        ConnectionDetector.isNetworkAvailable(h,2000);
			
	

		break;

		case R.id.action_new: {
			newMeal();
			break;
				}
		case R.id.action_home:
			Intent home = new Intent(GalleryListActivity.this,HomeNDActivity.class);
			home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(home);
		}
		return super.onOptionsItemSelected(item);
	}

	

	

	private void newMeal() {
		Intent i = new Intent(this, NewImageActivity.class);
		startActivityForResult(i, 0);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			// If a new post has been added, update
			// the list of posts
			
		}
	}

	
	
	
	public class ParseImage extends AsyncTask<String, String, String>  {

		@Override
		protected String doInBackground(String... params)  {
			// TODO Auto-generated method stub
			
			DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
			HttpPost httppost = new HttpPost("http://excelapi.net84.net/livegallery.json");
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
				 jsonarray = jsonObject.getJSONArray("livegallery");
					
					 imageurl = new String[jsonarray.length()];
					 desc = new String[jsonarray.length()];
					 author = new String[jsonarray.length()];
					 gid = new int[jsonarray.length()];
					 imagebyte = new byte[jsonarray.length()][];
					  n = jsonarray.length();
					
					for(int i=0;i<n;i++){
						gid[i]= jsonarray.getJSONObject(i).getInt("gid");
						imageurl[i]= jsonarray.getJSONObject(i).getString("url");
						desc[i]= jsonarray.getJSONObject(i).getString("desc");
						author[i]= jsonarray.getJSONObject(i).getString("author");
						
						
						
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
			for(i=0;i<n;i++){
				if(sharedPreferences.getInt("gid", 99)<gid[i]){
					Toast.makeText(getApplicationContext(), "livegallery condition ok", Toast.LENGTH_SHORT).show();
				imagebyte[i] = imageDownloader.Download(imageurl[i]);
				}
				}
			
			excelDataBase = new ExcelDataBase(getApplicationContext());
			SQLiteDatabase sqLiteDatabase = excelDataBase.getSQLiteDataBase();
			ContentValues contentValues = new ContentValues();
			
			
				
			for( i=0;i<n;i++){
				if(sharedPreferences.getInt("gid", 99)<gid[i]){
				contentValues.put("GID", gid[i]);
				contentValues.put("DESC", desc[i]);
				contentValues.put("IMAGE", imagebyte[i]);
				contentValues.put("AUTHOR", author[i]);
				sqLiteDatabase.insert("GALLERY", null, contentValues);
				Toast.makeText(getApplicationContext(), "Sponsor Inserted", Toast.LENGTH_LONG).show();
				}
				}
			SharedPreferences.Editor editor = sharedPreferences.edit();
			int a=gid[n-1];
			editor.putInt("gid", a);
			editor.commit();
				
				 author = new String[cursor.getCount()];
				    desc = new String[cursor.getCount()];
				    bs = new byte[cursor.getCount()][];
				    for(int i=0;i<cursor.getCount();i++,cursor.moveToNext()){
				    	desc[i]= cursor.getString(cursor.getColumnIndex("AUTHOR"));
				    	desc[i]= desc[i]+" : "+ cursor.getString(cursor.getColumnIndex("DESC"));
				    	bs[i]=cursor.getBlob(cursor.getColumnIndex("IMAGE"));
				    }
				    	
				    //	adapter = new LiveGalleryAdapter(getApplicationContext(),desc,bs);
				    //	setListAdapter(adapter);
				
		}

		
	}

}
