package com.greycodes.excel14.login;


import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
	  CircularImageView circularImageView;
	  String[] name;
	  Bitmap image;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.user_account_activity, container, false);
		 tvname = (TextView) rootView.findViewById(R.id.textView2);
		 sharedPreferences = getActivity().getSharedPreferences("login",
					Context.MODE_PRIVATE);
			
				String[] columns = { "NAME", "EMAIL", "PICTURE" };
				excelDataBase = new ExcelDataBase(getActivity());
				SQLiteDatabase sqLiteDatabase = excelDataBase.getSQLiteDataBase();
				 cursor = sqLiteDatabase.query("USER", columns, null,null, null, null, null);
				cursor.moveToFirst();
				name[0] = cursor.getString(cursor.getColumnIndex("NAME"));
				tvname.setText(name[0]);

				if (sharedPreferences.getBoolean("fb", false)) {
					byte[] bs;
					bs = cursor.getBlob(cursor.getColumnIndex("PICTURE"));
					 image = BitmapFactory.decodeByteArray(bs, 0, bs.length);
					circularImageView = (CircularImageView) rootView.findViewById(R.id.propic);
				circularImageView.setImageBitmap(image);
				}
			
		 columns = new String[]{"ENAME"};
		 
		
		
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
		
		 
		 ArrayAdapter<String> adapter= new ArrayAdapter<String>(inflater.getContext(), R.layout.participate_listitem, participating);
		   setListAdapter(adapter);; 
		 
		 
		 return rootView;
	
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

		

}
