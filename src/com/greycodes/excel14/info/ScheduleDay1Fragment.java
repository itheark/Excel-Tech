package com.greycodes.excel14.info;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.greycodes.excel14.R;
import com.greycodes.excel14.database.ExcelDataBase;

public class ScheduleDay1Fragment extends Fragment implements OnScrollListener {
	ExcelDataBase excelDataBase;
	String[] columns,selection;
	int count;
	String[] ename,venue,stime,duration,time;
	int[] level,cat;
	ScheduleAdapter adapter;
	ListView listView;
	TextView tvtime;
	Cursor cursor;
	@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.schedule_dayone, container, false);
		listView = (ListView) rootView.findViewById(R.id.schedule_listview);
		tvtime = (TextView) rootView.findViewById(R.id.schedule_time);
		   excelDataBase = new ExcelDataBase(getActivity());
	SQLiteDatabase sqLiteDatabase=	  excelDataBase.getSQLiteDataBase();
	columns = new String[]{"EID","ENAME","LEVEL","CAT","VENUE","STIME","DURATION","TIME"};
	selection = new String[]{"1"};
	 cursor=	sqLiteDatabase.query("SCHEDULE", columns, "DAY=?", selection, null, null,null);
	
	
	cursor.moveToFirst();
	count = cursor.getCount();
	ename = new String[count];
	cat = new int[count];
	venue = new String[count];
	stime = new String[count];
	duration = new String[count];
	time = new String[count];
	level = new int[count];
	for(int i=0;i<count;i++,cursor.moveToNext()){
		
		ename[i] = cursor.getString(cursor.getColumnIndex("ENAME"));
		cat[i] = cursor.getInt(cursor.getColumnIndex("CAT"));
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
		 listView.setAdapter(adapter);
	
	listView.setOnScrollListener(this);
		 
		 return rootView;
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
	int a=listView.getFirstVisiblePosition()+visibleItemCount/2;
	
	tvtime.setText(time[a]);
	tvtime.setAnimation(AnimationUtils.loadAnimation(getActivity(),android.R.anim.fade_in));
	
	}
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		
	}
}
