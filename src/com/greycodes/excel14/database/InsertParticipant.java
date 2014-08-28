package com.greycodes.excel14.database;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Service;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.IBinder;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

import com.greycodes.excel14.CompetitionNDActivity;
import com.greycodes.excel14.R;
import com.parse.PushService;

public class InsertParticipant extends Service {

String url,results,Ename;
int flag,eid,tid,uid;
boolean team,success=false;


	
	
	
	
	public void insert(){
		ExcelDataBase excelDataBase = new ExcelDataBase(CompetitionNDActivity.context);
		SQLiteDatabase sqLiteDatabase = excelDataBase.getSQLiteDataBase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("EID", eid);
		contentValues.put("ENAME", Ename);
		contentValues.put("TID", tid);
		if((sqLiteDatabase.insert("PARTICIPATE", null, contentValues))>0){
			if(team){
				Toast.makeText(CompetitionNDActivity.context, "Team id  is "+tid+" Use this id to add members to your team :)", Toast.LENGTH_LONG).show();
			}
			Toast.makeText(CompetitionNDActivity.context, "Successful :)", Toast.LENGTH_LONG).show();
		}else
			Toast.makeText(CompetitionNDActivity.context, "Already registered for the event", Toast.LENGTH_LONG).show();
PushService.subscribe(getApplicationContext(), "excel"+eid, CompetitionNDActivity.class, R.drawable.excel_logo);		
stopSelf();
	}
	
	public class Insertparticipant extends AsyncTask<String, String, String>  {

		@Override
		protected String doInBackground(String... params)  {
			// TODO Auto-generated method stub
			
			DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
			HttpGet httppost = new HttpGet(url);
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
				stopSelf();
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
				flag = jsonObject.getJSONObject("participate").getInt("success");
				if(flag==1){
					tid = jsonObject.getJSONObject("participate").getInt("id");	
				}
					 
				

			}catch(JSONException e){
				e.printStackTrace();
				stopSelf();
				Toast.makeText(getApplicationContext(), "No internet connectivity"+e, Toast.LENGTH_LONG).show();
			}
			if(flag==1){
				insert();
			}else
				if(flag==2)
				Toast.makeText(CompetitionNDActivity.context, "Team id not found", Toast.LENGTH_LONG).show();
				else
					if(flag==3){
						
					if(team){
						Toast.makeText(CompetitionNDActivity.context, "Teamis full", Toast.LENGTH_LONG).show();
					}else{
						Toast.makeText(CompetitionNDActivity.context, "Already registered", Toast.LENGTH_LONG).show();
					}
						
						
					}
		}

		
	}
	
	void Alert(){
		AlertDialog.Builder alert = new AlertDialog.Builder(CompetitionNDActivity.context);

		alert.setTitle("Excel");
		alert.setMessage("If you have a team id please Enter.All members of a team should have same team id");
alert.setCancelable(false);
		// Set an EditText view to get user input 
		final EditText input = new EditText(CompetitionNDActivity.context);
		input.setInputType(InputType.TYPE_CLASS_NUMBER);
		input.setHint("Team id");
		
		alert.setView(input);

		alert.setPositiveButton("I Have", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) {
			if(input.length()==0){
				Toast.makeText(CompetitionNDActivity.context, "Please Enter a valid team id	", Toast.LENGTH_LONG).show();
				Alert();
			}else{
				tid = Integer.parseInt(input.getText().toString());
				url = "http://excelmec.org/Login2014/parti1.php?eid="+eid+"&pid="+uid+"&tid="+tid;
				try {
				 new Insertparticipant().execute(url);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					stopSelf();
				} 
			}
			
		  // Do something with value!
		  }
		});

		alert.setNegativeButton("I Dont Have", new DialogInterface.OnClickListener() {
		  public void onClick(DialogInterface dialog, int whichButton) {
			  url = "http://excelapi.net84.net/participate.json";
			  try {
				  url = "http://excelmec.org/Login2014/parti1.php?eid="+eid+"&pid="+uid+"&tid=0";
					new Insertparticipant().execute(url);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					stopSelf();
					e.printStackTrace();
				}
		  }
		});

		alert.show();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		
		String[] columns = { "PID"};
	ExcelDataBase	excelDataBase = new ExcelDataBase(getApplicationContext());
		SQLiteDatabase sqLiteDatabase = excelDataBase.getSQLiteDataBase();
	Cursor	 cursor = sqLiteDatabase.query("USER", columns, null,null, null, null, null);
		cursor.moveToFirst();
		uid =	cursor.getInt(cursor.getColumnIndex("PID"));
		
		team=	intent.getBooleanExtra("team", false);
		
	eid= 	intent.getIntExtra("eid", 0);
	
	Ename = intent.getStringExtra("Ename");
	
		
	
	
	AlertDialog.Builder alert = new AlertDialog.Builder(CompetitionNDActivity.context);

	alert.setTitle("Excel");
	alert.setMessage("Are you sure?");
alert.setCancelable(false);
	// Set an EditText view to get user input 
	

	alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	public void onClick(DialogInterface dialog, int whichButton) {
		Toast.makeText(getApplicationContext(), "Waiting for internet connection", Toast.LENGTH_LONG).show();
		if(team){
			Alert();
		}else{
			url = "http://excelmec.org/Login2014/parti1.php?eid="+eid+"&pid="+uid+"&tid=0";
			 new Insertparticipant().execute(url);
		}
	  // Do something with value!
	  }
	});

	alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
	  public void onClick(DialogInterface dialog, int whichButton) {
		stopSelf();
	  }
	});

	alert.show();
	
	
	
	
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
