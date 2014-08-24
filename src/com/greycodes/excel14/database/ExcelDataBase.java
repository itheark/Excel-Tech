package com.greycodes.excel14.database;

import com.greycodes.excel14.CompetitionNDActivity;
import com.greycodes.excel14.HomeNDActivity;
import com.greycodes.excel14.InfoNDActivity;
import com.greycodes.excel14.R;
import com.greycodes.excel14.login.AccountFragment;
import com.greycodes.excel14.login.LoginActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

public class ExcelDataBase {
Context context;
ExcelDatabaseHelper helper;
SQLiteDatabase db;
SharedPreferences sharedPreferences;
	public ExcelDataBase(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		Toast.makeText(context, "exceldatabase constructor", Toast.LENGTH_SHORT).show();

		helper= new ExcelDatabaseHelper();
	}
	
	public long insetCompetition(int eid,String cat,String name,String intro,String format,String rules,String date,String cordinator,String mob){
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues contentvalues = new ContentValues();
		contentvalues.put("EID", eid);
		contentvalues.put("CAT", cat);
		contentvalues.put("ENAME", name);
		contentvalues.put("INTRO", intro);
		contentvalues.put("FORMAT", format);
		contentvalues.put("RULES", rules);
		contentvalues.put("date", date);
		contentvalues.put("CORDINATOR", cordinator);
		contentvalues.put("MOB", mob);
	long flag=	db.insert("COMPETITION", null, contentvalues);
if(flag<0){
	Toast.makeText(context, "not inserted", Toast.LENGTH_SHORT).show();
}else{
	Toast.makeText(context, "inserted", Toast.LENGTH_SHORT).show();
}
return flag;
	}
	 public SQLiteDatabase getSQLiteDataBase(){
		 helper= new ExcelDatabaseHelper();
			SQLiteDatabase db = helper.getWritableDatabase();
			return db;
	 }
	public String getdata(){
		helper= new ExcelDatabaseHelper();
		SQLiteDatabase db = helper.getWritableDatabase();
		String[] columns = new String[]{"FORMAT"};
		String values=null;
		Cursor cursor= db.query("COMPETITION", columns, null, null, null, null, null);
		while(cursor.moveToNext()){
			values=cursor.getString(cursor.getColumnIndex("FORMAT"));
		}
		
		return values;
		
	}
	
	private class ExcelDatabaseHelper extends SQLiteOpenHelper{
		static final int Database_version=2;
		static final String Database_name= "excel.db";
		
		static final String Create_competiotion= "CREATE TABLE COMPETITION (EID INT PRIMARY KEY NOT NULL,CAT VARCHAR(20) NOT NULL,ENAME VARCHAR(25) NOT NULL,INTRO VARCHAR NOT NULL,FORMAT VARCHAR NOT NULL,RULES VARCHAR NOT NULL,DATE VARCHAR NOT NULL,CORDINATOR VARCHAR(30) NOT NULL,MOB VARCHAR NOT NULL);";
		static final String Create_newsfeeds = "CREATE TABLE NEWSFEED (NID INT PRIMARY KEY NOT NULL,SUBJECT VARCHAR NOT NULL,MESSAGE VARCHAR NOT NULL,PCODE INT NOT NULL,CAT INT NOT NULL);";
		static final String Create_WorkshopSeminar= "CREATE TABLE WORKSHOPSEMINAR (TSID INT PRIMARY KEY NOT NULL,TOPIC VARCHAR NOT NULL,INTRO VARCHAR NOT NULL,DATE VARCHAR NOT NULL,CORDINATOR VARCHAR(30) NOT NULL,MOB VARCHAR(15) NOT NULL);";
		static final String Create_dotIssue = "CREATE TABLE DOTISSUE (TSID INT PRIMARY KEY NOT NULL,INTRO VARCHAR NOT NULL,TOPIC VARCHAR NOT NULL,MODEARATOR VARCHAR NOT NULL,MODDESC VARCHAR NOT NULL,MODIMAGE BLOB NOT NULL,S1NAME VARCHAR NOT NULL,S1DESC VARCHAR NOT NULL,S1IMAGE BLOB NOT NULL,S2NAME VARCHAR NOT NULL,S2DESC VARCHAR NOT NULL,S2IMAGE BLOB NOT NULL,S3NAME VARCHAR NOT NULL,S3DESC VARCAHR NOT NULL,S3IMAGE BLOB  NOT NULL,S4NAME VARCAHR NOT NULL,S4DESC VARCHAR NOT NULL,S4IMAGE BLOB NOT NULL,S5NAME VARCHAR NOT NULL,S5DESC VARCHAR NOT NULL,S5IMAGE BLOB NOT NULL,EVENTDETAILS VARCHAR NOT NULL,DATE VARCHAR NOT NULL,CORDINATOR VARCHAR NOT NULL,MOB VARCHAR NOT NULL);";
		static final String Create_tedxmec = "CREATE TABLE TEDXMEC (TSID INT PRIMARY KEY NOT NULL,INTRO VARCHAR NOT NULL,EVENTDETAILS VARCHAR NOT NULL,S1NAME VARCHAR NOT NULL,S1DESC VARCHAR NOT NULL,S1IMAGE BLOB NOT NULL,S2NAME VARCHAR NOT NULL,S2DESC VARCHAR NOT NULL,S2IMAGE BLOB NOT NULL,S3NAME VARCHAR NOT NULL,S3DESC VARCAHR NOT NULL,S3IMAGE BLOB  NOT NULL,S4NAME VARCAHR NOT NULL,S4DESC VARCHAR NOT NULL,S4IMAGE BLOB NOT NULL,S5NAME VARCHAR NOT NULL,S5DESC VARCHAR NOT NULL,S5IMAGE BLOB NOT NULL,DATE VARCHAR NOT NULL,CORDINATOR VARCHAR NOT NULL,MOB VARCHAR NOT NULL);";
		static final String Create_userdetails = "CREATE TABLE USER (PID INT PRIMARY KEY NOT NULL,FNAME VARCHAR NOT NULL,LNAME VARCHAR NOT NULL,UNAME VARCHAR NOT NULL,PASSWORD VARCHAR NOT NULL,EMAIL VARCHAR NOT NULL,COLLEGE VARCHAR NOT NULL,DEPT VARCHAR NOT NULL,SEMESTER VARCHAR NOT NULL,PHONE VARCHAR NOT NULL,PICTURE BLOB);";

