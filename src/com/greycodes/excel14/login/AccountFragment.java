package com.greycodes.excel14.login;


import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.greycodes.excel14.HomeNDActivity;
import com.greycodes.excel14.R;
import com.greycodes.excel14.database.ExcelDataBase;


public class AccountFragment extends ListFragment {
	  String[] participating,columns;
	  ExcelDataBase excelDataBase;
	  SQLiteDatabase sqLiteDatabase;
	 Cursor cursor;
	  TextView tvname;
	  SharedPreferences sharedPreferences;
	  ImageView propic,online;
	  String name;
	  Bitmap image;
	  int[] tid;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.user_account_activity, container, false);
		 
		
		 tvname = (TextView) rootView.findViewById(R.id.textView2);
		propic = (ImageView) rootView.findViewById(R.id.propic);
		online = (ImageView) rootView.findViewById(R.id.onlinescore);
		excelDataBase = new ExcelDataBase(getActivity());
		SQLiteDatabase sqLiteDatabase = excelDataBase.getSQLiteDataBase();
	//	 cursor = sqLiteDatabase.query("USER", columns, null,null, null, null, null);
	
		tvname.setText(HomeNDActivity.name[0]);
	propic.setImageBitmap(HomeNDActivity.image);
	/*String[] columns = { "FNAME", "PID", "PICTURE" };
	cursor = sqLiteDatabase.query("USER", columns, null,null, null, null, null);
	//name[0]= cursor.getString(cursor.getColumnIndex("FNAME"));
	Toast.makeText(getActivity(), Integer.toString(cursor.getCount()), Toast.LENGTH_LONG).show();
		Toast.makeText(getActivity(), "First", Toast.LENGTH_LONG).show();
	String[] columnname =cursor.getColumnNames();
	//name = cursor.getString(cursor.getColumnIndex(columnname[0]));
	if(cursor.isNull(cursor.getColumnIndex(columnname[0]))){
		Toast.makeText(getActivity(), "Null", Toast)
	}*/
	
	online.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View view) {
			
			
		}
	});
		 columns = new String[]{"ENAME","TID"};
		 
		
		
		 cursor=	sqLiteDatabase.query("PARTICIPATE", columns, null, null, null, null, null);
		
		cursor.moveToFirst();
		participating = new String[cursor.getCount()];
		tid = new int[cursor.getCount()];
		if(cursor.getCount()==0){
			participating = new String[]{"You havent registered for any"};
			tid =new  int[]{1};
			
			
		}
	

		
		for(int i =0;i<cursor.getCount();i++,cursor.moveToNext() )
		{
			participating[i]= cursor.getString(cursor.getColumnIndex("ENAME"));
			tid[i] = cursor.getInt(cursor.getColumnIndex("TID"));
		}
	
	
		 
		AccountAdapter adapter  = new AccountAdapter(getActivity(), participating, tid);
		   setListAdapter(adapter);; 
		
		
		 return rootView;
	
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

		

}
