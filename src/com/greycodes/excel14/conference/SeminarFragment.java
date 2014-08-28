package com.greycodes.excel14.conference;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.greycodes.excel14.R;
import com.greycodes.excel14.database.ExcelDataBase;
import com.greycodes.excel14.database.ParseTS;

public class SeminarFragment extends Fragment {
	public  static TextView tv;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.conference_seminars, container, false);
		 tv = (TextView) rootView.findViewById(R.id.txtinclude);
		 try {
			String[] columns = new String[]{"TID","INTRO"};
			 String[] condition = new String[]{"0"};
			 ExcelDataBase excelDataBase = new ExcelDataBase(getActivity());
			 SQLiteDatabase sqLiteDatabase=	excelDataBase.getSQLiteDataBase();
			 Cursor cursor=	sqLiteDatabase.query("TALKSERIES", columns, "TID=?", condition, null, null, null);
			 cursor.moveToFirst();
			 if(cursor.getCount()==0){
				 getActivity().startService(new Intent(getActivity(), ParseTS.class));	
			 }else{
				 tv.setText(cursor.getString(cursor.getColumnIndex("INTRO")));
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return rootView;
	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		 getActivity().stopService(new Intent(getActivity(), ParseTS.class));	
	}
}