		//static final String Create_userdetails = "CREATE TABLE USER (PID INT PRIMARY KEY NOT NULL,NAME VARCHAR NOT NULL,PASSWORD VARCHAR NOT NULL,EMAIL VARCHAR NOT NULL,SEMESTER VARCHAR NOT NULL,PHONE VARCHAR NOT NULL,PICTURE BLOB);";
		static final String Create_sponsor = "CREATE TABLE SPONSOR (SID INT PRIMARY KEY NOT NULL,PCODE INT NOT NULL,IMAGE BLOB NOT NULL,URL VARCHAR(30) NOT NULL);";
		static final String Create_LiveGallery = "CREATE TABLE GALLERY (GID INT PRIMARY KEY NOT NULL,DESC VARCHAR(200) NOT NULL,IMAGE BLOB NOT NULL,AUTHOR VARCHAR(50) NOT NULL);";
		static final String Create_participate = "CREATE TABLE PARTICIPATE (EID INT PRIMARY KEY NOT NULL,ENAME VARCHAR NOT NULL,TID INT NOT NULL);";
		static final String Create_schedule = "CREATE TABLE SCHEDULE (SID INT PRIMARY KEY NOT NULL,EID INT NOT NULL,ENAME VARCHAR NOT NULL,LEVEL INT NOT NULL,DAY INT NOT NULL,CAT INT NOT NULL,VENUE VARCHAR(30) NOT NULL,STIME VARCHAR(25) NOT NULL,DURATION VARCHAR(15) NOT NULL,TIME VARCHAR(15) NOT NULL);";
		static final String Create_proshow = "CREATE TABLE PROSHOW (IMAGE BLOB NOT NULL,VIDEO VARCHAR NOT NULL);";
		public ExcelDatabaseHelper() {
			super(context, Database_name, null, Database_version);
			Toast.makeText(context, "ExcelDatabaseHelper constructor", Toast.LENGTH_SHORT).show();
		}

		
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			try {
				db.execSQL(Create_competiotion);
				db.execSQL(Create_newsfeeds);
				db.execSQL(Create_WorkshopSeminar);
				db.execSQL(Create_dotIssue);
				db.execSQL(Create_tedxmec);
				db.execSQL(Create_userdetails);
				db.execSQL(Create_sponsor);
				db.execSQL(Create_participate);
				db.execSQL(Create_LiveGallery);
				db.execSQL(Create_schedule);
				db.execSQL(Create_proshow);
				Toast.makeText(context, " db created", Toast.LENGTH_LONG).show();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				Toast.makeText(context, "error creating db", Toast.LENGTH_LONG).show();
				e.printStackTrace();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
		
		}
		
	}
	
	public boolean Isregistered() {
		
		sharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE);
		if(sharedPreferences.getBoolean("registered",false)){
			
	
			if(sharedPreferences.getBoolean("active",false)){
				return true;
			}else{
				
				new AlertDialog.Builder(context)
			    .setTitle("Excel")
			    .setMessage("It seems like you havent activated your account.Check your mail to activate.")
			    .setPositiveButton("Check Again", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			            // continue with delete
			        }
			     })
			    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			            // do nothing
			        }
			     })
			    .setIcon(android.R.drawable.ic_dialog_alert)
			     .show();
				
			}
			}else{
				Toast.makeText(context, "Please Register", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(context, LoginActivity.class);
				context.startActivity(intent);
				
			}
		
		return false;
		
	}
	
}
