package com.greycodes.excel14;
 

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.greycodes.excel14.database.ExcelDataBase;
import com.greycodes.excel14.database.ImageDownloader;
 
public class ProShowFragment extends Fragment implements OnClickListener{
 ImageView thai,naresh,avial,comin;
 Intent intent;
 int proshow_flag;
 ImageDownloader imageDownloader;
 String results,url,video;
 byte[] imagebyte;
 Handler h;
 SharedPreferences   sharedPreferences;
    //public static final String URL = "http://www.excelmec.org/excel2013/images/proshow.jpg";
   // ImageView imageView;
	//ProgressDialog progDialog;

    /** Called when the activity is first created. */
  
 
    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
    	 View rootView = inflater.inflate(R.layout.homend_proshow_fragment, container, false);
		   sharedPreferences = getActivity().getSharedPreferences("flag", Context.MODE_PRIVATE);

		   
		   if(sharedPreferences.getBoolean("proshow", false)){
			   
			   String[] columns = new String[]{"IMAGE","VIDEO"};
			   comin = (ImageView) rootView.findViewById(R.id.commin);
			   ExcelDataBase excelDataBase = new ExcelDataBase(getActivity());
				SQLiteDatabase sqLiteDatabase=	  excelDataBase.getSQLiteDataBase();
				Cursor cursor=	sqLiteDatabase.query("PROSHOW", columns, null, null, null, null, null);
				cursor.moveToFirst();
			imagebyte=	cursor.getBlob(cursor.getColumnIndex("IMAGE"));
			video=	cursor.getString(cursor.getColumnIndex("VIDEO"));
				
				Bitmap bitmap = BitmapFactory.decodeByteArray(imagebyte, 0,
						imagebyte.length);
						comin.setImageBitmap(bitmap);
						
						comin.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View view) {
								// TODO Auto-generated method stub
								watchYoutubeVideo(video);
							}
						});
			   
		   }else{
			 ConnectionDetector connectionDetector = new ConnectionDetector(getActivity());
			 h = new Handler() {
		            @Override
		            public void handleMessage(Message msg) {

		                if (msg.what != 1) { // code if not connected
		                
		               // Toast.makeText(getActivity(), "No Connection", Toast.LENGTH_LONG).show();
		              
		                	
		                	
		            				
		                } else { // code if connected
		                		       Toast.makeText(getActivity(), "connection", Toast.LENGTH_SHORT).show();                
		                        
		                new  flagCheck().execute("http://excelapi.net84.net/flag.json");
		               	 
		                }   
		            }
		        };
		        
		            
		        connectionDetector.isNetworkAvailable(h,2000);
		   }
		   
    	 thai = (ImageView) rootView.findViewById(R.id.thai);
    	 naresh = (ImageView) rootView.findViewById(R.id.naresh);
    	 avial = (ImageView) rootView.findViewById(R.id.avial);
    	 
    	 thai.setOnClickListener(this);
    	 naresh.setOnClickListener(this);
    	 avial.setOnClickListener(this);
    	 /*imageView = (ImageView) rootView.findViewById(R.id.proshow_imageview);
    	 
         // Create an object for subclass of AsyncTask
         GetXMLTask task = new GetXMLTask();
         // Execute the task
         task.execute(new String[] { URL });*/
    return rootView;
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.thai:
			watchYoutubeVideo("nZsMJgWXb6M");
			break;
case R.id.naresh:
	watchYoutubeVideo("WXculvT0fW8");
			break;
case R.id.avial:
	watchYoutubeVideo("ODhlx4XkJcM");
	
	break;

		
		}
		
	}

	public  void watchYoutubeVideo(String id){
	    try{
	         intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
	         startActivity(intent);                 
	         }catch (ActivityNotFoundException ex){
	             Intent intent=new Intent(Intent.ACTION_VIEW, 
	             Uri.parse("http://www.youtube.com/watch?v="+id));
	             startActivity(intent);
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
		  			proshow_flag = jsonObject.getInt("proshow");
		  			
		  			//SharedPreferences   sharedPreferences = getActivity().getSharedPreferences("flag", Context.MODE_PRIVATE);
		  			if(proshow_flag==1){
		  				//Toast.makeText(getActivity(), "No updates", Toast.LENGTH_LONG).show();
		  				
		  			
		  			}else{
		  				Object   nfeed = new ParseProshow().execute("http://excelapi.net84.net/proshow.json").get();
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
	
	
	
	public class ParseProshow extends AsyncTask<String, String, String>  {

		@Override
		protected String doInBackground(String... params)  {
			// TODO Auto-generated method stub
			
			DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
			HttpPost httppost = new HttpPost("http://excelapi.net84.net/proshow.json");
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
					
				url=	jsonObject.getJSONObject("proshow").getString("url");
				video=	jsonObject.getJSONObject("proshow").getString("channel");
						
				 

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
			
				imagebyte = imageDownloader.Download(url);
			
			
		ExcelDataBase	excelDataBase = new ExcelDataBase(getActivity());
			SQLiteDatabase sqLiteDatabase = excelDataBase.getSQLiteDataBase();
			ContentValues contentValues = new ContentValues();
			//SID  ,PCODE INT NOT NULL, IMAGE ,URL VARCHAR(30)
			 ;
				
				
					contentValues.put("IMAGE",imagebyte );
					contentValues.put("VIDEO",video);
					
					sqLiteDatabase.insert("PROSHOW", null, contentValues);
					
					String[] columns = new String[]{"IMAGE","VIDEO"};
					Cursor cursor=	sqLiteDatabase.query("PROSHOW", columns, null, null, null, null, null);
					if(cursor.getCount()>0){
						
					
					
					Editor editor = sharedPreferences.edit();
					editor.putBoolean("proshow", true);
					editor.commit();
				Toast.makeText(getActivity(), "Proshow Deal", Toast.LENGTH_LONG).show();
					}
				}
				
				
		}

		
	}

