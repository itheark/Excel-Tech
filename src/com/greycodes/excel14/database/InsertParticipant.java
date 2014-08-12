package com.greycodes.excel14.database;

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

import com.greycodes.excel14.CompetitionNDActivity;
import com.greycodes.excel14.R;
import com.parse.ParsePush;
import com.parse.PushService;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

public class InsertParticipant {
Context context;
String url,results,Ename;
int flag,id,eid,tid;
boolean team,success=false;

ProgressDialog progressDialog;
	public InsertParticipant(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		
	}
	
	public void PInsert(int eid,String Ename,boolean team){
		
		this.eid = eid;
		this.Ename = Ename;
		this.team = team;
		
		progressDialog = ProgressDialog.show(context, "Excel", "Please Wait...");
		progressDialog.setCancelable(false);
		if(team){
			Alert();
			
		}else{
			url = "http://excelapi.net84.net/participate.json";
			try {
				Object object = new Result().execute(url).get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	public void insert(){
		ExcelDataBase excelDataBase = new ExcelDataBase(context);
		SQLiteDatabase sqLiteDatabase = excelDataBase.getSQLiteDataBase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("EID", eid);
		contentValues.put("ENAME", Ename);
		contentValues.put("TID", id);
		if((sqLiteDatabase.insert("PARTICIPATE", null, contentValues))>0){
			Toast.makeText(context, "Your id for competition is "+id, Toast.LENGTH_LONG).show();
		}else
			Toast.makeText(context, "Already registered for the event", Toast.LENGTH_LONG).show();
PushService.subscribe(context, "excel"+eid, CompetitionNDActivity.class, R.drawable.excel_logo);		progressDialog.dismiss();
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
				JSONObject jsonObject;
				try{
					jsonObject = new JSONObject(results);
					flag = jsonObject.getJSONObject("participate").getInt("success");
					id = jsonObject.getJSONObject("participate").getInt("id");		 
					

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
			if(flag==1){
				insert();
			}else
				if(flag==2)
				Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
				else
					if(flag==3)
						Toast.makeText(context, "Team id not found", Toast.LENGTH_LONG).show();
						
			
		}

		
	}
	
	void Alert(){
		AlertDialog.Builder alert = new AlertDialog.Builder(context);

		alert.setTitle("Excel");
		alert.setMessage("If you have a team id please Enter.All members of a team should have same team id");
alert.setCancelable(false);
		// Set an EditText view to get user input 
		final EditText input = new EditText(context);
		input.setInputType(InputType.TYPE_CLASS_NUMBER);
		input.setHint("Team id");
		
		alert.setView(input);

		alert.setPositiveButton("I Have", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) {
			tid = Integer.parseInt(input.getText().toString());
			url = "http://excelapi.net84.net/participate.json";
			try {
				Object object = new Result().execute(url).get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  // Do something with value!
		  }
		});

		alert.setNegativeButton("I Dont Have", new DialogInterface.OnClickListener() {
		  public void onClick(DialogInterface dialog, int whichButton) {
			  url = "http://excelapi.net84.net/participate.json";
			  try {
					Object object = new Result().execute(url).get();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		  }
		});

		alert.show();
	}

}
