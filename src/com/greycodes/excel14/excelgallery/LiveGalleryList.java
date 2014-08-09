package com.greycodes.excel14.excelgallery;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.greycodes.excel14.R;
import com.greycodes.excel14.database.ExcelDataBase;



public class LiveGalleryList extends ListFragment  {

LiveGalleryAdapter adapter;
String[] desc,author,columns;
byte[][] bs;
Cursor cursor;
ExcelDataBase excelDataBase;
	@Override
public void onActivityCreated(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onActivityCreated(savedInstanceState);
	
	excelDataBase = new ExcelDataBase(getActivity());
	columns = new String[]{"DESC","IMAGE","AUTHOR"};
	 
	SQLiteDatabase sqLiteDatabase=	  excelDataBase.getSQLiteDataBase();
	 cursor=	sqLiteDatabase.query("GALLERY", columns, null, null, null, null, "GID DESC");
	 cursor.moveToFirst();
	 
author = new String[cursor.getCount()];
desc = new String[cursor.getCount()];
bs = new byte[cursor.getCount()][];
for(int i=0;i<cursor.getCount();i++,cursor.moveToNext()){
	desc[i]= cursor.getString(cursor.getColumnIndex("AUTHOR"));
	desc[i]= desc[i]+" : "+ cursor.getString(cursor.getColumnIndex("DESC"));
	bs[i]=cursor.getBlob(cursor.getColumnIndex("IMAGE"));
}
	
	adapter = new LiveGalleryAdapter(getActivity(),desc,bs);
	setListAdapter(adapter);
}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 View rootView = inflater.inflate(R.layout.livegallery_fragment, container, false);
		
		
		
		 return rootView;
	}

	



	
	
}
