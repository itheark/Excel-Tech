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

 String results,url,video;
 byte[] imagebyte;

    //public static final String URL = "http://www.excelmec.org/excel2013/images/proshow.jpg";
   // ImageView imageView;
	//ProgressDialog progDialog;

    /** Called when the activity is first created. */
  
 
    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
    	 View rootView = inflater.inflate(R.layout.homend_proshow_fragment, container, false);
 
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
	


		
	}

