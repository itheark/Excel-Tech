package com.greycodes.excel14.login;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.greycodes.excel14.HomeNDActivity;
import com.greycodes.excel14.R;
import com.greycodes.excel14.database.ExcelDataBase;


public class AccountFragment extends ListFragment {
	  String[] participating,columns;
	  ExcelDataBase excelDataBase;
	  SQLiteDatabase sqLiteDatabase;
	  Cursor cursor;
	  
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.user_account_activity, container, false);
		
		 columns = new String[]{"ENAME"};
		 
		 excelDataBase = new ExcelDataBase(getActivity());
		 sqLiteDatabase=	  excelDataBase.getSQLiteDataBase();
		 cursor=	sqLiteDatabase.query("PARTICIPATE", columns, null, null, null, null, null);
		
		cursor.moveToFirst();
		if(cursor.getCount()==0){
			participating = new String[1];
			participating[0]="You havent added any";
		}
		participating = new String[cursor.getCount()];
		for(int i =0;i<cursor.getCount();i++,cursor.moveToNext() )
		{
			participating[i]= cursor.getString(cursor.getColumnIndex("ENAME"));
		}
		
		 
		 ArrayAdapter<String> adapter= new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, participating);
		   setListAdapter(adapter);; 
		 
		 
		 return rootView;
	
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

		

}
