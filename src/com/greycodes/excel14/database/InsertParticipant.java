package com.greycodes.excel14.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class InsertParticipant {
Context context;

	public InsertParticipant(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		
	}
	
	public boolean insert(int eid,String ename){
		ExcelDataBase excelDataBase = new ExcelDataBase(context);
		SQLiteDatabase sqLiteDatabase = excelDataBase.getSQLiteDataBase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("EID", eid);
		contentValues.put("ENAME", ename);
		if((sqLiteDatabase.insert("PARTICIPATE", null, contentValues))>0){
			return true;
		}else
		return false;
		
	}

}
