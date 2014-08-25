package com.greycodes.excel14.info;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greycodes.excel14.R;
import com.greycodes.excel14.database.ExcelDataBase;
import com.greycodes.excel14.database.ImageDownloader;
import com.greycodes.excel14.database.ParseSponsor;

public class SponsorFragment extends ListFragment  {
	
	SponsorList sponsorList;
	
	String url,results;
	
	String[] imageurl,companyurl;
	int[] sid,pcode;
	int n;
	byte[][] imagebyte,bs;
	 int sponsor_flag;
	ExcelDataBase excelDataBase;
	ImageDownloader imageDownloader;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			
		 View rootView = inflater.inflate(R.layout.info_sponsor, container, false);
		 getActivity().startService(new Intent(getActivity(), ParseSponsor.class));
		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		assign();
		sponsorList = new SponsorList(getActivity(),bs);
		setListAdapter(sponsorList);
	}


	
	
	
	

	
	


	public void assign(){
		ExcelDataBase excelDataBase= new ExcelDataBase(getActivity());
		SQLiteDatabase sqLiteDatabase=	  excelDataBase.getSQLiteDataBase();
		String[] columns = new String[]{"PCODE","IMAGE","URL"};
		Cursor cursor=	sqLiteDatabase.query("SPONSOR", columns, null, null, null, null, "PCODE DESC");
		cursor.moveToFirst();
		bs = new byte[cursor.getCount()][];
		for(int i=0;i<cursor.getCount();i++,cursor.moveToNext()){
			
			bs[i]=cursor.getBlob(cursor.getColumnIndex("IMAGE"));
		}
	}


}
