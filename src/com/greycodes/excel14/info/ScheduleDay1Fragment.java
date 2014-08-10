package com.greycodes.excel14.info;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.greycodes.excel14.R;
import com.greycodes.excel14.database.ExcelDataBase;

public class ScheduleDay1Fragment extends ListFragment {
	ExcelDataBase excelDataBase;
	String[] columns,selection;
	int count;
	String[] ename,cat,venue,stime,duration,time;
	int[] level;
	ScheduleAdapter adapter;
	@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.schedule_dayone, container, false);
		
		   excelDataBase = new ExcelDataBase(getActivity());
	SQLiteDatabase sqLiteDatabase=	  excelDataBase.getSQLiteDataBase();
	columns = new String[]{"EID","ENAME","LEVEL","CAT","VENUE","STIME","DURATION","TIME"};
	selection = new String[]{"1"};
	Cursor cursor=	sqLiteDatabase.query("SCHEDULE", columns, null, null, null, null,null);
	
	
	cursor.moveToFirst();
	Toast.makeText(getActivity(), Integer.toString(count), Toast.LENGTH_LONG).show();
	count = cursor.getCount();
	ename = new String[count];
	cat = new String[count];
	venue = new String[count];
	stime = new String[count];
	duration = new String[count];
	time = new String[count];
	level = new int[count];
	for(int i=0;i<count;i++,cursor.moveToNext()){
		
		ename[i] = cursor.getString(cursor.getColumnIndex("ENAME"));
		cat[i] = cursor.getString(cursor.getColumnIndex("CAT"));
		venue[i] = cursor.getString(cursor.getColumnIndex("VENUE"));
		stime[i] = cursor.getString(cursor.getColumnIndex("STIME"));
		duration[i] = cursor.getString(cursor.getColumnIndex("DURATION"));
		time[i] = cursor.getString(cursor.getColumnIndex("TIME"));
		level[i] = cursor.getInt(cursor.getColumnIndex("LEVEL"));
	}
	/*
	
	ename = new String[]{"Hash Include"};
	cat = new String[]{"CS"};
	venue = new String[]{"room 302"};
	stime = new String[]{"11pm"};
	duration = new String[]{"2hrs"};
	time = new String[]{"11PM"};
	level = new int[]{1};
*/
	adapter = new ScheduleAdapter(getActivity(), ename, level, cat, venue, stime, duration, stime);
		 setListAdapter(adapter);
		 
		 return rootView;
	}
}
